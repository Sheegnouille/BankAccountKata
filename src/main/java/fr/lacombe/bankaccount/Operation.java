package fr.lacombe.bankaccount;

import java.text.SimpleDateFormat;
import java.util.Date;

import static fr.lacombe.bankaccount.Amount.ZERO_AMOUNT;

class Operation {

    private final Date date;
    private final Amount amount;
    private final Amount balanceAfterOperation;

    Operation(Date operationDate, Amount operationAmount, Amount balanceAfterOperation) {
        this.amount = operationAmount;
        this.balanceAfterOperation = new Amount(balanceAfterOperation);
        date = operationDate;
    }

    String printStatement() {
        return new SimpleDateFormat("dd/MM/yyyy").format(date) +
                "\t" + amount +
                "\t" + balanceAfterOperation.toString();
    }

    static final class OperationBuilder {
        private Amount amount;
        private Amount balanceAfterOperation;
        private Date date;

        private OperationBuilder() {
        }

        private static OperationBuilder anOperation() {
            return new OperationBuilder();
        }

        static OperationBuilder aDeposit(Amount amount) {
            return anOperation().withAmount(amount);
        }

        static OperationBuilder aWithdrawal(Amount amount) {
            return anOperation().withAmount(ZERO_AMOUNT.minus(amount));
        }

        OperationBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        private OperationBuilder withAmount(Amount amount) {
            this.amount = amount;
            return this;
        }

        OperationBuilder withBalanceAfterOperation(Amount balanceAfterOperation) {
            this.balanceAfterOperation = balanceAfterOperation;
            return this;
        }

        Operation build() {
            return new Operation(this.date, this.amount, balanceAfterOperation);
        }
    }
}
