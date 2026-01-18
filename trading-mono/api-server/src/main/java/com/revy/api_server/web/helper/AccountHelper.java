package com.revy.api_server.web.helper;

import com.revy.api_server.domain.account.Account;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface AccountHelper {
    Account findById(Long id);

    Account findOneByAccountNo(String accountNo);

    Account findOneByOwnerIdAndAccountNo(Long userId, String accountNo);

    @Transactional
    Account updateBuyOrderAccountBalance(Account account, BigDecimal amount);

    @Transactional
    Account updateCancelOrderAccountBalance(Account account, BigDecimal amount);
}
