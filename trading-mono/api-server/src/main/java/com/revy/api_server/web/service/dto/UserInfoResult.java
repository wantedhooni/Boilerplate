package com.revy.api_server.web.service.dto;

import com.revy.api_server.domain.user.UserStatus;

public interface UserInfoResult {
    String getEmail();

    String getName();

    String getPhone();

    String getAddress();

    UserStatus getStatus();
}
