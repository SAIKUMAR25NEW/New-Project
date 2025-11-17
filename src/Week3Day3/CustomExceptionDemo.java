package Week3Day3;

// Custom exception class for invalid operations
class InvalidOperationException extends Exception {
    public InvalidOperationException(String message) {
        super(message);
    }
}

public class CustomExceptionDemo {
    public static void main(String[] args) {
        BankAccountV4 acc = new BankAccountV4("Alice", 1000);

        try {
            System.out.println("Trying to deposit -500...");
            acc.deposit(-500);  // Invalid deposit
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("\nTrying to withdraw 1500...");
            acc.withdraw(1500);  // Invalid withdrawal
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            System.out.println("\nTrying to withdraw 300...");
            acc.withdraw(300);  // Valid withdrawal
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nFinal Account Balance:");
        acc.displayBalance();
    }
}

class BankAccountV4 {
    private String accountHolder;
    private double balance;

    public BankAccountV4(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Deposit method with custom exception
    public void deposit(double amount) throws InvalidOperationException {
        if (amount <= 0) {
            throw new InvalidOperationException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.println("Deposit successful: $" + amount);
    }

    // Withdraw method with custom exception
    public void withdraw(double amount) throws InvalidOperationException {
        if (amount <= 0) {
            throw new InvalidOperationException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new InvalidOperationException("Insufficient funds. Current balance: $" + balance);
        }
        balance -= amount;
        System.out.println("Withdrawal successful: $" + amount);
    }

    // Display current balance
    public void displayBalance() {
        System.out.println(accountHolder + "'s current balance: $" + balance);
    }
}
