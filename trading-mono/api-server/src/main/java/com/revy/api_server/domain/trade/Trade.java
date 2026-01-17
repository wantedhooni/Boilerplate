package com.revy.api_server.domain.trade;

import com.revy.api_server.domain.account.Account;
import com.revy.api_server.domain.common.BaseUUIDEntity;
import com.revy.api_server.domain.trade.enums.TradeSide;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "stock_trade", indexes = {})
public class Trade extends BaseUUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "symbol", nullable = false, length = 20)
    private String symbol;

    @Enumerated(EnumType.STRING)
    @Column(name = "side", nullable = false, length = 10)
    private TradeSide side;

    @Column(name = "qty", nullable = false, precision = 19, scale = 6)
    private BigDecimal qty; // 체결 수량

    @Column(name = "price_amount", nullable = false, precision = 19, scale = 6)
    private BigDecimal priceAmount; // 체결 단가(금액)

    @Column(name = "price_currency", nullable = false, length = 3)
    private String priceCurrency; // 단가 통화 (예: KRW, USD)

    @Column(name = "fee_amount", nullable = false, precision = 19, scale = 6)
    private BigDecimal feeAmount; // 수수료(없으면 0)

    @Column(name = "tax_amount", nullable = false, precision = 19, scale = 6)
    private BigDecimal taxAmount; // 세금(없으면 0)

    @Column(name = "realized_pnl_amount", precision = 19, scale = 6)
    private BigDecimal realizedPnlAmount; // 매도 시 실현손익(선택)

    @Column(name = "executed_at", nullable = false)
    private LocalDateTime executedAt;
}
