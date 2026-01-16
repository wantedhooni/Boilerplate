package com.revy.api_server.web.api.account.payload;

import com.revy.api_server.domain.account.enums.AccountStatus;
import com.revy.api_server.domain.account.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public class MyAccountsPayload {
    @Schema(name = "MyAccountsPayload.Req")
    public record Req(
            @RequestParam(required = false)
            Set<String> currencies,
            @RequestParam(required = false)
            Set<AccountType> types,
            @RequestParam(required = false)
            Set<AccountStatus> statuses
    ) {

    }

    @Schema(name = "MyAccountsPayload.Res")
    public record Res(
            AccountType type,
            String accountNo,
            String currency,
            AccountStatus status,
            BigDecimal cashBalance,
            BigDecimal availableCash
    ) {
    }
}
