package atm;

public class InsufficientFundsException extends Exception {

    private final double shortfall;

    public InsufficientFundsException(double shortfall) {
        super("Insufficient funds: short by " + shortfall);
        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}