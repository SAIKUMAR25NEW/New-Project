package Week2day1;

import java.util.Scanner;

public class ArraySum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask for the size of the array
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        // Create an array
        int[] numbers = new int[n];

        // Input elements into the array
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        // Calculate the sum of all elements
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += numbers[i];
        }

        // Display the result
        System.out.println("The sum of all elements is: " + sum);

        sc.close();
    }
}

