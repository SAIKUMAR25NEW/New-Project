package Week2day3;
import java.util.HashMap;
import java.util.Scanner;

public class StudentMarksMap {
    public static void main(String[] args) {

        // Create a HashMap to store student name and marks
        HashMap<String, Integer> studentMarks = new HashMap<>();

        // Add entries using put()
        studentMarks.put("Alice", 85);
        studentMarks.put("Bob", 92);
        studentMarks.put("Charlie", 78);
        studentMarks.put("David", 90);

        // Display all student marks
        System.out.println("Student Marks: " + studentMarks);

        // Get marks of a specific student using get()
        String name = "Bob";
        int marks = studentMarks.get(name);
        System.out.println(name + "'s Marks: " + marks);

        // Check if a student's record exists using containsKey()
        String checkName = "Eve";
        if (studentMarks.containsKey(checkName)) {
            System.out.println(checkName + "'s Marks: " + studentMarks.get(checkName));
        } else {
            System.out.println(checkName + " is not found in the record.");
        }

        // Add a new student record through user input
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter a new student name: ");
        String newName = sc.nextLine();
        System.out.print("Enter marks for " + newName + ": ");
        int newMarks = sc.nextInt();

        studentMarks.put(newName, newMarks);
        System.out.println("\nUpdated Student Marks: " + studentMarks);

        sc.close();
    }
}



