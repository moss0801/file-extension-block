package com.moss.fileextensionblock.dto;

import lombok.Data;

/**
 * 파일 확장자 차단 Dto 목록 조회 쿼리
 */
@Data
public class FileExtensionBlockDtosQuery {
    // 활성화 여부
    private Boolean isEnabled;
}
