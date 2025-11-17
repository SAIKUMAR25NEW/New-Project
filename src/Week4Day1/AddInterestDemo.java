package Week4Day1;



// Base class (Parent)
class BaseAccount {
    protected String accountNumber;
    protected String accountHolder;
    protected double balance;

    public BaseAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " into " + accountHolder + "'s account.");
        } else {
            System.out.println("Deposit amount must be greater than zero.");
        }
    }

    public void displayDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + balance);
        System.out.println("-----------------------------");
    }
}

// Derived class (Child) with interest calculation
class InterestAccount extends BaseAccount {
    private double interestRate; // percentage

    public InterestAccount(String accountNumber, String accountHolder, double balance, double interestRate) {
        super(accountNumber, accountHolder, balance);
        this.interestRate = interestRate;
    }

    // Method to add interest
    public void addInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        System.out.println("Interest of $" + interest + " added to " + accountHolder + "'s account.");
        System.out.println("New balance after interest: $" + balance);
    }

    @Override
    public void displayDetails() {
        System.out.println("Account Type: Interest Account");
        super.displayDetails();
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("-----------------------------");
    }
}

// Main class to test interest calculation
public class AddInterestDemo {
    public static void main(String[] args) {
        // Create an InterestAccount object
        InterestAccount acc1 = new InterestAccount("INT1001", "Alice", 5000.0, 4.5);

        System.out.println("--- Account Details Before Interest ---");
        acc1.displayDetails();

        // Add interest to account
        System.out.println("--- Adding Interest ---");
        acc1.addInterest();

        System.out.println("\n--- Account Details After Interest ---");
        acc1.displayDetails();
    }
}
