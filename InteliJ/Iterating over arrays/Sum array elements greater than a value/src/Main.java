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

        // Read the number n
        int n = scanner.nextInt();

        // Sum the array elements greater than n
        int sum = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] > n) {
                sum += array[i];
            }
        }

        // Print the sum
        System.out.println(sum);

        scanner.close();
    }
}
