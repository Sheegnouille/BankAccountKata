import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

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
    void given_a_deposit_creates_an_operation() {
        Account account = new Account();
        account.deposit(new Amount(100.0));
        int oneOperationOnAccount = 1;
        assertThat(account.operations.size()).isEqualTo(oneOperationOnAccount);
    }

    @Test
    void given_an_account_and_multiple_operations_should_return_the_correct_statement() {
        Account account = new Account();
        account.deposit(new Amount(100));
        account.withdraw(new Amount(50));
        account.deposit(new Amount(3000));
        account.withdraw(new Amount(375));
        account.withdraw(new Amount(22));
        account.deposit(new Amount(93));
        String accountStatement = "Date\tAmount\tBalance\n"
                                + today + "\t100.0\t100.0\n"
                                + today + "\t-50.0\t50.0\n"
                                + today + "\t3000.0\t3050.0\n"
                                + today + "\t-375.0\t2675.0\n"
                                + today + "\t-22.0\t2653.0\n"
                                + today + "\t93.0\t2746.0";
        assertThat(account.printStatement()).isEqualTo(accountStatement);
    }
}