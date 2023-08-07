import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int choice = scanner.nextInt();
        String[] choices = {"square", "circle", "triangle", "rhombus"};

        switch (choice) {
            case 1, 2, 3, 4:
                System.out.println("You have chosen a " + choices[choice - 1]);
                break;
            default:
                System.out.println("There is no such shape!");
                break;

        }
    }
}