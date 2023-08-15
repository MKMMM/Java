import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        // Read input
        Scanner scanner = new Scanner(System.in);
        String inputDateTime = scanner.next();
        int daysToAdd = scanner.nextInt();
        int hoursToSubtract = scanner.nextInt();

        // Convert the input string to a LocalDateTime object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, formatter);

        // Adjust the date and time
        dateTime = dateTime.plusDays(daysToAdd).minusHours(hoursToSubtract);

        // Convert the adjusted date-time to the required format and print
        System.out.println(dateTime.format(formatter));

        scanner.close();

    }
}