package com.hexagonal.domain.bidding.port.shared;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Money {

    BigDecimal amount;
    public static final Money ZERO = new Money(0);

    public Money(int i) {
        this.amount = new BigDecimal(i);
    }

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money add(Money other) {
        return new Money(amount.add(other.amount));
    }

    public Money subtract(Money other) {
        return new Money(amount.subtract(other.amount));
    }
}
