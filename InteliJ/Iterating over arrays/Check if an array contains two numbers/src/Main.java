import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the size of the array
        int size = scanner.nextInt();

        // Read the array elements
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        // Read the numbers n and m
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Check if the numbers can be found in the array
        boolean foundN = false;
        boolean foundM = false;
        boolean adjacent = false; // New variable to check adjacency

        for (int i = 0; i < size; i++) {
            if (array[i] == n) {
                foundN = true;
                // Check if the next element is m
                if (i < size - 1 && array[i + 1] == m) {
                    adjacent = true;
                    break;
                }
            }
            if (array[i] == m) {
                foundM = true;
                // Check if the next element is n
                if (i < size - 1 && array[i + 1] == n) {
                    adjacent = true;
                    break;
                }
            }
        }

        // Print the result
        System.out.println(adjacent);

        scanner.close();
    }
}
