package com.moss.fileextensionblock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 파일 확장자 목록 조회
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FileExtensionBlocksDto {
    // 고정 확장자 목록
    List<FileExtensionBlockDto> fixes;

    // 커스텀 확장자 목록
    List<FileExtensionBlockDto> customs;
}
