package Week4Day1;


// Base class (Parent)
class BankAccountMain {
    protected String accountNumber;
    protected String accountHolder;
    protected double balance;

    public BankAccountMain(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Normal withdrawal rule
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance. Available balance: $" + balance);
        } else {
            balance -= amount;
            System.out.println(accountHolder + " withdrew $" + amount + ". New balance: $" + balance);
        }
    }

    public void displayDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + balance);
        System.out.println("-----------------------------");
    }
}

// Derived class (Child) with overridden withdraw method
class PremiumAccount extends BankAccountMain {
    private double transactionFee = 20.0;
    private double minimumBalance = 500.0;

    public PremiumAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    // Overridden withdraw method with special rules
    @Override
    public void withdraw(double amount) {
        System.out.println("Processing withdrawal for premium account...");
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
        } else if (amount + transactionFee > balance) {
            System.out.println("Insufficient funds after including transaction fee. Available balance: $" + balance);
        } else if ((balance - (amount + transactionFee)) < minimumBalance) {
            System.out.println("Cannot withdraw. Minimum balance of $" + minimumBalance + " must be maintained.");
        } else {
            balance -= (amount + transactionFee);
            System.out.println("Withdrawal of $" + amount + " successful with a $" + transactionFee + " fee.");
            System.out.println("Remaining balance: $" + balance);
        }
    }
}

// Main class to test overriding
public class OverrideMethodDemo {
    public static void main(String[] args) {
        // Base class object
        BankAccountMain normalAcc = new BankAccountMain("ACC1001", "Alice", 1000.0);
        // Derived class object
        PremiumAccount premiumAcc = new PremiumAccount("ACC2001", "Bob", 2000.0);

        System.out.println("--- Normal Account Withdrawal ---");
        normalAcc.displayDetails();
        normalAcc.withdraw(300);

        System.out.println("\n--- Premium Account Withdrawal ---");
        premiumAcc.displayDetails();
        premiumAcc.withdraw(1400); // should fail due to minimum balance rule
        premiumAcc.withdraw(1300); // should succeed
    }
}
