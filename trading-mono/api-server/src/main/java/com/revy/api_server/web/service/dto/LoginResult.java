package com.revy.api_server.web.service.dto;

public interface LoginResult {
    String getTokenType();

    String getAccessToken();

    String getRefreshToken();
}
