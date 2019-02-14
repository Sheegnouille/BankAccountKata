package fr.lacombe.bankaccount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static fr.lacombe.bankaccount.Account.AccountBuilder.anAccount;
import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Mock
    DateProvider provider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void given_a_hundred_deposit_on_empty_new_account_balance_should_be_one_hundred() {
        Amount hundredAmount = new Amount(BigDecimal.valueOf(100));
        Account account = new Account(provider);
        account.deposit(hundredAmount);
        Account accountWithOneHundred = anAccount()
                .withProvider(provider)
                .withBalance(hundredAmount)
                .build();
        assertThat(account.printBalance()).isEqualTo(accountWithOneHundred.printBalance());
    }

    @Test
    void given_a_fifty_withdrawal_on_empty_new_account_balance_should_be_minus_fifty() {
        Account account = new Account(provider);
        Amount fiftyAmount = new Amount(BigDecimal.valueOf(50.0));
        account.withdraw(fiftyAmount);
        Amount minusFiftyAmount = new Amount(BigDecimal.valueOf(-50.0));
        Account accountWithFifty = anAccount()
                .withProvider(provider)
                .withBalance(minusFiftyAmount)
                .build();
        assertThat(account.printBalance()).isEqualTo(accountWithFifty.printBalance());
    }

    @Test
    void given_a_deposit_creates_an_operation() {
        Account account = new Account(provider);
        account.deposit(new Amount(BigDecimal.valueOf(100.0)));
        int oneOperationOnAccount = 1;
        Assertions.assertThat(account.operations.size()).isEqualTo(oneOperationOnAccount);
    }

    @Test
    void given_an_account_and_multiple_operations_should_return_the_correct_statement() {
        Mockito.when(provider.getDate()).thenReturn(new Date());
        String today = new SimpleDateFormat("dd/MM/yyyy").format(provider.getDate());
        Account account = new Account(provider);
        account.deposit(new Amount(BigDecimal.valueOf(100)));
        account.withdraw(new Amount(BigDecimal.valueOf(50)));
        account.deposit(new Amount(BigDecimal.valueOf(3000)));
        account.withdraw(new Amount(BigDecimal.valueOf(375)));
        account.withdraw(new Amount(BigDecimal.valueOf(22)));
        account.deposit(new Amount(BigDecimal.valueOf(93)));
        String accountStatement = "Date\tAmount\tBalance\n"
                + today + "\t100\t100\n"
                + today + "\t-50\t50\n"
                + today + "\t3000\t3050\n"
                + today + "\t-375\t2675\n"
                + today + "\t-22\t2653\n"
                + today + "\t93\t2746";
        Assertions.assertThat(account.printStatement()).isEqualTo(accountStatement);
    }
}