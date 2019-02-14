package fr.lacombe.bankaccount;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

class Amount {

    static final Amount ZERO_AMOUNT = new Amount(ZERO);
    private final BigDecimal amount;

    Amount(BigDecimal amount) {
        this.amount = amount;
    }

    Amount(Amount otherAmount) {
        this.amount = otherAmount.amount;
    }

    Amount add(Amount otherAmount) {
        return new Amount(amount.add(otherAmount.amount));
    }

    Amount minus(Amount otherAmount) {
        return new Amount(amount.subtract(otherAmount.amount));
    }

    @Override
    public String toString() {
        return amount.toString();
    }
}
