package com.revy.api_server.web.api.account.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.revy.api_server.domain.account.Account;
import com.revy.api_server.domain.account.QAccount;
import com.revy.api_server.domain.account.enums.AccountType;
import com.revy.api_server.domain.account.repo.AccountRepo;
import com.revy.api_server.web.api.account.payload.CreateAccountPayload;
import com.revy.api_server.web.api.account.payload.MyAccountsPayload;
import com.revy.api_server.web.api.account.service.AccountUseCase;
import com.revy.common.utils.BigDecimalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountUseCaseImpl implements AccountUseCase {
    private final AccountRepo accountRepo;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @Transactional
    public CreateAccountPayload.Res create(Long userId, AccountType accountType, String currency) {
        Assert.notNull(userId, "userId is null");
        Assert.notNull(accountType, "accountType is null");
        Assert.hasText(currency, "currency is empty");
        Account newAccount = Account.createNewAccount(userId, accountType, currency);
        newAccount = accountRepo.save(newAccount);
        return mapper.convertCreateAccountRes(newAccount);

    }

    @Override
    @Transactional(readOnly = true)
    public List<MyAccountsPayload.Res> getMyAccounts(Long userId, MyAccountsPayload.Req req) {
        Assert.notNull(userId, "userId is null");

        // 동적쿼리 보정
        if (req == null) {
            req = new MyAccountsPayload.Req(null, null, null);
        }

        JPAQuery<MyAccountsPayload.Res> query = jpaQueryFactory
                .select(Projections.constructor(MyAccountsPayload.Res.class,
                        QAccount.account.publicId,
                        QAccount.account.type,
                        QAccount.account.accountNo,
                        QAccount.account.currency,
                        QAccount.account.status,
                        QAccount.account.cashBalance,
                        QAccount.account.availableCash
                )).from(QAccount.account);
        query.where(QAccount.account.ownerId.eq(userId));

        // 조건부 동적쿼리
        if (req.currencies() != null && !req.currencies().isEmpty()) {
            query.where(QAccount.account.currency.in(req.currencies()));
        }

        if (req.types() != null && !req.types().isEmpty()) {
            query.where(QAccount.account.type.in(req.types()));
        }

        if (req.statuses() != null && !req.statuses().isEmpty()) {
            query.where(QAccount.account.status.in(req.statuses()));
        }

        // 만든 순서대로 정렬
        query.orderBy(QAccount.account.createdDate.asc());

        // TODO:Revy 계좌 목록 조회할때 페이징이 있던가??
        return query.fetch();
    }

    @Override
    @Transactional
    public void depositAccount(Long userId, String accountNo, BigDecimal amount) {
        Assert.notNull(userId, "userId is null");
        Assert.hasText(accountNo, "accountNo is empty");
        Assert.notNull(amount, "amount is empty");
        Assert.isTrue(BigDecimalUtil.isPositive(amount), "amount is not positive");

        accountRepo.findByOwnerIdAndAccountNo(userId, accountNo)
                                     .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountNo));

        accountRepo.deposit(accountNo, amount);
    }

    static class mapper {
        public static CreateAccountPayload.Res convertCreateAccountRes(Account newAccount) {
            return new CreateAccountPayload.Res(newAccount.getPublicId(), newAccount.getType(), newAccount.getAccountNo(), newAccount.getCurrency(), newAccount.getCashBalance(), newAccount.getAvailableCash());
        }
    }
}
