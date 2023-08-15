import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        // Parse the input date-time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime inputDateTime;
        try {
            inputDateTime = LocalDateTime.parse(input, formatter);

            // Calculate hours passed
            long hoursPassed = calculateHoursPassed(inputDateTime);

            // Output the result
            System.out.println(hoursPassed);
        } catch (Exception e) {
            System.out.println("Error parsing the date-time. Please ensure it's in the correct format.");
        }
    }

    private static long calculateHoursPassed(LocalDateTime dateTime) {
        LocalDateTime startOfYear = LocalDateTime.of(dateTime.getYear(), 1, 1, 0, 0);
        Duration duration = Duration.between(startOfYear, dateTime);
        return duration.toHours();
    }
}