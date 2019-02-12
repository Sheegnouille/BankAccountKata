import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class OperationTest {

String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

    @Test
    void a_one_hundred_deposit_operation_should_print_a_statement_with_the_date_operation_amount_and_new_balance() {
        String operationStatement = today + "\t100.0\t100.0";
        Amount newBalance = new Amount(100);
        Operation operation = new Operation(new Amount(100), newBalance);
        assertThat(operation.printStatement()).isEqualTo(operationStatement);
    }

    @Test
    void name() {
        String operationStatement = today + "\t200.0\t200.0";
        Amount newBalance = new Amount(200);
        Operation operation = new Operation(new Amount(200), newBalance);
        assertThat(operation.printStatement()).isEqualTo(operationStatement);
    }
}