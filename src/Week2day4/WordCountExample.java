package Week2day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordCountExample {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Ask user for file name or path
        System.out.print("Enter the file name (with path if not in project folder): ");
        String filePath = sc.nextLine();

        int wordCount = 0;

        // Try-with-resources to auto-close file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read file line by line
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                // Split the line into words (by spaces, tabs, etc.)
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }

            System.out.println("\n✅ Total number of words in the file: " + wordCount);

        } catch (IOException e) {
            System.out.println("\n❌ Error reading file: " + e.getMessage());
        }

        sc.close();
    }
}
