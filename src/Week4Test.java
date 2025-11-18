
import java.io.*;
import java.nio.file.*;
import java.util.*;



public class Week4Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountRepository repo = new AccountRepository("accounts.csv");

        // Load accounts if file exists
        List<CoreAccount> accounts = new ArrayList<>();
        try {
            accounts = repo.load();
            System.out.println("Loaded " + accounts.size() + " account(s) from accounts.csv");
        } catch (IOException e) {
            System.out.println("No existing data or failed to load: " + e.getMessage());
        }

        int choice;
        do {
            System.out.println("\n=== ROBUST BANKING SYSTEM ===");
            System.out.println("1. Create Account");
            System.out.println("2. List Accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Apply Monthly Interest (all accounts)");
            System.out.println("7. Save to File");
            System.out.println("8. Reload from File");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            while (!sc.hasNextInt()) { sc.next(); System.out.print("Enter a number: "); }
            choice = sc.nextInt(); sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        createAccount(sc, accounts);
                        break;
                    case 2:
                        listAccounts(accounts);
                        break;
                    case 3:
                        deposit(sc, accounts);
                        break;
                    case 4:
                        withdraw(sc, accounts);
                        break;
                    case 5:
                        transfer(sc, accounts);
                        break;
                    case 6:
                        applyMonthlyInterest(accounts);
                        break;
                    case 7:
                        repo.save(accounts);
                        System.out.println("Saved " + accounts.size() + " account(s) to accounts.csv");
                        break;
                    case 8:
                        accounts = repo.load();
                        System.out.println("Reloaded " + accounts.size() + " account(s) from accounts.csv");
                        break;
                    case 9:
                        System.out.println("Goodbye.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (AccountException ae) {
                System.out.println("Transaction Error: " + ae.getMessage());
            } catch (IOException io) {
                System.out.println("I/O Error: " + io.getMessage());
            } catch (Exception ex) {
                System.out.println("Unexpected Error: " + ex.getMessage());
            }
        } while (choice != 9);

        sc.close();
    }



    private static void createAccount(Scanner sc, List<CoreAccount> accounts) throws AccountException {
        System.out.print("Enter account type (1 = InterestAccountPro, 2 = TransactionAccountPro): ");
        int type = readInt(sc);

        System.out.print("Enter account number: ");
        String number = sc.nextLine().trim();
        if (find(accounts, number) != null) throw new AccountException("Account already exists with number: " + number);

        System.out.print("Enter account holder: ");
        String holder = sc.nextLine().trim();

        System.out.print("Enter initial balance: ");
        double balance = readDouble(sc);

        CoreAccount acc;
        if (type == 1) {
            System.out.print("Enter annual interest rate (percent, e.g., 5 for 5%): ");
            double rate = readDouble(sc);
            acc = new InterestAccountPro(number, holder, balance, rate);
        } else if (type == 2) {
            System.out.print("Enter overdraft limit for transaction account: ");
            double overdraft = readDouble(sc);
            acc = new TransactionAccountPro(number, holder, balance, overdraft);
        } else {
            throw new AccountException("Unknown account type.");
        }

        accounts.add(acc);
        System.out.println("Account created: " + acc.summary());
    }

    private static void listAccounts(List<CoreAccount> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts.");
            return;
        }
        for (CoreAccount acc : accounts) {
            System.out.println(acc.details());
        }
    }

    private static void deposit(Scanner sc, List<CoreAccount> accounts) throws AccountException {
        System.out.print("Enter account number: ");
        String number = sc.nextLine().trim();
        CoreAccount acc = require(accounts, number);

        System.out.print("Enter deposit amount: ");
        double amt = readDouble(sc);
        acc.deposit(amt);
        System.out.println("New balance: " + acc.getBalance());
    }

    private static void withdraw(Scanner sc, List<CoreAccount> accounts) throws AccountException {
        System.out.print("Enter account number: ");
        String number = sc.nextLine().trim();
        CoreAccount acc = require(accounts, number);

        System.out.print("Enter withdrawal amount: ");
        double amt = readDouble(sc);
        acc.withdraw(amt);
        System.out.println("New balance: " + acc.getBalance());
    }

    private static void transfer(Scanner sc, List<CoreAccount> accounts) throws AccountException {
        System.out.print("Enter sender account number: ");
        String from = sc.nextLine().trim();
        CoreAccount a = require(accounts, from);

        System.out.print("Enter receiver account number: ");
        String to = sc.nextLine().trim();
        CoreAccount b = require(accounts, to);

        System.out.print("Enter transfer amount: ");
        double amt = readDouble(sc);

        a.withdraw(amt);
        b.deposit(amt);
        System.out.println("Transfer complete.");
        System.out.println("Sender balance: " + a.getBalance());
        System.out.println("Receiver balance: " + b.getBalance());
    }

    private static void applyMonthlyInterest(List<CoreAccount> accounts) {
        int count = 0;
        for (CoreAccount acc : accounts) {
            double added = acc.applyMonthlyInterest();
            if (added > 0) {
                System.out.println("Applied " + added + " to " + acc.getAccountNumber() + " (" + acc.getAccountType() + ")");
                count++;
            }
        }
        if (count == 0) System.out.println("No interest-bearing accounts or no interest applied.");
    }



    private static CoreAccount find(List<CoreAccount> accounts, String number) {
        for (CoreAccount a : accounts) if (a.getAccountNumber().equalsIgnoreCase(number)) return a;
        return null;
    }

    private static CoreAccount require(List<CoreAccount> accounts, String number) throws AccountException {
        CoreAccount acc = find(accounts, number);
        if (acc == null) throw new AccountException("Account not found: " + number);
        return acc;
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) { sc.next(); System.out.print("Enter a valid integer: "); }
        int v = sc.nextInt(); sc.nextLine();
        return v;
    }

    private static double readDouble(Scanner sc) {
        while (!sc.hasNextDouble()) { sc.next(); System.out.print("Enter a valid number: "); }
        double v = sc.nextDouble(); sc.nextLine();
        return v;
    }
}



