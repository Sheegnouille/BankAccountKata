import java.util.ArrayList;
import java.util.List;

class Account {

    public Amount balance = new Amount();
    public List<Operation> operations = new ArrayList<Operation>();

    public void deposit(Amount depositAmount) {
        balance.add(depositAmount);
        operations.add(new Operation(depositAmount, balance));
    }

    public String printBalance() {
        return balance.toString();
    }

    public void withdraw(Amount withdrawAmount) {
        balance.minus(withdrawAmount);
        Amount operationAmount = new Amount(0);
        operationAmount.minus(withdrawAmount);
        operations.add(new Operation(operationAmount, balance));
    }

    public String printStatement() {
        String statement = "Date\tAmount\tBalance";
        for (Operation operation: operations
             ) {
            statement += "\n" + operation.printStatement();
        }
        return statement;
    }
}
