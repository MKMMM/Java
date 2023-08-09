import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static <T> T getElementByIndex(List<T> lst, int index) {
        // write your code here

        // Size of the list
        int n = lst.size();

        // For positive indexes (regular order)
        if (index >= 0) {
            if (index >= n) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + n);
            }
            return lst.get(index);
        }

        // For negative indexes (backward order)
        else {
            int backwardIndex = n + index;
            if (backwardIndex < 0) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + n);
            }
            return lst.get(backwardIndex);
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        final List<String> values = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        final int index = Integer.parseInt(scanner.nextLine());

        try {
            String element = getElementByIndex(values, index);
            System.out.println(element);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception");
        }
    }
}