abstract class CoreAccount {
    protected final String accountNumber;
    protected final String accountHolder;
    protected double balance;

    protected CoreAccount(String accountNumber, String accountHolder, double balance) {
        if (accountNumber == null || accountNumber.isBlank()) throw new IllegalArgumentException("accountNumber required");
        if (accountHolder == null || accountHolder.isBlank()) throw new IllegalArgumentException("accountHolder required");
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountType() { return getClass().getSimpleName(); }
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }

    public void deposit(double amount) throws AccountException {
        if (amount <= 0) throw new AccountException("Deposit must be > 0");
        balance += amount;
    }

    public void withdraw(double amount) throws AccountException {
        if (amount <= 0) throw new AccountException("Withdrawal must be > 0");
        if (amount > balance) throw new AccountException("Insufficient funds");
        balance -= amount;
    }

    public abstract double yearlyInterest();

    public double applyMonthlyInterest() {
        // default: monthly portion of yearly interest; subclasses can override yearlyInterest
        double monthly = yearlyInterest() / 12.0;
        if (monthly > 0) balance += monthly;
        return monthly;
    }

    // For display
    public String summary() {
        return "[" + getAccountType() + "] " + accountNumber + " | " + accountHolder + " | balance=" + balance;
    }

    public String details() {
        return "Type: " + getAccountType() + "\nAccount: " + accountNumber + "\nHolder: " + accountHolder + "\nBalance: " + balance + "\n";
    }

    // CSV persistence
    public String toCsv() {
        // type,number,holder,balance,extra
        return String.join(",",
                escape(getAccountType()),
                escape(accountNumber),
                escape(accountHolder),
                String.valueOf(balance),
                extraCsv());
    }

    protected String extraCsv() { return ""; }

    protected static String escape(String v) {
        if (v.contains(",") || v.contains("\"")) {
            return "\"" + v.replace("\"", "\"\"") + "\"";
        }
        return v;
    }

