package week2day2;

import java.util.ArrayList;
import java.util.HashSet;

public class FindDuplicates {
    public static void main(String[] args) {

        // Create an ArrayList with duplicate elements
        ArrayList<String> names = new ArrayList<>();
        names.add("John");
        names.add("Alice");
        names.add("Bob");
        names.add("John");
        names.add("David");
        names.add("Alice");

        System.out.println("Original List: " + names);

        // Create a HashSet to track unique elements
        HashSet<String> unique = new HashSet<>();
        HashSet<String> duplicates = new HashSet<>();

        // Loop through the ArrayList
        for (String name : names) {
            // If element can't be added (already exists), it's a duplicate
            if (!unique.add(name)) {
                duplicates.add(name);
            }
        }

        // Display duplicates
        System.out.println("Duplicate Elements: " + duplicates);
    }
}

