package com.revy.api_server.domain.account;

import com.revy.api_server.domain.account.enums.AccountStatus;
import com.revy.api_server.domain.account.enums.AccountType;
import com.revy.api_server.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity<Long> {

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 16)
    private AccountType type; // 계좌 유형(CASH/MARGIN)

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private AccountStatus status; // 계좌 상태 - ACTIVE(정상), SUSPENDED(정지), CLOSED(해지) 등

    @Column(name = "account_no", nullable = false, length = 30)
    private UUID accountNo; // 계좌번호(업무키) - 외부 노출/업무상 식별용, 유니크

    @Column(name = "owner_id", nullable = false)
    private Long ownerId; // 계좌 소유자 ID - 사용자/회원 테이블의 식별자(FK를 직접 안 두는 단순화 버전)

    @Column(name = "currency", nullable = false, length = 3)
    private String currency; // 통화 코드 - "KRW", "USD" 등 (ISO 4217)

    @Column(name = "cash_balance", nullable = false, precision = 19, scale = 4)
    private BigDecimal cashBalance; // 현금 잔고 - 실제 보유 현금(입출금/정산 반영된 금액)

    @Column(name = "available_cash", nullable = false, precision = 19, scale = 4)
    private BigDecimal availableCash; // 주문가능 현금 - 주문 예약금(hold) 차감 후 즉시 사용 가능한 금액

    public static Account createNewAccount(Long userId, AccountType type, String currency) {
        Account account = new Account();
        account.type = type;
        account.status = AccountStatus.ACTIVE;
        account.accountNo = UUID.randomUUID();
        account.ownerId = userId;
        account.currency = currency;
        account.cashBalance = BigDecimal.ZERO;
        account.availableCash = BigDecimal.ZERO;
        return account;
    }



}
