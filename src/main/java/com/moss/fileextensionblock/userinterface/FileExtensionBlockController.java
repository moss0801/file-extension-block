package com.moss.fileextensionblock.userinterface;

import com.moss.fileextensionblock.dto.AddFileExtensionBlockCommand;
import com.moss.fileextensionblock.dto.FileExtensionBlockDto;
import com.moss.fileextensionblock.dto.FileExtensionBlocksDto;
import com.moss.fileextensionblock.dto.UpdateFileExtensionBlockCommand;
import com.moss.fileextensionblock.service.FileExtensionBlockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 파일 확장자 차단 Controller
 */
@RestController
@RequestMapping("api/fileExtensionBlocks")
public class FileExtensionBlockController {
    private FileExtensionBlockService service;

    public FileExtensionBlockController(FileExtensionBlockService service) {
        this.service = service;
    }

    /**
     * 파일 확장자 차단 추가
     * @param command
     */
    @PostMapping
    public void addFileExtensionBlock(@RequestBody AddFileExtensionBlockCommand command) {
        service.addFileExtensionBlock(command);
    }

    /**
     * 파일 확장자 차단 목록 조회
     */
    @GetMapping
    public List<FileExtensionBlockDto> getFileExtensionBlocks() {
        return service.getFileExtensionBlockDtos();
    }

    /**
     * 파일 확장자 차단 수정
     */
    @PutMapping("{extension}")
    public void updateFileExtensionBlock(@PathVariable String extension, @RequestBody UpdateFileExtensionBlockCommand command) {
        command.setExtension(extension);
        service.updateFileExtensionBlock(command);
    }

    /**
     * 파일 확장자 차단 삭제
     */
    @DeleteMapping("{extension}")
    public void deleteFileExtensionBlock(@PathVariable String extension) {
        service.deleteFileExtensionBlock(extension);
    }
}
