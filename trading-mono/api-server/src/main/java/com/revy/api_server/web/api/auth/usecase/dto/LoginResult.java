package com.revy.api_server.web.api.auth.usecase.dto;

public interface LoginResult {
    String getTokenType();

    String getAccessToken();

    String getRefreshToken();
}
