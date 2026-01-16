package com.revy.api_server.web.exception;

import com.revy.common.error.ApiException;
import com.revy.common.error.ErrorCode;

public class AccountException extends ApiException {
    public AccountException(String code, String message) {
        super(code, message);
    }

    public AccountException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AccountException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
