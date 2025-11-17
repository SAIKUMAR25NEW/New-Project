package Week3Day4;

public class IterateAccountsDemo {
    public static void main(String[] args) {
        // Create an array of Account objects
        AccountDetails[] accounts = new AccountDetails[3];

        // Initialize the array with Account objects
        accounts[0] = new AccountDetails("Alice", "ACC1001", 1500.0);
        accounts[1] = new AccountDetails("Bob", "ACC1002", 2200.5);
        accounts[2] = new AccountDetails("Charlie", "ACC1003", 1800.75);

        // Iterate through the array using a for loop
        System.out.println("--- Iterating Accounts using for loop ---");
        for (int i = 0; i < accounts.length; i++) {
            accounts[i].displayAccount();
        }

        // Iterate through the array using a for-each loop
        System.out.println("\n--- Iterating Accounts using for-each loop ---");
        for (AccountDetails account : accounts) {
            account.displayAccount();
        }
    }
}

class AccountDetails {
    private String accountHolder;
    private String accountNumber;
    private double balance;

    // Constructor
    public AccountDetails(String accountHolder, String accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Method to display account details
    public void displayAccount() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
        System.out.println("-----------------------------");
    }
}

