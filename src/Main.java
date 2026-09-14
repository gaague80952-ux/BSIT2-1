import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PaymentGateway gateway = new PaymentGateway();
        int nextId = 1001;

        gateway.add(new GCashPayment(nextId++, "Ana", 1500.00, "0917-555-0134"));
        gateway.add(new MayaPayment(nextId++, "Jerome", 899.50, "jerome@liceo.edu.ph"));
        gateway.add(new CashPayment(nextId++, "Liza", 250.00));

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("========================================");
            System.out.println("           LICEO PAY - CLI");
            System.out.println("========================================");
            System.out.println("1. Make a Payment");
            System.out.println("2. Print All Receipts");
            System.out.println("3. Find Payment by ID");
            System.out.println("4. Total Collected");
            System.out.println("5. Refund All Refundable Payments");
            System.out.println("6. Show Service Fees");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Payer name: ");
                    String name = scanner.nextLine().trim();

                    System.out.print("Amount: ");
                    double amount;
                    try {
                        amount = Double.parseDouble(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount. Payment cancelled.");
                        break;
                    }

                    System.out.print("Provider (GCASH / MAYA / CASH): ");
                    String provider = scanner.nextLine().trim().toUpperCase();

                    Payment newPayment;

                    if (provider.equals("GCASH")) {
                        System.out.print("Mobile number: ");
                        String mobile = scanner.nextLine().trim();
                        newPayment = new GCashPayment(nextId++, name, amount, mobile);
                    } else if (provider.equals("MAYA")) {
                        System.out.print("Email: ");
                        String email = scanner.nextLine().trim();
                        newPayment = new MayaPayment(nextId++, name, amount, email);
                    } else if (provider.equals("CASH")) {
                        newPayment = new CashPayment(nextId++, name, amount);
                    } else {
                        System.out.println("Unknown provider. Payment cancelled.");
                        break;
                    }

                    gateway.add(newPayment);
                    newPayment.printReceipt();
                    newPayment.printThankYou();
                    break;
                }

                case 2:
                    gateway.processAll();
                    break;

                case 3: {
                    System.out.print("Enter payment ID: ");
                    int searchId;
                    try {
                        searchId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid ID number.");
                        break;
                    }

                    Payment found = gateway.findById(searchId);
                    if (found == null) {
                        System.out.println("No payment found with ID " + searchId + ".");
                    } else {
                        System.out.println("Payment found:");
                        found.printReceipt();
                    }
                    break;
                }

                case 4:
                    System.out.printf("Total collected so far: PHP %.2f%n", gateway.totalCollected());
                    break;

                case 5:
                    System.out.println("Refunding every payment that can be refunded:");
                    gateway.refundAll();
                    break;

                case 6:
                    System.out.println("Service fees (the two serviceFee methods):");
                    gateway.showServiceFees();
                    break;

                case 0:
                    System.out.println("Thank you for using LICEO PAY. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 0-6.");
            }
        }

        scanner.close();
    }
}