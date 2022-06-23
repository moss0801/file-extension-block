package com.moss.fileextensionblock.service;

import com.moss.fileextensionblock.config.AppProperties;
import com.moss.fileextensionblock.domain.model.FileExtensionBlock;
import com.moss.fileextensionblock.domain.repository.FileExtensionBlockRepository;
import com.moss.fileextensionblock.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 파일 확장자 차단 서비스
 */
@Slf4j
@Validated
@Service
public class FileExtensionBlockService {
    private final FileExtensionBlockRepository repository;
    private final int maxCustomExtension;

    public FileExtensionBlockService(FileExtensionBlockRepository repository, AppProperties appProperties) {
        this.repository = repository;
        this.maxCustomExtension = appProperties.getMaxCustomExtension();
        log.info("maxCustomExtension: " + maxCustomExtension);
    }

    /**
     * 파일 확장자 추가
     */
    @Transactional
    public void addFileExtensionBlock(@Valid AddFileExtensionBlockCommand command) {
        var extension = FileExtensionBlock.resolveExtension(command.getExtension());

        // 중복 체크
        if (repository.existsByExtension(extension)) {
            throw new IllegalArgumentException("extension " + command.getExtension() + " is already exist.");
        }

        // 고정여부
        boolean isFixed = command.getIsFixed();
        // 커스텀 확장자 개수 제한 확인
        if (!isFixed) {
            if (maxCustomExtension < repository.countByIsFixedFalse()) {
                throw new IllegalStateException("max custom extension is " + maxCustomExtension + ".");
            }
        }

        // 활성화 여부
        Boolean isEnabled = isFixed ? command.getIsEnabled() : true;

        // 저장
        FileExtensionBlock fileExtensionBlock = FileExtensionBlock.builder()
                .extension(extension)
                .isFixed(isFixed)
                .isEnabled(isEnabled)
                .build();
        repository.save(fileExtensionBlock);
    }

    /**
     * 파일 확장자 차단 목록 조회
     */
    @Transactional(readOnly = true)
    public List<FileExtensionBlockDto> getFileExtensionBlockDtos(@Valid FileExtensionBlockDtosQuery query) {
        List<FileExtensionBlock> list;
        if (null != query.getIsEnabled()) {
            list = repository.findAllByIsEnabledOrderByExtensionAsc(query.getIsEnabled());
        } else {
            list = repository.findAllByOrderByExtensionAsc();
        }
        return DtoAssembler.to(list, FileExtensionBlockDto.class);
    }

    /**
     * 파일 확장자 차단 수정
     */
    @Transactional
    public void updateFileExtensionBlock(@Valid UpdateFileExtensionBlockCommand command) {
        var extension = FileExtensionBlock.resolveExtension(command.getExtension());

        // 존재 확인
        var optional = repository.findByExtension(extension);
        if (optional.isEmpty()) {
            throw new IllegalStateException("extension " + extension + " is not exists.");
        }

        var block = optional.get();

        // 수정
        block.setEnabled(command.getIsEnabled());
    }

    /**
     * 파일 확장자 삭제
     */
    @Transactional
    public void deleteFileExtensionBlock(
            @NotBlank @Length(max = FileExtensionBlock.Constraint.ExtensionMaxLength) String extension) {
        extension = FileExtensionBlock.resolveExtension(extension);

        // 존재 확인
        var optional = repository.findByExtension(extension);
        if (optional.isEmpty()) {
            throw new IllegalStateException("extension " + extension + " is not exists.");
        }
        
        // 삭제
        repository.delete(optional.get());
    }
}
