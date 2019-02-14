package fr.lacombe.bankaccount;

import java.util.ArrayList;
import java.util.List;

import static fr.lacombe.bankaccount.Amount.ZERO_AMOUNT;
import static fr.lacombe.bankaccount.Operation.OperationBuilder.aDeposit;
import static fr.lacombe.bankaccount.Operation.OperationBuilder.aWithdrawal;

class Account {

    private Amount balance = ZERO_AMOUNT;
    List<Operation> operations = new ArrayList<>();
    private DateProvider provider;

    Account(DateProvider provider) {
        this.provider = provider;
    }

    public void deposit(Amount depositAmount) {
        balance = balance.add(depositAmount);
        operations.add(aDeposit(depositAmount)
                .withDate(provider.getDate())
                .withBalanceAfterOperation(balance)
                .build());
    }

    public void withdraw(Amount withdrawAmount) {
        balance = balance.minus(withdrawAmount);
        operations.add(aWithdrawal(withdrawAmount)
                .withDate(provider.getDate())
                .withBalanceAfterOperation(balance)
                .build());
    }

    public String printBalance() {
        return balance.toString();
    }

    public String printStatement() {
        StringBuilder statement = new StringBuilder("Date\tAmount\tBalance");
        for (Operation operation: operations
        ) {
            statement.append("\n").append(operation.printStatement());
        }
        return statement.toString();
    }

    public static final class AccountBuilder {
        List<Operation> operations = new ArrayList<Operation>();
        private Amount balance = ZERO_AMOUNT;
        private DateProvider provider;

        private AccountBuilder() {
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder withBalance(Amount balance) {
            this.balance = balance;
            return this;
        }

        public AccountBuilder withOperations(List<Operation> operations) {
            this.operations = operations;
            return this;
        }

        public AccountBuilder withProvider(DateProvider provider) {
            this.provider = provider;
            return this;
        }

        public Account build() {
            Account account = new Account(provider);
            account.operations = this.operations;
            account.balance = this.balance;
            return account;
        }
    }
}
