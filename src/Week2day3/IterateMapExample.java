package Week2day3;
import java.util.HashMap;
import java.util.Map;

public class IterateMapExample {
    public static void main(String[] args) {

        // Create a HashMap to store student names and marks
        HashMap<String, Integer> studentMarks = new HashMap<>();

        // Add some entries
        studentMarks.put("Alice", 85);
        studentMarks.put("Bob", 72);
        studentMarks.put("Charlie", 90);
        studentMarks.put("David", 65);

        System.out.println("Student Marks: " + studentMarks);

        // ✅ Iterate using entrySet() (access both key and value)
        System.out.println("\nIterating using entrySet():");
        for (Map.Entry<String, Integer> entry : studentMarks.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        // ✅ Iterate using keySet() (access key, then get value separately)
        System.out.println("\nIterating using keySet():");
        for (String key : studentMarks.keySet()) {
            Integer value = studentMarks.get(key);
            System.out.println(key + " → " + value);
        }
    }
}


