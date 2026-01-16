package com.revy.api_server.domain.account.repo;

import com.revy.api_server.domain.account.Account;
import com.revy.api_server.domain.account.repo.query_repo.AccountQueryRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long>, AccountQueryRepo {
    @Modifying
    @Query(
            value = """
            UPDATE Account account SET account.cashBalance = account.cashBalance + :amount,
            account.availableCash = account.availableCash + :amount
            WHERE account.accountNo = :accountNo
            """)
    void addBalance(String accountNo, BigDecimal amount);
}
