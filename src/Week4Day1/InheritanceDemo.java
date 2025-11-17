package Week4Day1;

// Base class (Parent)
class BankAccountBase {
    protected String accountNumber;
    protected String accountHolder;
    protected double balance;

    public BankAccountBase(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " into " + accountHolder + "'s account.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from " + accountHolder + "'s account.");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void displayAccountDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
        System.out.println("-----------------------------");
    }
}

// Derived class 1 (Child)
class SavingsAccount extends BankAccountBase {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolder, double balance, double interestRate) {
        super(accountNumber, accountHolder, balance); // Call parent constructor
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest of $" + interest + " added to " + accountHolder + "'s savings account.");
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Account Type: Savings Account");
        super.displayAccountDetails();
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("-----------------------------");
    }
}

// Derived class 2 (Child)
class CheckingAccount extends BankAccountBase {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String accountHolder, double balance, double overdraftLimit) {
        super(accountNumber, accountHolder, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= (balance + overdraftLimit)) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from " + accountHolder + "'s checking account.");
        } else {
            System.out.println("Withdrawal exceeds overdraft limit.");
        }
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Account Type: Checking Account");
        super.displayAccountDetails();
        System.out.println("Overdraft Limit: $" + overdraftLimit);
        System.out.println("-----------------------------");
    }
}

// Main class to test inheritance
public class InheritanceDemo {
    public static void main(String[] args) {
        // Create objects for each account type
        SavingsAccount savings = new SavingsAccount("SAV1001", "Alice", 3000.0, 5.0);
        CheckingAccount checking = new CheckingAccount("CHK2001", "Bob", 1500.0, 500.0);

        // Display account details
        System.out.println("--- Account Details ---");
        savings.displayAccountDetails();
        checking.displayAccountDetails();

        // Perform transactions
        System.out.println("--- Transactions ---");
        savings.deposit(500);
        savings.addInterest();
        checking.withdraw(1800);
        checking.deposit(200);

        // Updated details
        System.out.println("\n--- Updated Account Details ---");
        savings.displayAccountDetails();
        checking.displayAccountDetails();
    }
}

