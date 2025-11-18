package Week4Day5;

import java.io.*;
import java.util.*;

// ---------------------- Custom Exception ----------------------
class BankingException extends Exception {
    public BankingException(String message) {
        super(message);
    }
}

// ---------------------- Account Class ----------------------
class EnhancedAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public EnhancedAccount(String accountNumber, String holderName, double balance) {
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

    public void deposit(double amount) throws BankingException {
        if (amount <= 0) {
            throw new BankingException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) {
            throw new BankingException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new BankingException("Insufficient funds. Current balance: " + balance);
        }
        balance -= amount;
    }

    public String toLine() {
        return accountNumber + "," + holderName + "," + balance;
    }

    public static EnhancedAccount fromLine(String line) {
        String[] p = line.split(",");
        return new EnhancedAccount(p[0], p[1], Double.parseDouble(p[2]));
    }
}

// ---------------------- File Persistence ----------------------
class EnhancedFileManager {
    private final String fileName;

    public EnhancedFileManager(String fileName) {
        this.fileName = fileName;
    }

    public void save(List<EnhancedAccount> accounts) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for (EnhancedAccount acc : accounts) {
            bw.write(acc.toLine());
            bw.newLine();
        }
        bw.close();
    }

    public List<EnhancedAccount> load() throws IOException {
        List<EnhancedAccount> list = new ArrayList<>();

        File file = new File(fileName);
        if (!file.exists()) return list;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            list.add(EnhancedAccount.fromLine(line));
        }

        br.close();
        return list;
    }
}

// ---------------------- Main System ----------------------
public class EnhancedBankingSystem {

    private static EnhancedAccount find(List<EnhancedAccount> list, String accNo) {
        for (EnhancedAccount acc : list) {
            if (acc.getAccountNumber().equalsIgnoreCase(accNo)) return acc;
        }
        return null;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EnhancedFileManager file = new EnhancedFileManager("bankdata.txt");

        List<EnhancedAccount> accounts = new ArrayList<>();

        try {
            accounts = file.load();
            System.out.println("Loaded " + accounts.size() + " accounts from file.");
        } catch (Exception e) {
            System.out.println("No previous data loaded.");
        }

        int choice;

        do {
            System.out.println("\n=== ENHANCED BANKING SYSTEM ===");
            System.out.println("1. Create Account");
            System.out.println("2. List Accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Save Accounts to File");
            System.out.println("7. Load Accounts from File");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                sc.next();
                System.out.print("Enter a valid number: ");
            }
            choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {

                    case 1:
                        System.out.print("Enter account number: ");
                        String num = sc.nextLine();

                        System.out.print("Enter holder name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter initial balance: ");
                        double bal = sc.nextDouble();
                        sc.nextLine();

                        accounts.add(new EnhancedAccount(num, name, bal));
                        System.out.println("Account created successfully.");
                        break;

                    case 2:
                        for (EnhancedAccount a : accounts) {
                            System.out.println(a.getAccountNumber() + " | " +
                                    a.getHolderName() + " | " + a.getBalance());
                        }
                        break;

                    case 3:
                        System.out.print("Enter account number: ");
                        String dnum = sc.nextLine();
                        EnhancedAccount dacc = find(accounts, dnum);
                        if (dacc == null) throw new BankingException("Account not found.");

                        System.out.print("Enter deposit amount: ");
                        double damt = sc.nextDouble();
                        sc.nextLine();

                        dacc.deposit(damt);
                        System.out.println("Deposit successful. New balance: " + dacc.getBalance());
                        break;

                    case 4:
                        System.out.print("Enter account number: ");
                        String wnum = sc.nextLine();
                        EnhancedAccount wacc = find(accounts, wnum);
                        if (wacc == null) throw new BankingException("Account not found.");

                        System.out.print("Enter withdrawal amount: ");
                        double wamt = sc.nextDouble();
                        sc.nextLine();

                        wacc.withdraw(wamt);
                        System.out.println("Withdrawal successful. New balance: " + wacc.getBalance());
                        break;

                    case 5:
                        System.out.print("Enter sender account number: ");
                        String s1 = sc.nextLine();
                        EnhancedAccount sender = find(accounts, s1);
                        if (sender == null) throw new BankingException("Sender account not found.");

                        System.out.print("Enter receiver account number: ");
                        String s2 = sc.nextLine();
                        EnhancedAccount receiver = find(accounts, s2);
                        if (receiver == null) throw new BankingException("Receiver account not found.");

                        System.out.print("Enter amount to transfer: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();

                        sender.withdraw(amt);
                        receiver.deposit(amt);

                        System.out.println("Transfer completed.");
                        System.out.println("Sender balance: " + sender.getBalance());
                        System.out.println("Receiver balance: " + receiver.getBalance());
                        break;

                    case 6:
                        file.save(accounts);
                        System.out.println("Accounts saved to file.");
                        break;

                    case 7:
                        accounts = file.load();
                        System.out.println("Accounts loaded from file.");
                        break;

                    case 8:
                        System.out.println("Exiting system.");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (BankingException be) {
                System.out.println("Error: " + be.getMessage());
            } catch (IOException ioe) {
                System.out.println("File Error: " + ioe.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }

        } while (choice != 8);

        sc.close();
    }
}
