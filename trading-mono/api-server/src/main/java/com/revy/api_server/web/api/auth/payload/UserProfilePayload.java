package com.revy.api_server.web.api.auth.payload;

import com.revy.api_server.web.api.auth.service.dto.UserInfoResult;

public class UserProfilePayload {

    public record Res(
            String email,
            String name,
            String phone,
            String address
    ) {

        public static Res from(UserInfoResult userInfoResult) {
            return new Res(
                    userInfoResult.getEmail(),
                    userInfoResult.getName(),
                    userInfoResult.getPhone(),
                    userInfoResult.getAddress());
        }
    }
}
