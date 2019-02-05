class Account {

    public Amount balance = new Amount();

    public void deposit(Amount depositAmount) {
        balance.add(depositAmount);
    }

    public String printBalance() {
        return balance.toString();
    }

    public void withdraw(Amount withdrawAmount) {
        balance.minus(withdrawAmount);
    }
}
