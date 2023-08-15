import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here

        LocalTime now = LocalTime.parse(scanner.nextLine());
        LocalTime then = LocalTime.parse(scanner.nextLine());

        System.out.println(Math.abs(now.toSecondOfDay() - then.toSecondOfDay()));

    }
}