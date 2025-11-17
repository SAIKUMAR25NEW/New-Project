package week1day4;
public class ShapeDetails {
    private double length;
    private double width;
    private double radius;
    private String type;

    // Default constructor
    public ShapeDetails() {
        type = "Unknown";
        length = 0;
        width = 0;
        radius = 0;
    }

    // Constructor for square or rectangle
    public ShapeDetails(double length, double width) {
        this.length = length;
        this.width = width;
        this.type = "Rectangle";
    }

    // Constructor for circle
    public ShapeDetails(double radius) {
        this.radius = radius;
        this.type = "Circle";
    }

    // Constructor with type name only
    public ShapeDetails(String type) {
        this.type = type;
    }

    // Method to calculate area
    public double area() {
        switch (type) {
            case "Circle":
                return Math.PI * radius * radius;
            case "Rectangle":
                return length * width;
            default:
                return 0;
        }
    }

    // Method to calculate perimeter
    public double perimeter() {
        switch (type) {
            case "Circle":
                return 2 * Math.PI * radius;
            case "Rectangle":
                return 2 * (length + width);
            default:
                return 0;
        }
    }

    public void display() {
        System.out.println("Shape Type: " + type);
        System.out.println("Area: " + area());
        System.out.println("Perimeter: " + perimeter());
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        // Using different constructors
        ShapeDetails circle = new ShapeDetails(5);              // Circle
        ShapeDetails rectangle = new ShapeDetails(10, 5);       // Rectangle
        ShapeDetails unknown = new ShapeDetails();              // Default
        ShapeDetails named = new ShapeDetails("Custom Shape");  // Type-only

        circle.display();
        rectangle.display();
        unknown.display();
        named.display();
    }
}



