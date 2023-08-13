import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        // put your code here

        Scanner scanner = new Scanner(System.in);

        // Create a string array to store the parts
        String[] parts = scanner.nextLine().split(" ");

        // Make variables out of parsed parts
        int year = Integer.parseInt(parts[0]);
        int dayOfYear = Integer.parseInt(parts[1]);

        // Create a boolean variable
        boolean isFirstDayOfMonth = isFirstDayOfYear(year, dayOfYear);
        System.out.println(isFirstDayOfMonth);
    }

    private static boolean isFirstDayOfYear(int year, int dayOfYear) {

        // Convert year and day of year to a Localdate
        LocalDate date = LocalDate.ofYearDay(year, dayOfYear);

        // Check if day of month is 1
        return date.getDayOfMonth() == 1;
    }


}