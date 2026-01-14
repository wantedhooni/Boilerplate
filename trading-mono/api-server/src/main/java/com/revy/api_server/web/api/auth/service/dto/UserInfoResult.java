package com.revy.api_server.web.api.auth.service.dto;

import com.revy.api_server.domain.user.UserStatus;

public interface UserInfoResult {
    String getEmail();

    String getName();

    String getPhone();

    String getAddress();

    UserStatus getStatus();
}
