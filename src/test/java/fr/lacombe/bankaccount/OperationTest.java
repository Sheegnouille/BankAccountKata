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

class OperationTest {

    @Mock
    DateProvider provider;

    private String today;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void a_one_hundred_deposit_operation_should_print_a_statement_with_the_date_operation_amount_and_new_balance() {
        Mockito.when(provider.getDate()).thenReturn(new Date());
        today = new SimpleDateFormat("dd/MM/yyyy").format(provider.getDate());
        String operationStatement = today + "\t100\t100";
        Amount newBalance = new Amount(BigDecimal.valueOf(100));
        Operation operation = new Operation(provider.getDate(), new Amount(BigDecimal.valueOf(100)), newBalance);
        Assertions.assertThat(operation.printStatement()).isEqualTo(operationStatement);
    }

    @Test
    void a_one_hundred_withdrawal_operation_should_print_a_statement_with_the_date_operation_amount_and_new_balance() {
        Mockito.when(provider.getDate()).thenReturn(new Date());
        today = new SimpleDateFormat("dd/MM/yyyy").format(provider.getDate());
        String operationStatement = today + "\t200\t200";
        Amount newBalance = new Amount(BigDecimal.valueOf(200));
        Operation operation = new Operation(provider.getDate(), new Amount(BigDecimal.valueOf(200)), newBalance);
        Assertions.assertThat(operation.printStatement()).isEqualTo(operationStatement);
    }
}