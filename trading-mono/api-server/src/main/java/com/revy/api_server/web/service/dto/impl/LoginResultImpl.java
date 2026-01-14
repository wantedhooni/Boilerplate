package com.revy.api_server.web.service.dto.impl;

import com.revy.api_server.web.service.dto.LoginResult;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResultImpl implements LoginResult {

    String tokenType;
    String accessToken;
    String refreshToken;

    @Builder

    public LoginResultImpl(String tokenType, String accessToken, String refreshToken) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
