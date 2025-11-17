package Week3Day4;

public class ArrayOfAccountsDemo {
    public static void main(String[] args) {
        // Create an array to store multiple Account objects
        Account[] accounts = new Account[3];

        // Initialize each account in the array
        accounts[0] = new Account("Alice", "ACC1001", 1500.0);
        accounts[1] = new Account("Bob", "ACC1002", 2000.0);
        accounts[2] = new Account("Charlie", "ACC1003", 1200.5);

        // Display all account details using a loop
        System.out.println("--- Account Details ---");
        for (int i = 0; i < accounts.length; i++) {
            accounts[i].displayAccountDetails();
        }
    }
}

class Account {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    // Constructor
    public Account(String accountHolder, String accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Method to display account details
    public void displayAccountDetails() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
        System.out.println("-----------------------------");
    }
}

