package Week2day4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AppendFileExample {
    public static void main(String[] args) {

        // File path (you can change it as needed)
        String filePath = "output.txt";

        // Data to append
        String newData = "\nThis is new data being appended to the file.";

        // Open FileWriter in append mode (true)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(newData);
            System.out.println("✅ New data appended successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("❌ Error appending to file: " + e.getMessage());
        }
    }
}

