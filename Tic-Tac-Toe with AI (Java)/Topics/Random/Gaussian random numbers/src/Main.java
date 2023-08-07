import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] parts = input.split(" ");
        int K = Integer.parseInt(parts[0]);
        int N = Integer.parseInt(parts[1]);
        float M = Float.parseFloat(parts[2]);

        long seed = K;

        while (true) {
            Random random = new Random(seed);
            boolean allLessThanM = true;
            for (int i = 0; i < N; i++) {
                double gaussian = random.nextGaussian();
                if (gaussian > M) {
                    allLessThanM = false;
                    break;
                }
            }

            if (allLessThanM) {
                System.out.println(seed);
                break;
            }

            seed++;
        }
    }
}
