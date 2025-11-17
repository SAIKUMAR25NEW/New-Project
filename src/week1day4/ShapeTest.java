package week1day4;
import java.util.Scanner;


// Base class
class Shape {
    public double area() {
        return 0;
    }

    public double perimeter() {
        return 0;
    }
}

// Subclass for Circle
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}

// Subclass for Rectangle
class Rectangle extends Shape {
    private double length, width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public double perimeter() {
        return 2 * (length + width);
    }
}

// Week1day1.Main class
public class ShapeTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a shape: 1. Circle  2. Rectangle");
        int choice = scanner.nextInt();

        Shape shape;

        switch (choice) {
            case 1:
                System.out.print("Enter radius of circle: ");
                double radius = scanner.nextDouble();
                shape = new Circle(radius);
                break;

            case 2:
                System.out.print("Enter length of rectangle: ");
                double length = scanner.nextDouble();
                System.out.print("Enter width of rectangle: ");
                double width = scanner.nextDouble();
                shape = new Rectangle(length, width);
                break;

            default:
                System.out.println("Invalid choice!");
                scanner.close();
                return;
        }

        System.out.println("Area: " + shape.area());
        System.out.println("Perimeter: " + shape.perimeter());

        scanner.close();
    }
}
