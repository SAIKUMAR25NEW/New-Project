package week2day2;

import java.util.ArrayList;

public class SumAndAverage {
    public static void main(String[] args) {

        // Create an ArrayList of numbers
        ArrayList<Integer> numbers = new ArrayList<>();

        // Add elements
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        System.out.println("Numbers in the list: " + numbers);

        // Calculate sum
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }

        // Calculate average
        double average = (double) sum / numbers.size();

        // Display results
        System.out.println("Sum of numbers: " + sum);
        System.out.println("Average of numbers: " + average);
    }
}

