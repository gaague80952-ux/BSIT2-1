package atm;

public class Main {
    public static void main(String[] args) {
        Account acc = new SavingsAccount("ACC-001", "Jane Doe", 100.0);
        System.out.println(acc);
        System.out.println("Balance: " + acc.getBalance());

        acc.deposit(50);
        System.out.println("After deposit: " + acc.getBalance());

        try {
            acc.withdraw(30);
            System.out.println("After withdraw: " + acc.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Withdraw failed: " + e.getMessage());
        }

        try {
            acc.withdraw(1000);
        } catch (InsufficientFundsException e) {
            System.out.println("Expected failure: " + e.getMessage());
        }

        try {
            new SavingsAccount("", "Bad Account", 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected validation error: " + e.getMessage());
        }

        try {
            new SavingsAccount("ACC-002", "Nobody", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected validation error: " + e.getMessage());
        }
    }
}
