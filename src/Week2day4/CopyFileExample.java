package Week2day4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CopyFileExample {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Ask user for input and output file names
        System.out.print("Enter the source file name (file to copy from): ");
        String sourceFile = sc.nextLine();

        System.out.print("Enter the destination file name (file to copy to): ");
        String destinationFile = sc.nextLine();

        // Try-with-resources to auto-close files
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile))) {

            String line;
            // Read each line from source and write to destination
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine(); // write a new line after each line
            }

            System.out.println("\n✅ File copied successfully from " + sourceFile + " to " + destinationFile);

        } catch (IOException e) {
            System.out.println("\n❌ Error copying file: " + e.getMessage());
        }

        sc.close();
    }
}

