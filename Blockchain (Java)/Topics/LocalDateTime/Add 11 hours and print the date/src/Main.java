import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        // Read input date-time
        String inputDateTime = scanner.next();
        LocalDateTime dateTime;

        // Convert the input string to a LocalDateTime object
        // If the input string has seconds, parse accordingly, otherwise use the format without seconds
        if (inputDateTime.length() > 16) {  // format with seconds: yyyy-MM-ddTHH:mm:ss -> 19 characters
            DateTimeFormatter formatterWithSeconds = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            dateTime = LocalDateTime.parse(inputDateTime, formatterWithSeconds);
        } else {
            DateTimeFormatter formatterWithoutSeconds = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            dateTime = LocalDateTime.parse(inputDateTime, formatterWithoutSeconds);
        }


        // Add 11 hours to the date-time
        dateTime = dateTime.plusHours(11);

        // Print only the date part of the adjusted date-time
        System.out.println(dateTime.toLocalDate());

        scanner.close();
    }
}