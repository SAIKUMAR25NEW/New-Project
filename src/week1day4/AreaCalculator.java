package week1day4;

import java.util.Scanner;

public class AreaCalculator {

    // Method to calculate area of a circle
    public double area(double radius) {
        return Math.PI * radius * radius;
    }

    // Method to calculate area of a rectangle
    public double area(double length, double width) {
        return length * width;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AreaCalculator calc = new AreaCalculator();

        System.out.println("Choose shape to calculate area:");
        System.out.println("1. Circle");
        System.out.println("2. Rectangle");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter radius: ");
                double radius = scanner.nextDouble();
                System.out.println("Area of Circle: " + calc.area(radius));
                break;

            case 2:
                System.out.print("Enter length: ");
                double length = scanner.nextDouble();
                System.out.print("Enter width: ");
                double width = scanner.nextDouble();
                System.out.println("Area of Rectangle: " + calc.area(length, width));
                break;

            default:
                System.out.println("Invalid choice!");
        }

        scanner.close();
    }
}

