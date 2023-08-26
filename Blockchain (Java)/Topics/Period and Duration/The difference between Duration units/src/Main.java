import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Duration> durations = createDurationList(scanner);

        Duration result = getMaxMinusAvg(durations);

        System.out.println(result);
    }

    public static List<Duration> createDurationList(Scanner scanner) {
        List<Duration> list = new ArrayList<>();
        list.add(Duration.of(scanner.nextInt(), ChronoUnit.DAYS));
        list.add(Duration.of(scanner.nextInt(), ChronoUnit.HOURS));
        list.add(Duration.of(scanner.nextInt(), ChronoUnit.MINUTES));
        list.add(Duration.of(scanner.nextInt(), ChronoUnit.MINUTES));
        list.add(Duration.of(scanner.nextInt(), ChronoUnit.SECONDS));

        return list;
    }

    public static Duration getMaxMinusAvg(List<Duration> durations) {

        // Initial max and sum columns
        Duration max = Duration.ZERO;
        Duration sum = Duration.ZERO;

        for (Duration d : durations) {
            if (d.compareTo(max) > 0) {
                max = d;
            }
            sum = sum.plus(d);
        }

        // Calculate the average
        Duration avg = sum.dividedBy(durations.size());

        // Return avg subtracted from max
        return max.minus(avg);
    }
}