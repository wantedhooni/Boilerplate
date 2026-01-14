package com.revy.api_server.web.api.auth.payload;

import com.revy.api_server.web.api.auth.service.dto.SignupCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignupPayload {
    public record Req(
            @Email
            @NotBlank
            String email,
            @NotBlank
            String password,
            @NotBlank
            String name,
            @NotBlank
            String phone,
            @NotBlank
            String address
    ) implements SignupCommand {

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getPhone() {
            return phone;
        }

        @Override
        public String getAddress() {
            return address;
        }
    }

    public record Res(
            Long userId,
            String message
    ) {
    }
}
