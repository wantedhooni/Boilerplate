package com.revy.api_server.web.api.common;

import com.revy.api_server.web.exception.AuthException;
import com.revy.common.api.ApiResponse;
import com.revy.common.error.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;


// TODO:Revy -> 나중에 Exception 정리해서 합치자.
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<Void> handleAuth(AuthException e) {
        log.warn("AuthException:", e);
        return ApiResponse.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<Void> handleAuthenticationException(AuthenticationException e) {
        log.warn("AuthenticationException:", e);
        return ApiResponse.fail(String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<Void> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("AccessDeniedException:", e);
        return ApiResponse.fail(String.valueOf(HttpStatus.UNAUTHORIZED.value()), e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> handleUnknown(Exception e) {
        log.error("unknown exception:", e);
        return ApiResponse.fail("INTERNAL_ERROR", e.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> handleApiException(ApiException e) {
        log.error("unknown exception:", e);
        return ApiResponse.fail(e.getCode(), e.getMessage());
    }

}