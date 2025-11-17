package Week1day3;

import java.util.Scanner;

public class Multiplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number to print its multiplication table: ");
        int num = scanner.nextInt();

        System.out.println("Week1day3.Multiplication Table of " + num + ":");

        for (int i = 1; i <= 10; i++) {
            System.out.println(num + " × " + i + " = " + (num * i));
        }

        scanner.close();
    }
}
