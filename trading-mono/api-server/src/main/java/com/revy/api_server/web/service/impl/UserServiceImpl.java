package com.revy.api_server.web.service.impl;

import com.revy.api_server.domain.user.User;
import com.revy.api_server.domain.user.repo.UserRepository;
import com.revy.api_server.web.service.UserService;
import com.revy.api_server.web.service.dto.UserInfoResult;
import com.revy.api_server.web.service.dto.impl.UserInfoResultImpl;
import com.revy.common.error.ApiException;
import com.revy.common.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserInfoResult getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        return UserInfoResultImpl
                .builder()
                .email(user.getEmail())
                .status(user.getStatus())
                .name(user.getDetail().getName())
                .phone(user.getDetail().getPhone())
                .address(user.getDetail().getAddress())
                .build();
    }
}
