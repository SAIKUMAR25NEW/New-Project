package Week2day3;
import java.util.HashMap;
import java.util.Map;

public class FilterStudents {
    public static void main(String[] args) {

        // Create a HashMap to store student names and their marks
        HashMap<String, Integer> studentMarks = new HashMap<>();

        // Add some student records
        studentMarks.put("Alice", 45);
        studentMarks.put("Bob", 72);
        studentMarks.put("Charlie", 60);
        studentMarks.put("David", 35);
        studentMarks.put("Eve", 90);

        System.out.println("All Students: " + studentMarks);

        System.out.println("\nStudents with marks > 50:");

        // Loop through entries using Map.Entry
        for (Map.Entry<String, Integer> entry : studentMarks.entrySet()) {
            String name = entry.getKey();
            int marks = entry.getValue();

            if (marks > 50) {
                System.out.println(name + " → " + marks);
            }
        }
    }
}


