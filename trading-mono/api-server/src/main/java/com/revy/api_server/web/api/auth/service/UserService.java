package com.revy.api_server.web.api.auth.service;

import com.revy.api_server.web.api.auth.service.dto.UserInfoResult;

public interface UserService {
    UserInfoResult getUserInfo(Long userId);
}
