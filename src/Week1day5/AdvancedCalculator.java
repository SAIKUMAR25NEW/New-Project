package Week1day5;

import java.util.Scanner;

public class AdvancedCalculator {

    // Method for addition
    public static double add(double a, double b) {
        return a + b;
    }

    // Method for subtraction
    public static double subtract(double a, double b) {
        return a - b;
    }

    // Method for multiplication
    public static double multiply(double a, double b) {
        return a * b;
    }

    // Method for division
    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero is not allowed!");
            return 0;
        }
        return a / b;
    }

    // Method for power
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    // Method for reversing a number
    public static int reverseNumber(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return reversed;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== MENU DRIVEN CALCULATOR ===");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Power");
            System.out.println("6. Reverse a Number");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter first number: ");
                    double a1 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    double b1 = scanner.nextDouble();
                    System.out.println("Result: " + add(a1, b1));
                    break;

                case 2:
                    System.out.print("Enter first number: ");
                    double a2 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    double b2 = scanner.nextDouble();
                    System.out.println("Result: " + subtract(a2, b2));
                    break;

                case 3:
                    System.out.print("Enter first number: ");
                    double a3 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    double b3 = scanner.nextDouble();
                    System.out.println("Result: " + multiply(a3, b3));
                    break;

                case 4:
                    System.out.print("Enter first number: ");
                    double a4 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    double b4 = scanner.nextDouble();
                    System.out.println("Result: " + divide(a4, b4));
                    break;

                case 5:
                    System.out.print("Enter base: ");
                    double base = scanner.nextDouble();
                    System.out.print("Enter exponent: ");
                    double exp = scanner.nextDouble();
                    System.out.println("Result: " + power(base, exp));
                    break;

                case 6:
                    System.out.print("Enter a number to reverse: ");
                    int num = scanner.nextInt();
                    System.out.println("Reversed Number: " + reverseNumber(num));
                    break;

                case 7:
                    System.out.println("Exiting the program. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Please enter between 1-7.");
            }

        } while (choice != 7);

        scanner.close();
    }
}

