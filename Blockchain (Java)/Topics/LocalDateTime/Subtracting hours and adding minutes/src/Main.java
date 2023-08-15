import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String dateTimeInput = scanner.nextLine();

        // Try parsing with and without seconds
        LocalDateTime dateTime = null;
        try {
            DateTimeFormatter formatterWithSeconds = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            dateTime = LocalDateTime.parse(dateTimeInput, formatterWithSeconds);
        } catch (DateTimeParseException e) {
            // If the format with seconds failed, try without seconds
            DateTimeFormatter formatterWithoutSeconds = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            dateTime = LocalDateTime.parse(dateTimeInput, formatterWithoutSeconds);
        }

        int hoursToSubtract = scanner.nextInt();
        int minutesToAdd = scanner.nextInt();

        // Perform the operations
        dateTime = dateTime.minusHours(hoursToSubtract).plusMinutes(minutesToAdd);

        // Output the result
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        System.out.println(dateTime.format(outputFormatter));
    }
}