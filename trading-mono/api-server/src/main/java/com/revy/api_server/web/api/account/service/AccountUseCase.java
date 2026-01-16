package com.revy.api_server.web.api.account.service;

import com.revy.api_server.domain.account.enums.AccountType;
import com.revy.api_server.web.api.account.payload.CreateAccountPayload;
import com.revy.api_server.web.api.account.payload.MyAccountsPayload;
import com.revy.api_server.web.api.account.payload.TransferPayload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public interface AccountUseCase {
    CreateAccountPayload.Res create(Long userId, AccountType accountType, String currency);

    List<MyAccountsPayload.Res> getMyAccounts(Long id, MyAccountsPayload.Req req);

    void deposit(Long userId, String accountNo, BigDecimal amount);

    void withdraw(Long userId, String accountNo, BigDecimal amount);
    TransferPayload.Res transfer(Long userId, TransferPayload.Req req);
}
