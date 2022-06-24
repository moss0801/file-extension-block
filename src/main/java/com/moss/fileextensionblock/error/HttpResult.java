package com.moss.fileextensionblock.error;

import lombok.Data;

/**
 * API 호출 결과
 * 에러 발생시 사용
 */
@Data
public class HttpResult {
    // 에러
    private String error;

    // 메시지
    private String message;
}
