import java.util.Scanner;

public class MiniATM {

    static double balance = 1000.00;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("        WELCOME TO THE MINI ATM");
        System.out.println("=========================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    deposit();
                    break;
                case "2":
                    withdraw();
                    break;
                case "3":
                    checkBalance();
                    break;
                case "4":
                    running = false;
                    System.out.println("\nThank you for using the Mini ATM. Goodbye!");
                    break;
                default:
                    System.out.println("\n[!] Please choose a number from 1 to 4.\n");
            }
        }

        sc.close();
    }

    static void printMenu() {
        System.out.println("  [1] Deposit   [2] Withdraw   [3] Check balance   [4] Exit");
        System.out.print("Enter your choice: ");
    }

    static void deposit() {
        System.out.print("Enter amount to deposit: ");
        String line = sc.nextLine().trim();

        try {
            double amount = Double.parseDouble(line);

            // amount cannot be 0 or negative
            if (amount <= 0) {
                throw new InvalidAmountException("Deposit amount must be greater than zero.");
            }

            balance = balance + amount;
            System.out.printf("Deposited PHP %.2f. New balance: PHP %.2f%n%n", amount, balance);

        } catch (NumberFormatException e) {
            System.out.println("[!] Please enter a valid number.");
        } catch (InvalidAmountException e) {
            System.out.println("[!] " + e.getMessage());
        } finally {
            System.out.println("-- transaction finished --\n");
        }
    }

    static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        String line = sc.nextLine().trim();

        try {
            double amount = Double.parseDouble(line);

            if (amount <= 0) {
                throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
            }

            // cannot withdraw more than what we have
            if (amount > balance) {
                double shortfall = amount - balance;
                throw new InsufficientFundsException(
                        String.format("Insufficient funds. You are short by PHP %.2f.", shortfall),
                        shortfall);
            }

            balance = balance - amount;
            System.out.printf("Withdrew PHP %.2f. New balance: PHP %.2f%n%n", amount, balance);

        } catch (NumberFormatException e) {
            System.out.println("[!] Please enter a valid number.");
        } catch (InvalidAmountException | InsufficientFundsException e) {
            // both cases just show the message so might as well combine them
            System.out.println("[!] " + e.getMessage());
        } finally {
            System.out.println("-- transaction finished --\n");
        }
    }

    static void checkBalance() {
        System.out.printf("%nYour current balance is: PHP %.2f%n%n", balance);
    }
}

class InsufficientFundsException extends Exception {

    private double shortfall;

    public InsufficientFundsException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}

class InvalidAmountException extends Exception {

    public InvalidAmountException(String message) {
        super(message);
    }
}