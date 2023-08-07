import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;




public class Main {
    public static void main(String[] args) {

        String filename = "C:\\dev\\Java\\FileReader\\src\\dataset3.txt";

        int[] numbers = readIntegersFromFile(filename);

        assert numbers != null;
        System.out.print("Largest numba is: ");
        System.out.println(largest(numbers));

        System.out.print("Number of numbers bigger than 9999 is: ");
        System.out.println(numberOfLargest(numbers));

        System.out.println(readIntegers(filename));

    }

    public static int[] readIntegersFromFile(String filename) {

        try (Scanner scanner = new Scanner(new File(filename))) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");

            int[] numbers = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                numbers[i] = Integer.parseInt(tokens[i]);
            }
            return numbers;

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            return null;
        }
    }
    public static int readIntegers(String filename) {
        List<Integer> numbersList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                int number = scanner.nextInt();
                numbersList.add(number);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }

        int[] numbers = new int[numbersList.size()];
        for (int i = 0; i < numbersList.size(); i++) {
            numbers[i] = numbersList.get(i);
        }

        int laSummaraisia = 0;

        for (int i = 0; i < numbers.length; i++){
            laSummaraisia = numbers[i] + laSummaraisia;
        }

        return laSummaraisia;
    }

    public static int largest(int[] arr) {

        int max = arr[0];

        for( int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int numberOfLargest(int[] arr) {

        int counter = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > 9999) {
                counter ++;
            }

        }
        return counter;
    }

}