package com.moss.fileextensionblock.domain.repository;

import com.moss.fileextensionblock.domain.model.FileExtensionBlock;
import com.moss.fileextensionblock.dto.FileExtensionBlockDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileExtensionBlockRepository extends JpaRepository<FileExtensionBlock, Long> {
    /**
     * 확장자 존재 여부
     */
    boolean existsByExtension(String extension);

    /**
     * 확장자로 조회
     */
    Optional<FileExtensionBlock> findByExtension(String extension);

    List<FileExtensionBlock> findAllByOrderByExtensionAsc();

    /**
     * 고정 여부로 목록조회
     */
    List<FileExtensionBlock> findAllByIsFixedOrderByExtensionAsc(Boolean isFixed);

    /**
     * 커스텀 수 조회
     */
    int countByIsFixedFalse();
}
