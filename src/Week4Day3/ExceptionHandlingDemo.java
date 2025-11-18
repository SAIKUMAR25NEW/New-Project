package Week4Day3;
import java.util.Scanner;

// Custom Exception for insufficient funds
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// BankAccount class with basic operations
class SimpleBankAccount {
    private String accountNumber;
    private double balance;

    public SimpleBankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Deposit amount must be positive.");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException, Exception {
        if (amount <= 0) {
            throw new Exception("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Available balance: " + balance);
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

// Main class to test exception handling
public class ExceptionHandlingDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SimpleBankAccount account = new SimpleBankAccount("ACC101", 1000);

        try {
            System.out.print("Enter amount to withdraw: ");

            // Invalid input handling
            if (!sc.hasNextDouble()) {
                throw new Exception("Invalid input. Please enter a numeric value.");
            }

            double amount = sc.nextDouble();
            account.withdraw(amount);

            System.out.println("Withdrawal successful.");
            System.out.println("Remaining balance: " + account.getBalance());
        }
        catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            System.out.println("Transaction completed.");
            sc.close();
        }
    }
}
