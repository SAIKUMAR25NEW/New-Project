package Week1day3;

import java.util.Scanner;

public class SumOfEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of N: ");
        int n = scanner.nextInt();

        int sum = 0;

        // Loop to calculate sum of first N even numbers
        for (int i = 1; i <= n; i++) {
            int evenNumber = 2 * i;
            sum += evenNumber;
        }

        System.out.println("The sum of the first " + n + " even numbers is: " + sum);

        scanner.close();
    }
}

