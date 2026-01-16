package com.revy.api_server.domain.account.repo.query_repo;

import com.revy.api_server.domain.account.Account;

import java.util.Optional;

public interface AccountQueryRepo {

    Optional<Account> findOneByAccountNo(String accountNo);

    Optional<Account> findOneByOwnerIdAndAccountNo(Long ownerId, String accountNo);
}
