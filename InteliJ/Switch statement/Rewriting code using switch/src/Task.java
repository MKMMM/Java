// You can experiment here, it won’t be checked
import java.util.Scanner;

public class Task {
  public static void main(String[] args) {
    // put your code here
    Scanner scanner = new Scanner(System.in);

    int direction = scanner.nextInt();

    switch (direction) {
      case 1 -> System.out.println("Move up");
      case 2 -> System.out.println("Move down");
      case 3 -> System.out.println("Move left");
      case 4 -> System.out.println("Move right");
      case 0 -> System.out.println("Do not move");
      default -> System.out.println("Error!");
    }

  }
}