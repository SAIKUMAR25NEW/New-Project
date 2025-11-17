package Week3Day5;

import java.util.Scanner;

// Custom exception for invalid banking operations
class InvalidTransactionException extends Exception {
    public InvalidTransactionException(String message) {
        super(message);
    }
}

// Class representing a single bank account
class BankAccount {
    private String accountID;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountID, String accountHolder, double balance) {
        this.accountID = accountID;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    public void withdraw(double amount) throws InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new InvalidTransactionException("Insufficient funds. Available balance: $" + balance);
        }
        balance -= amount;
        System.out.println("Withdrawal successful. New balance: $" + balance);
    }

    public void transfer(BankAccount receiver, double amount) throws InvalidTransactionException {
        if (receiver == null) {
            throw new InvalidTransactionException("Receiver account not found.");
        }
        if (amount <= 0) {
            throw new InvalidTransactionException("Transfer amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new InvalidTransactionException("Insufficient funds for transfer.");
        }
        this.balance -= amount;
        receiver.balance += amount;
        System.out.println("Transfer successful. $" + amount + " transferred from " + accountHolder + " to " + receiver.accountHolder);
    }

    public void displayAccountDetails() {
        System.out.println("Account ID: " + accountID);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + balance);
        System.out.println("-------------------------------");
    }
}

// Main class for Banking System
public class BankingSystemSimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount[] accounts = new BankAccount[3];

        // Initialize some accounts
        accounts[0] = new BankAccount("ACC1001", "Alice", 2000);
        accounts[1] = new BankAccount("ACC1002", "Bob", 1500);
        accounts[2] = new BankAccount("ACC1003", "Charlie", 1800);

        int choice;
        do {
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Between Accounts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.println("\n--- Account Details ---");
                        for (BankAccount acc : accounts) {
                            acc.displayAccountDetails();
                        }
                        break;

                    case 2:
                        System.out.print("Enter Account ID: ");
                        String depID = sc.nextLine();
                        BankAccount depAcc = findAccount(accounts, depID);
                        if (depAcc != null) {
                            System.out.print("Enter amount to deposit: ");
                            double depAmt = sc.nextDouble();
                            depAcc.deposit(depAmt);
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Account ID: ");
                        String withID = sc.nextLine();
                        BankAccount withAcc = findAccount(accounts, withID);
                        if (withAcc != null) {
                            System.out.print("Enter amount to withdraw: ");
                            double withAmt = sc.nextDouble();
                            withAcc.withdraw(withAmt);
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Sender Account ID: ");
                        String senderID = sc.nextLine();
                        BankAccount sender = findAccount(accounts, senderID);

                        System.out.print("Enter Receiver Account ID: ");
                        String receiverID = sc.nextLine();
                        BankAccount receiver = findAccount(accounts, receiverID);

                        if (sender != null && receiver != null) {
                            System.out.print("Enter transfer amount: ");
                            double amt = sc.nextDouble();
                            sender.transfer(receiver, amt);
                        } else {
                            System.out.println("Invalid sender or receiver account.");
                        }
                        break;

                    case 5:
                        System.out.println("Exiting Banking System. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InvalidTransactionException e) {
                System.out.println("Transaction Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }

        } while (choice != 5);

        sc.close();
    }

    // Helper method to find an account by ID
    private static BankAccount findAccount(BankAccount[] accounts, String id) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountID().equalsIgnoreCase(id)) {
                return acc;
            }
        }
        return null;
    }
}

