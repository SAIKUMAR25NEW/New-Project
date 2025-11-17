package Week2day1;
import java.util.Scanner;


public class StudentMarks {



        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            // Ask how many students
            System.out.print("Enter the number of students: ");
            int n = sc.nextInt();

            // Create an array to store marks
            int[] marks = new int[n];

            // Input marks using a loop
            for (int i = 0; i < n; i++) {
                System.out.print("Enter marks for student " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            }

            // Initialize variables for calculations
            int max = marks[0];
            int min = marks[0];
            int sum = 0;

            // Loop through array to find max, min, and sum
            for (int i = 0; i < n; i++) {
                if (marks[i] > max) {
                    max = marks[i];
                }
                if (marks[i] < min) {
                    min = marks[i];
                }
                sum += marks[i];
            }

            // Calculate average
            double average = (double) sum / n;

            // Display results
            System.out.println("\nHighest Marks: " + max);
            System.out.println("Lowest Marks: " + min);
            System.out.println("Average Marks: " + average);

            sc.close();
        }
    }


