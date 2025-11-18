package Week4Day2;

// Parent class
class AccountBase {
    protected String accountNumber;
    protected double balance;

    public AccountBase(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Method to be overridden
    public void showAccountType() {
        System.out.println("This is a generic bank account.");
    }

    public double calculateInterest() {
        return balance * 0.03; // Default 3% interest
    }

    public void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
    }
}

// Child class 1 - InterestAccountTest
class InterestAccountTest extends AccountBase {
    public InterestAccountTest(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void showAccountType() {
        System.out.println("This is an Interest Account.");
    }

    @Override
    public double calculateInterest() {
        return balance * 0.05; // 5% interest
    }
}

// Child class 2 - TransactionAccountTest
class TransactionAccountTest extends AccountBase {
    public TransactionAccountTest(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void showAccountType() {
        System.out.println("This is a Transaction Account.");
    }

    @Override
    public double calculateInterest() {
        return balance * 0.02; // 2% interest
    }
}

// Main class to test polymorphism
public class TestPolymorphismDemo {
    public static void main(String[] args) {

        // Parent class reference pointing to child class objects
        AccountBase acc1 = new InterestAccountTest("INT9001", 5000);
        AccountBase acc2 = new TransactionAccountTest("TRN9002", 8000);
        AccountBase acc3 = new AccountBase("GEN9003", 4000);

        // Array of parent type holding different account objects
        AccountBase[] accounts = { acc1, acc2, acc3 };

        // Calling overridden methods polymorphically
        for (AccountBase acc : accounts) {
            System.out.println("----------------------------");
            acc.showAccountType();               // Calls overridden version
            acc.displayDetails();                // Common method
            System.out.println("Interest: $" + acc.calculateInterest()); // Overridden method
        }
    }
}

