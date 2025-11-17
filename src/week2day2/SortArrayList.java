package week2day2;

import java.util.ArrayList;
import java.util.Collections;

public class SortArrayList {
    public static void main(String[] args) {

        // Create an ArrayList of integers
        ArrayList<Integer> numbers = new ArrayList<>();

        // Add elements
        numbers.add(50);
        numbers.add(10);
        numbers.add(30);
        numbers.add(70);
        numbers.add(20);

        System.out.println("Original List: " + numbers);

        // ✅ Sort the list in ascending order
        Collections.sort(numbers);
        System.out.println("Sorted List (Ascending): " + numbers);

        // ✅ Sort the list in descending order
        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("Sorted List (Descending): " + numbers);
    }
}

