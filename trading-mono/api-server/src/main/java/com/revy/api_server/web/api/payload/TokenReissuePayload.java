package com.revy.api_server.web.api.payload;

import com.revy.api_server.web.service.dto.LoginResult;
import jakarta.validation.constraints.NotBlank;

public class TokenReissuePayload {
    public record Req(
            @NotBlank
            String refreshToken
    ) {

    }

    public record Res(
            String tokenType,
            String accessToken,
            String refreshToken

    ) {
        public static Res from(LoginResult result) {
            return new Res(
                    result.getTokenType(),
                    result.getAccessToken(),
                    result.getRefreshToken()
            );
        }
    }
}
