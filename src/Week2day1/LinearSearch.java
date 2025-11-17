package Week2day1;

import java.util.Scanner;

public class LinearSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the size of the array
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        // Input array elements
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Ask for the element to search
        System.out.print("Enter the element to search: ");
        int key = sc.nextInt();

        // Perform Linear Search
        boolean found = false;
        int position = -1;

        for (int i = 0; i < n; i++) {
            if (arr[i] == key) {
                found = true;
                position = i;
                break; // stop when element is found
            }
        }

        // Display the result
        if (found) {
            System.out.println("Element " + key + " found at position " + (position + 1));
        } else {
            System.out.println("Element " + key + " not found in the array.");
        }

        sc.close();
    }
}

