import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        // put your code here
        Deque<Character> stack = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        boolean isBalanced = true;

        for (char c : userInput.toCharArray()) {

            // If opening bracket push to Deque
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    isBalanced = false;
                    break;
                }

                // If closing pop
                char openBracket = stack.pop();
                if (c == ')' && openBracket != '(' ||
                    c == '}' && openBracket != '{' ||
                    c == ']' && openBracket != '[') {
                    isBalanced = false;
                    break;
                }
            }
        }

        if (isBalanced && stack.isEmpty()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}