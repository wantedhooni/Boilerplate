package com.revy.api_server.domain.account.repo;

import com.revy.api_server.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByOwnerIdAndAccountNo(Long ownerId, String accountNo);

    @Modifying
    @Query(
            value = """
            UPDATE Account account SET account.cashBalance = account.cashBalance + :amount,
            account.availableCash = account.availableCash + :amount
            WHERE account.accountNo = :accountNo
            """)
    void deposit(String accountNo, BigDecimal amount);
}
