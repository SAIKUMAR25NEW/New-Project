package Week3Day2;

public class TransferValidationDemo {
    public static void main(String[] args) {
        BankAccountV2 acc1 = new BankAccountV2("Alice", 500);
        BankAccountV2 acc2 = new BankAccountV2("Bob", 1000);

        System.out.println("--- Initial Balances ---");
        acc1.displayBalance();
        acc2.displayBalance();

        System.out.println("\n--- Attempting transfer ---");
        acc1.transfer(acc2, 700);
        acc1.transfer(acc2, 200);

        System.out.println("\n--- Updated Balances ---");
        acc1.displayBalance();
        acc2.displayBalance();
    }
}

// Renamed class: BankAccountV2
class BankAccountV2 {
    private String accountHolder;
    private double balance;

    public BankAccountV2(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public void transfer(BankAccountV2 receiver, double amount) {
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Transfer failed! Insufficient funds in " + accountHolder + "'s account.");
        } else {
            balance -= amount;
            receiver.balance += amount;
            System.out.println("$" + amount + " transferred from " + accountHolder + " to " + receiver.accountHolder);
        }
    }

    public void displayBalance() {
        System.out.println(accountHolder + "'s current balance: $" + balance);
    }
}
