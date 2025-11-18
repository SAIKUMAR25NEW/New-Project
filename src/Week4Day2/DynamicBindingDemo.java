package Week4Day2;

// Parent class
class BaseAccount {
    protected String accountNumber;
    protected double balance;

    public BaseAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Method to calculate interest — overridden in subclasses
    public double calculateInterest() {
        return balance * 0.03; // Default 3% interest
    }

    public void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
    }
}

// Child class 1 — renamed from SavingsAccount to InterestAccount
class InterestAccount extends BaseAccount {
    public InterestAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public double calculateInterest() {
        return balance * 0.05; // 5% interest
    }
}

// Child class 2 — renamed from CheckingAccount to TransactionAccount
class TransactionAccount extends BaseAccount {
    public TransactionAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public double calculateInterest() {
        return balance * 0.02; // 2% interest
    }
}

// Main class demonstrating dynamic binding
public class DynamicBindingDemo {
    public static void main(String[] args) {

        // Parent class reference pointing to child objects
        BaseAccount acc1 = new InterestAccount("INT1001", 5000);
        BaseAccount acc2 = new TransactionAccount("TRN2001", 8000);
        BaseAccount acc3 = new BaseAccount("GEN3001", 6000);

        // Store all accounts in an array of parent type
        BaseAccount[] accounts = { acc1, acc2, acc3 };

        // Demonstrate dynamic method binding
        for (BaseAccount acc : accounts) {
            System.out.println("----------------------------");
            acc.displayDetails();
            System.out.println("Interest Earned: $" + acc.calculateInterest());
        }
    }
}
