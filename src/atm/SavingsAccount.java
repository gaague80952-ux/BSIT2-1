package atm;

public class SavingsAccount extends Account {

    public SavingsAccount(String accountNumber, String ownerName, double openingBalance) {
        super(accountNumber, ownerName, openingBalance);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}