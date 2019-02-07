import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    void given_a_hundred_deposit_on_empty_new_account_balance_should_be_one_hundred() {
        Account account = new Account();
        account.deposit(new Amount(100.0));
        String balance = account.printBalance();
        String hundredOnAccountBalance = "100.0";
        assertThat(balance).isEqualTo(hundredOnAccountBalance);
    }

    @Test
    void given_a_fifty_withdrawal_on_empty_new_account_balance_should_be_minus_fifty() {
        String minusFiftyOnAccountBalance = "-50.0";
        Account account = new Account();
        Amount withdrawAmount = new Amount(50.0);
        account.withdraw(withdrawAmount);
        String balance = account.printBalance();
        assertThat(balance).isEqualTo(minusFiftyOnAccountBalance);
    }

    @Test
    void name() {
        String oneHundredDepositStatement =
                "  Date  \t|\tAmount\t|\tBalance\n" +
                "01/01/2019\t|\t100.0\t|\t100.0";
        String operationStatement = oneHundredDepositStatement + " ";
        assertThat(operationStatement).isEqualTo(oneHundredDepositStatement);
    }
}