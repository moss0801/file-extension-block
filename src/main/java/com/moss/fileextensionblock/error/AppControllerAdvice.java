package com.moss.fileextensionblock.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler({Exception.class})
    public HttpResult exception(Exception e, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        var result = new HttpResult();
        result.setError(e.getClass().getSimpleName());
        result.setMessage(e.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return result;
    }
}
