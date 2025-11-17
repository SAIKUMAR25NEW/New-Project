package Week2Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Ask user for input and output file names
        System.out.print("Enter input CSV file name (e.g., marks.csv): ");
        String inputFileName = sc.nextLine().trim();

        System.out.print("Enter output CSV file name (e.g., results.csv): ");
        String outputFileName = sc.nextLine().trim();

        File inputFile = new File(inputFileName);

        // 1 Validate input file existence
        if (!inputFile.exists() || !inputFile.isFile()) {
            System.out.println(" Input file does not exist: " + inputFile.getAbsolutePath());
            sc.close();
            return;
        }

        List<String[]> studentData = new ArrayList<>();
        String[] header = null;
        int invalidLines = 0;

        // 2 Read and validate CSV content
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (isFirstLine) {
                    // First line = header (Name,Math,Science,...)
                    header = parts;
                    if (header.length < 2) {
                        System.out.println(" Invalid header. Need at least Name + 1 subject.");
                        return;
                    }
                    isFirstLine = false;
                    continue;
                }

                // Validate each data row
                if (parts.length != header.length) {
                    System.out.println(" Skipping line (column count mismatch): " + line);
                    invalidLines++;
                    continue;
                }

                // Validate marks (must be integers)
                boolean validRow = true;
                for (int i = 1; i < parts.length; i++) { // skip name at index 0
                    String markStr = parts[i].trim();
                    try {
                        int mark = Integer.parseInt(markStr);
                        if (mark < 0 || mark > 100) {
                            System.out.println(" Skipping line (mark out of range 0-100): " + line);
                            validRow = false;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Skipping line (invalid number): " + line);
                        validRow = false;
                        break;
                    }
                }

                if (validRow) {
                    studentData.add(parts);
                } else {
                    invalidLines++;
                }
            }

        } catch (IOException e) {
            System.out.println(" Error reading file: " + e.getMessage());
            sc.close();
            return;
        }

        if (studentData.isEmpty()) {
            System.out.println(" No valid student records found. Please check your CSV file.");
            sc.close();
            return;
        }

        // 3 Perform calculations
        List<String> outputLines = new ArrayList<>();
        outputLines.add("Name,Total,Average");

        double highestAvg = Double.MIN_VALUE;
        double lowestAvg = Double.MAX_VALUE;
        String topStudent = "";
        String bottomStudent = "";

        double sumOfAllAverages = 0.0;

        for (String[] row : studentData) {
            String name = row[0].trim();

            int total = 0;
            int subjectCount = row.length - 1;

            for (int i = 1; i < row.length; i++) {
                int mark = Integer.parseInt(row[i].trim()); // already validated
                total += mark;
            }

            double average = (double) total / subjectCount;
            sumOfAllAverages += average;

            // Track top & bottom students
            if (average > highestAvg) {
                highestAvg = average;
                topStudent = name;
            }
            if (average < lowestAvg) {
                lowestAvg = average;
                bottomStudent = name;
            }

            outputLines.add(name + "," + total + "," + String.format("%.2f", average));
        }

        double classAverage = sumOfAllAverages / studentData.size();

        // 4 Write results to output file (with validation)
        File outputFile = new File(outputFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            for (String line : outputLines) {
                writer.write(line);
                writer.newLine();
            }

            writer.newLine();
            writer.write("Top Student," + topStudent + "," + String.format("%.2f", highestAvg));
            writer.newLine();
            writer.write("Bottom Student," + bottomStudent + "," + String.format("%.2f", lowestAvg));
            writer.newLine();
            writer.write("Class Average,," + String.format("%.2f", classAverage));
            writer.newLine();

            System.out.println(" Results successfully written to: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println(" Error writing to output file: " + e.getMessage());
            sc.close();
            return;
        }

        // 5 Print validation summary on console
        System.out.println("\n SUMMARY");
        System.out.println("Valid student records processed : " + studentData.size());
        System.out.println("Invalid lines skipped           : " + invalidLines);
        System.out.println("Top Student                     : " + topStudent + " (" + String.format("%.2f", highestAvg) + ")");
        System.out.println("Bottom Student                  : " + bottomStudent + " (" + String.format("%.2f", lowestAvg) + ")");
        System.out.println("Overall Class Average           : " + String.format("%.2f", classAverage));

        sc.close();
    }
}

