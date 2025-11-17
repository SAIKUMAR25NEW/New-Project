package Week3Day3;

// Custom exception for invalid or failed operations
class OperationException extends Exception {
    public OperationException(String message) {
        super(message);
    }
}

public class TestExceptionHandlingDemo {
    public static void main(String[] args) {

        BankAccountV5 account = new BankAccountV5("Alice", 1000);

        // Simulate invalid deposit
        try {
            System.out.println("Simulating invalid deposit...");
            account.deposit(-200);
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Deposit operation completed.\n");
        }

        // Simulate insufficient funds withdrawal
        try {
            System.out.println("Simulating insufficient funds withdrawal...");
            account.withdraw(2000);
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Withdrawal operation completed.\n");
        }

        // Simulate valid transaction
        try {
            System.out.println("Simulating valid deposit and withdrawal...");
            account.deposit(500);
            account.withdraw(300);
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Transaction process completed.\n");
        }

        // Final account status
        System.out.println("Final Account Balance:");
        account.displayBalance();
    }
}

class BankAccountV5 {
    private String accountHolder;
    private double balance;

    public BankAccountV5(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public void deposit(double amount) throws InvalidOperationException {
        if (amount <= 0) {
            throw new InvalidOperationException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.println("Deposit successful: $" + amount);
    }

    public void withdraw(double amount) throws InvalidOperationException {
        if (amount <= 0) {
            throw new InvalidOperationException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new InvalidOperationException("Insufficient funds. Available balance: $" + balance);
        }
        balance -= amount;
        System.out.println("Withdrawal successful: $" + amount);
    }

    public void displayBalance() {
        System.out.println(accountHolder + "'s current balance: $" + balance);
    }
}
