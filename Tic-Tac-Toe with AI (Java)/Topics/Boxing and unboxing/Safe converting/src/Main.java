import java.util.Scanner;

public class Main {

    public static int convert(Long value) {
        // write your code here
        if (value == null) {
            return 0;  // Default value for int
        } else if (value > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (value < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return value.intValue();
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String val = scanner.nextLine();
        Long longVal = "null".equals(val) ? null : Long.parseLong(val);
        System.out.println(convert(longVal));
    }
}