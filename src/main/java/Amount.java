class Amount {

    private Double amount;

    public Amount() {
        this.amount = 0.0;
    }

    public Amount(double amount) {
        this.amount = amount;
    }

    public Amount(Amount otherAmount) {
        this.amount = otherAmount.amount;
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    public void add(Amount otherAmount) {
        amount += otherAmount.amount;
    }

    public void minus(Amount otherAmount) {
        amount -= otherAmount.amount;
    }
}
