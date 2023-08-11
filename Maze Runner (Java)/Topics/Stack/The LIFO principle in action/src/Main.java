import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        Deque<String> stack = new ArrayDeque<>();

        int numOfElems = scanner.nextInt();

        for (int i = 0; i < numOfElems; i++) {
            String inputStr = scanner.nextLine();
            // Check if inputStr is empty
            if (!inputStr.isEmpty()) {
                stack.push(inputStr);
            } else {
                // Handle the case when inputStr is empty, if needed
                i--;
            }
        }

        for (int i = 0; i < numOfElems; i++) {
            System.out.println(stack.poll());
        }

    }
}