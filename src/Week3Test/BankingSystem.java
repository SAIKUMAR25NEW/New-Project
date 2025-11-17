package Week3Test;


import java.util.Scanner;

// Custom exception for invalid transactions
class InvalidTransactionException extends Exception {
    public InvalidTransactionException(String message) {
        super(message);
    }
}

// Base class
class BaseAccount {
    protected String accountNumber;
    protected String accountHolder;
    protected double balance;

    public BaseAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public void deposit(double amount) throws InvalidTransactionException {
        if (amount <= 0)
            throw new InvalidTransactionException("Deposit amount must be greater than zero.");
        balance += amount;
        System.out.println("Deposited $" + amount + " successfully. Current balance: $" + balance);
    }

    public void withdraw(double amount) throws InvalidTransactionException {
        if (amount <= 0)
            throw new InvalidTransactionException("Withdrawal amount must be greater than zero.");
        if (amount > balance)
            throw new InvalidTransactionException("Insufficient balance. Current balance: $" + balance);
        balance -= amount;
        System.out.println("Withdrew $" + amount + " successfully. Current balance: $" + balance);
    }

    public void displayDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
        System.out.println("-----------------------------");
    }
}

// Derived class with interest feature
class InterestAccount extends BaseAccount {
    private double interestRate;

    public InterestAccount(String accountNumber, String accountHolder, double balance, double interestRate) {
        super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        System.out.println("Interest of $" + interest + " added successfully. New balance: $" + balance);
    }

    @Override
    public void displayDetails() {
        System.out.println("Account Type: Interest Account");
        super.displayDetails();
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("-----------------------------");
    }
}

// Main class for system management
public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BaseAccount[] accounts = new BaseAccount[5];
        int accountCount = 0;
        int choice;

        do {
            System.out.println("\n=== BANKING SYSTEM MENU ===");
            System.out.println("1. Create Account");
            System.out.println("2. Display All Accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Add Interest (Interest Accounts only)");
            System.out.println("6. Transfer Between Accounts");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        if (accountCount >= accounts.length) {
                            System.out.println("Maximum account limit reached.");
                            break;
                        }
                        System.out.print("Enter Account Type (1 for Normal / 2 for Interest): ");
                        int type = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Account Holder Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Account Number: ");
                        String accNo = sc.nextLine();
                        System.out.print("Enter Initial Balance: ");
                        double balance = sc.nextDouble();

                        if (type == 1) {
                            accounts[accountCount++] = new BaseAccount(accNo, name, balance);
                        } else if (type == 2) {
                            System.out.print("Enter Interest Rate: ");
                            double rate = sc.nextDouble();
                            accounts[accountCount++] = new InterestAccount(accNo, name, balance, rate);
                        } else {
                            System.out.println("Invalid type. Try again.");
                        }
                        break;

                    case 2:
                        System.out.println("\n--- All Accounts ---");
                        for (int i = 0; i < accountCount; i++) {
                            accounts[i].displayDetails();
                        }
                        break;

                    case 3:
                        System.out.print("Enter Account Number: ");
                        String depNo = sc.nextLine();
                        BaseAccount depAcc = findAccount(accounts, depNo, accountCount);
                        if (depAcc != null) {
                            System.out.print("Enter amount to deposit: ");
                            double amt = sc.nextDouble();
                            depAcc.deposit(amt);
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Account Number: ");
                        String withNo = sc.nextLine();
                        BaseAccount withAcc = findAccount(accounts, withNo, accountCount);
                        if (withAcc != null) {
                            System.out.print("Enter amount to withdraw: ");
                            double amt = sc.nextDouble();
                            withAcc.withdraw(amt);
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;

                    case 5:
                        System.out.print("Enter Account Number: ");
                        String intNo = sc.nextLine();
                        BaseAccount intAcc = findAccount(accounts, intNo, accountCount);
                        if (intAcc != null && intAcc instanceof InterestAccount) {
                            ((InterestAccount) intAcc).addInterest();
                        } else {
                            System.out.println("Either account not found or not an Interest Account.");
                        }
                        break;

                    case 6:
                        System.out.print("Enter Sender Account Number: ");
                        String fromNo = sc.nextLine();
                        BaseAccount sender = findAccount(accounts, fromNo, accountCount);

                        System.out.print("Enter Receiver Account Number: ");
                        String toNo = sc.nextLine();
                        BaseAccount receiver = findAccount(accounts, toNo, accountCount);

                        if (sender != null && receiver != null) {
                            System.out.print("Enter amount to transfer: ");
                            double amt = sc.nextDouble();

                            sender.withdraw(amt);
                            receiver.deposit(amt);
                            System.out.println("Transfer successful.");
                        } else {
                            System.out.println("Invalid account numbers.");
                        }
                        break;

                    case 7:
                        System.out.println("Exiting Banking System. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InvalidTransactionException e) {
                System.out.println("Transaction Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
                sc.nextLine(); // clear input buffer
            }

        } while (choice != 7);

        sc.close();
    }

    // Helper method to find an account by account number
    private static BaseAccount findAccount(BaseAccount[] accounts, String accNo, int count) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].accountNumber.equalsIgnoreCase(accNo))
                return accounts[i];
        }
        return null;
    }
}

