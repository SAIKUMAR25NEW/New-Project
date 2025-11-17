package Week2day5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class StudentMarksProcessor {
    public static void main(String[] args) {
        String inputFile = "marks.csv";       // Input CSV file
        String outputFile = "results.csv";    // Output file

        ArrayList<String[]> studentData = new ArrayList<>();

        // 1️⃣ Read data from CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {  // Skip header line
                    isHeader = false;
                    continue;
                }
                String[] parts = line.split(",");
                studentData.add(parts);
            }

        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
            return;
        }

        // 2️⃣ Process each student's marks
        ArrayList<String> results = new ArrayList<>();
        results.add("Name,Total,Average"); // Header for output file

        for (String[] student : studentData) {
            String name = student[0];
            int[] marks = Arrays.stream(student, 1, student.length)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int total = 0;
            for (int m : marks) {
                total += m;
            }

            double average = (double) total / marks.length;

            results.add(name + "," + total + "," + String.format("%.2f", average));
        }

        // 3️⃣ Write results to output CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String line : results) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("✅ Results written successfully to " + outputFile);
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }
}

