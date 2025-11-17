package Week3Day3;

// Custom exception for insufficient funds
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

public class ExceptionHandlingDemo {
    public static void main(String[] args) {
        BankAccountV3 acc1 = new BankAccountV3("Alice", 500);

        try {
            System.out.println("Attempting to withdraw $700...");
            acc1.withdraw(700); // This will throw an exception
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Transaction attempt completed.");
            acc1.displayBalance();
        }

        System.out.println("\nAttempting to withdraw $300...");
        try {
            acc1.withdraw(300); // This should succeed
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Transaction attempt completed.");
            acc1.displayBalance();
        }
    }
}

class BankAccountV3 {
    private String accountHolder;
    private double balance;

    public BankAccountV3(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Withdraw method that throws a custom exception
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new InsufficientFundsException("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Available balance: $" + balance);
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. Amount withdrawn: $" + amount);
        }
    }

    // Display balance
    public void displayBalance() {
        System.out.println(accountHolder + "'s current balance: $" + balance);
    }
}
