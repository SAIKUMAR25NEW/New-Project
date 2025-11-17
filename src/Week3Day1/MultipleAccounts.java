// File: MultipleAccounts.java
package Week3Day1;

import java.util.Scanner;

public class MultipleAccounts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first account holder name: ");
        String name1 = sc.nextLine();
        System.out.print("Enter " + name1 + "'s initial balance: ");
        double balance1 = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter second account holder name: ");
        String name2 = sc.nextLine();
        System.out.print("Enter " + name2 + "'s initial balance: ");
        double balance2 = sc.nextDouble();

        // ✅ Use the BankAccount class defined in BankAccount.java
        BankAccount acc1 = new BankAccount(name1, balance1);
        BankAccount acc2 = new BankAccount(name2, balance2);

        acc1.displayBalance();
        acc2.displayBalance();

        sc.close();
    }
}
