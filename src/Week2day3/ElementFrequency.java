package Week2day3;

import java.util.HashMap;
import java.util.Scanner;

public class ElementFrequency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask user for number of elements
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] numbers = new int[n];

        // Input elements
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        // Create a HashMap to store element frequencies
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        // Count frequency using loop
        for (int num : numbers) {
            if (frequencyMap.containsKey(num)) {
                frequencyMap.put(num, frequencyMap.get(num) + 1);
            } else {
                frequencyMap.put(num, 1);
            }
        }

        // Display frequency of each element
        System.out.println("\nFrequency of each element:");
        for (int key : frequencyMap.keySet()) {
            System.out.println(key + " → " + frequencyMap.get(key));
        }

        sc.close();
    }
}

