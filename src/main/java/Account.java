import java.util.ArrayList;
import java.util.List;

class Account {

    public Amount balance = new Amount();
    public List<Operation> operations = new ArrayList<Operation>();

    public void deposit(Amount depositAmount) {
        operations.add(new Operation(depositAmount));
        balance.add(depositAmount);
    }

    public String printBalance() {
        return balance.toString();
    }

    public void withdraw(Amount withdrawAmount) {
        balance.minus(withdrawAmount);
    }
}
