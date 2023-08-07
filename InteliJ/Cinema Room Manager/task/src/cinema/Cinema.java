package cinema;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;


public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int numRows = scanner.nextInt();

        System.out.print("Enter the number of seats in each row: ");
        int numSeats = scanner.nextInt();

        char[][] seatAssignment = new char[numRows][numSeats];
        initializeSeats(seatAssignment);
        // Main Menu
        printInfoHeader();
        int choice = scanner.nextInt();

        while (choice != 0) {
            switch (choice) {
                case 1 -> {
                    System.out.println();
                    printSeats(seatAssignment);
                }
                case 2 -> {
                    System.out.println();
                    System.out.print("Enter a row number: ");
                    int rowNumber = scanner.nextInt();
                    System.out.print("Enter a seat number in that row: ");
                    int seatNumber = scanner.nextInt();
                    int ticketPrice = seatCalculations(rowNumber, seatAssignment);
                    System.out.println();
                    System.out.println("Ticket price: $" + ticketPrice);
                    System.out.println();
                    assignSeat(rowNumber, seatNumber, seatAssignment);
                }
                case 3 -> {
                    System.out.println();
                    calculateStatistics(seatAssignment);
                }
                case 0 -> System.out.println();
                default -> {
                }
            }
            printInfoHeader();
            choice = scanner.nextInt();
        }

    }

    public static void printSeats(char[][] seatArray) {

        System.out.println("Cinema:");
        System.out.print("  ");
        int numOfSeats = seatArray[0].length;

        // Print the header row
        for (int i = 0; i < numOfSeats; i++){
            System.out.print(i + 1 + " ");

        }

        System.out.println(" ");
        //Print the cinema pattern
        for (int i = 0; i < seatArray.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0 ; j < seatArray[i].length; j++) {
                System.out.print(seatArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }
    public static void initializeSeats(char[][] seatArray) {

        //Initialize 'empty' seat array enhanced for loop
        for (char[] chars : seatArray) {
            Arrays.fill(chars, 'S');
        }

    }

    public static int seatCalculations(int rowNumber, char[][] seatArrangement) {

        int totalSeats = (seatArrangement.length) * (seatArrangement.length);
        int ticketPrice;
        int midpoint = seatArrangement.length / 2;

        if (totalSeats < 60){
            ticketPrice = 10;
        } else {
            if ( rowNumber <= midpoint ) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }

    return ticketPrice;

    }

    public static void calculateStatistics(char[][] seatArrangement){

        System.out.println();
        int ticketCounter = 0;
        int totalSeats = seatArrangement.length * seatArrangement.length;
        double percentageSold = 0.0;
        int totalPrice = 0;
        int totalTicketPrice = 0;

        // Calculate number of tickets sold
        for (char[] chars : seatArrangement) {
            for (int j = 0; j < seatArrangement.length; j++) {
                if (chars[j] == 'B') {
                    ticketCounter++;
                }
            }
        }

        System.out.println("Number of purchased tickets: " + ticketCounter);

        // Calculate the percentage sold
        percentageSold = ((double) ticketCounter /totalSeats) * 100.0;
        System.out.printf(Locale.US, "Percentage: %.2f%%%n", percentageSold);

        // Calculate the total tickets sold
        for (int i = 0; i < seatArrangement.length; i++) {
            int rowPrice = (i < seatArrangement.length / 2) ? 10 : 8;

            for (int j = 0; j < seatArrangement[i].length; j++) {
                if (seatArrangement[i][j] == 'B') {
                    totalTicketPrice += rowPrice;
                }
            }
        }
        System.out.println("Current income: $" + totalTicketPrice);

        // Calculate and print total price
        if (totalSeats < 60) {
            totalPrice = 10 * totalSeats;
        } else {
            for (int i = 0; i < seatArrangement.length; i++) {
                int rowPrice = (i < seatArrangement.length / 2) ? 10 : 8;

                for (int j = 0; j < seatArrangement[i].length; j++) {
                        totalPrice += rowPrice;
                }
            }
        }
        System.out.println("Total income: $" + totalPrice);
        System.out.println();

    }

    public static void assignSeat(int rowNumber, int seatNumber, char[][] seatArrangement) {

        //Assign Seat
        int numRows = seatArrangement.length;
        int numSeatsPerRow = seatArrangement[0].length;

        while (true) {
            if (rowNumber < 1 || seatNumber < 1 || rowNumber > numRows || seatNumber > numSeatsPerRow) {
                System.out.println();
                System.out.println("Wrong input!");
                System.out.println();
            } else if (seatArrangement[rowNumber - 1][seatNumber - 1] == 'B') {
                System.out.println();
                System.out.println("That ticket has already been purchased!");
                System.out.println();
            } else {
                break;
            }

            // Ask for new seat coordinates
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter row number: ");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row: ");
            seatNumber = scanner.nextInt();
            System.out.println();

            int ticketPrice = seatCalculations(rowNumber, seatArrangement);
            System.out.println();
            System.out.println("Ticket price: $" + ticketPrice);
            System.out.println();
        }

        // Assign Seat
        seatArrangement[rowNumber - 1][seatNumber - 1] = 'B';

    }

    public static void printInfoHeader(){
      System.out.println("1. Show the seats");
      System.out.println("2. Buy a ticket");
      System.out.println("3. Statistics");
      System.out.println("0. Exit");
    }

}