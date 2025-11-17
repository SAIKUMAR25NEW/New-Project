package Week2day4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadWriteFileExample {
    public static void main(String[] args) {

        // File paths (you can change them as needed)
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // Write data to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("Hello, this is a sample text file.\n");
            writer.write("This program writes and reads using BufferedWriter and BufferedReader.\n");
            writer.write("Java makes file handling simple!");
            System.out.println("✅ Data written successfully to " + outputFile);
        } catch (IOException e) {
            System.out.println("❌ Error writing file: " + e.getMessage());
        }

        // Read data from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line;
            System.out.println("\n📖 Reading from file:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }
}

