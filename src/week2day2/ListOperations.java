package week2day2;

import java.util.ArrayList;
import java.util.Iterator;

public class ListOperations {
    public static void main(String[] args) {

        // Create an ArrayList of integers
        ArrayList<Integer> numbers = new ArrayList<>();

        // ✅ Add elements
        numbers.add(10);
        numbers.add(25);
        numbers.add(30);
        numbers.add(45);
        numbers.add(60);

        System.out.println("Original List: " + numbers);

        // ✅ Remove an element
        numbers.remove(Integer.valueOf(30)); // removes the element 30
        System.out.println("After Removing 30: " + numbers);

        // ✅ Filter elements (remove numbers less than 40)
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            int num = iterator.next();
            if (num < 40) {
                iterator.remove(); // safe removal while iterating
            }
        }

        System.out.println("After Filtering (<40 removed): " + numbers);

        // ✅ Iterate using for-each loop
        System.out.println("\nIterating with for-each loop:");
        for (int num : numbers) {
            System.out.println(num);
        }

        // ✅ Iterate using Iterator again
        System.out.println("\nIterating with Iterator:");
        Iterator<Integer> it = numbers.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
