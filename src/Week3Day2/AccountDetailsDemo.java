package Week3Day2;

public class AccountDetailsDemo {
    public static void main(String[] args) {
        // Create multiple accounts
        AccountInfo acc1 = new AccountInfo("Alice", "ACC1001", 1500.0);
        AccountInfo acc2 = new AccountInfo("Bob", "ACC1002", 2200.5);
        AccountInfo acc3 = new AccountInfo("Charlie", "ACC1003", 1800.75);

        // Display all account details
        System.out.println("--- Account Details ---");
        acc1.displayAccountDetails();
        acc2.displayAccountDetails();
        acc3.displayAccountDetails();
    }
}

class AccountInfo {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    // Constructor to initialize all fields
    public AccountInfo(String accountHolder, String accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Method to display all account details
    public void displayAccountDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + balance);
        System.out.println("-----------------------------");
    }
}