    public static CoreAccount fromCsv(String line) throws AccountException {
        List<String> parts = parseCsv(line);
        if (parts.size() < 4) throw new AccountException("Invalid CSV line: " + line);
        String type = parts.get(0);
        String number = parts.get(1);
        String holder = parts.get(2);
        double bal = Double.parseDouble(parts.get(3));
        if ("InterestAccountPro".equals(type)) {
            double rate = parts.size() >= 5 ? Double.parseDouble(parts.get(4)) : 5.0;
            return new InterestAccountPro(number, holder, bal, rate);
        } else if ("TransactionAccountPro".equals(type)) {
            double od = parts.size() >= 5 ? Double.parseDouble(parts.get(4)) : 0.0;
            return new TransactionAccountPro(number, holder, bal, od);
        } else if ("CoreAccount".equals(type) || "Base".equalsIgnoreCase(type)) {
            // fallback to a non-interest basic account implemented as BaseCoreAccount
            return new BaseCoreAccount(number, holder, bal);
        } else {
            // Unknown type; treat as basic
            return new BaseCoreAccount(number, holder, bal);
        }
    }

    private static List<String> parseCsv(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQuotes) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') { // escaped quote
                        sb.append('"'); i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    sb.append(c);
                }
            } else {
                if (c == ',') {
                    out.add(sb.toString()); sb.setLength(0);
                } else if (c == '"') {
                    inQuotes = true;
                } else {
                    sb.append(c);
                }
            }
        }
        out.add(sb.toString());
        return out;
    }
}

// Basic non-interest account implementation for fallback/loading
class BaseCoreAccount extends CoreAccount {
    public BaseCoreAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }
    @Override public double yearlyInterest() { return 0.0; }
    @Override public String getAccountType() { return "CoreAccount"; }
}

// Interest-bearing account
class InterestAccountPro extends CoreAccount {
    private final double annualRatePercent; // e.g., 5.0 for 5%

    public InterestAccountPro(String accountNumber, String accountHolder, double balance, double annualRatePercent) {
        super(accountNumber, accountHolder, balance);
        if (annualRatePercent < 0) throw new IllegalArgumentException("Rate must be >= 0");
        this.annualRatePercent = annualRatePercent;
    }

    @Override
    public double yearlyInterest() {
        return balance * (annualRatePercent / 100.0);
    }

    @Override
    public String getAccountType() {
        return "InterestAccountPro";
    }

    @Override
    protected String extraCsv() {
        return String.valueOf(annualRatePercent);
    }

    @Override
    public String details() {
        return super.details() + "Annual Rate: " + annualRatePercent + "%\n";
    }
}

// Transaction/overdraft-enabled account
class TransactionAccountPro extends CoreAccount {
    private final double overdraftLimit;

    public TransactionAccountPro(String accountNumber, String accountHolder, double balance, double overdraftLimit) {
        super(accountNumber, accountHolder, balance);
        if (overdraftLimit < 0) throw new IllegalArgumentException("Overdraft must be >= 0");
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws AccountException {
        if (amount <= 0) throw new AccountException("Withdrawal must be > 0");
        if (amount > balance + overdraftLimit) throw new AccountException("Exceeds overdraft limit");
        balance -= amount;
    }

    @Override
    public double yearlyInterest() {
        // No interest credited by default for transaction accounts
        return 0.0;
    }

    @Override
    public String getAccountType() {
        return "TransactionAccountPro";
    }

    @Override
    protected String extraCsv() {
        return String.valueOf(overdraftLimit);
    }

    @Override
    public String details() {
        return super.details() + "Overdraft Limit: " + overdraftLimit + "\n";
    }
}



class AccountRepository {
    private final Path filePath;

    public AccountRepository(String fileName) {
        this.filePath = Paths.get(fileName);
    }

    public List<CoreAccount> load() throws IOException {
        List<CoreAccount> list = new ArrayList<>();
        if (!Files.exists(filePath)) return list;
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    CoreAccount acc = CoreAccount.fromCsv(line);
                    list.add(acc);
                } catch (Exception e) {
                    // Skip bad lines but continue loading others
                }
            }
        }
        return list;
    }

    public void save(List<CoreAccount> accounts) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(filePath)) {
            for (CoreAccount acc : accounts) {
                bw.write(acc.toCsv());
                bw.newLine();
            }
        }
    }
}


class AccountException extends Exception {
    public AccountException(String message) { super(message); }
}
