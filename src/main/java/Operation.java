import java.text.SimpleDateFormat;
import java.util.Date;

class Operation {

    private final Date date;
    private final Amount amount;
    private final Amount balanceAfterOperation;

    public Operation(Amount operationAmount, Amount balanceAfterOperation) {

        this.amount = operationAmount;
        this.balanceAfterOperation = new Amount(balanceAfterOperation);
        date = new Date();
    }

    public String printStatement() {
        return new SimpleDateFormat("dd/MM/yyyy").format(date) +
                "\t" + amount.toString() +
                "\t" + balanceAfterOperation.toString();
    }
}
