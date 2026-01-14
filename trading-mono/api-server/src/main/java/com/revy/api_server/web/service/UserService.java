package com.revy.api_server.web.service;

import com.revy.api_server.web.service.dto.UserInfoResult;

public interface UserService {
    UserInfoResult getUserInfo(Long userId);
}
