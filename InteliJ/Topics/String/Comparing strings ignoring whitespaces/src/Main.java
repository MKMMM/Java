import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String first = scanner.nextLine().replace(" ", "");
        String second = scanner.nextLine().replace(" ", "");
        System.out.println(first.equals(second));
    }
}
