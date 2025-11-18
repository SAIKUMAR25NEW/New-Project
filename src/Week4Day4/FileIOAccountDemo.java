package Week4Day4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Simple Account class
class FileAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public FileAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    // Convert to a single line text format
    public String toLine() {
        return accountNumber + "," + holderName + "," + balance;
    }

    // Convert from line to FileAccount object
    public static FileAccount fromLine(String line) {
        String[] parts = line.split(",");
        String acc = parts[0];
        String name = parts[1];
        double bal = Double.parseDouble(parts[2]);
        return new FileAccount(acc, name, bal);
    }
}

// Handles saving/loading
class AccountFileIO {
    private final String fileName;

    public AccountFileIO(String fileName) {
        this.fileName = fileName;
    }

    // Save a list of accounts to file
    public void saveAccounts(List<FileAccount> accounts) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

        for (FileAccount acc : accounts) {
            bw.write(acc.toLine());
            bw.newLine();
        }

        bw.close();
    }

    // Load accounts from file
    public List<FileAccount> loadAccounts() throws IOException {
        List<FileAccount> accounts = new ArrayList<>();

        File file = new File(fileName);
        if (!file.exists()) {
            return accounts;  // return empty list if file doesn't exist
        }

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            FileAccount acc = FileAccount.fromLine(line);
            accounts.add(acc);
        }

        br.close();
        return accounts;
    }
}

// Demo class with main() method
public class FileIOAccountDemo {
    public static void main(String[] args) {

        AccountFileIO fileIO = new AccountFileIO("accounts.txt");

        // Create sample accounts
        List<FileAccount> accounts = new ArrayList<>();
        accounts.add(new FileAccount("A101", "Alice", 5000));
        accounts.add(new FileAccount("A102", "Bob", 3000));
        accounts.add(new FileAccount("A103", "Charlie", 8000));

        try {
            // Save accounts to file
            fileIO.saveAccounts(accounts);
            System.out.println("Accounts saved to accounts.txt");

            // Load accounts again
            List<FileAccount> loaded = fileIO.loadAccounts();

            System.out.println("\nLoaded Accounts:");
            for (FileAccount acc : loaded) {
                System.out.println(acc.getAccountNumber() + " | "
                        + acc.getHolderName() + " | "
                        + acc.getBalance());
            }

        } catch (IOException e) {
            System.out.println("File I/O Error: " + e.getMessage());
        }
    }
}
