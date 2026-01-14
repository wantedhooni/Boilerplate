package com.revy.api_server.web.api.auth.service;

import com.revy.api_server.web.api.auth.service.dto.LoginCommand;
import com.revy.api_server.web.api.auth.service.dto.LoginResult;
import com.revy.api_server.web.api.auth.service.dto.SignupCommand;

public interface AuthService {
    Long signup(SignupCommand signupCommand);

    /**
     * 로그인 후 토큰을 발급한다.
     *
     * @param loginCommand 로그인 요청
     * @return 토큰 응답
     */
    LoginResult login(LoginCommand loginCommand);

    LoginResult reissue(String refreshToken);

    /**
     * 로그아웃 처리를 수행한다.
     *
     * @param accessToken 액세스 토큰
     */
    void logout(String accessToken);

    /**
     * 회원 탈퇴를 처리한다.
     *
     * @param userId 사용자 ID
     */
    void withdraw(Long userId);
}
