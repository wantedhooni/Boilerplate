package com.revy.api_server.web.api.account.payload;

import com.revy.api_server.domain.account.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateAccountPayload {

    @Schema(name= "CreateAccountPayload.Req")
    public record Req(
            @NotNull
            AccountType accountType,

            @NotEmpty
            String currency
    ) {
    }

    @Schema(name = "CreateAccountPayload.Res")
    public record Res(
            AccountType type,
            String accountNo,
            String currency,
            BigDecimal cashBalance,
            BigDecimal availableCash
    ) {

    }
}
