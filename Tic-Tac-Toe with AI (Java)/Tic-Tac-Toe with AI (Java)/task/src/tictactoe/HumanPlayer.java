package tictactoe;

import java.util.Scanner;

class HumanPlayer extends Player {

    public HumanPlayer(char sign) {
        super(sign);
    }

    @Override
    public String getMove(TicTacToeBoard board) {

        // Logic for human player to get move
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates: ");
        return analyseInput(scanner.nextLine(), board);
    }

    private String analyseInput(String userInput, TicTacToeBoard board) {

        // Humans are humans we need to check their input. All of it. Eeehhh...
        String[] inputValues;
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);

        // Validate input until correct
        do {
            inputValues = userInput.split(" ");

            // Check input length
            if (inputValues.length == 2) {
                String rowInput = inputValues[0];
                String colInput = inputValues[1];

                // See if they are integers
                try {
                    int rowValue = Integer.parseInt(rowInput);
                    int colValue = Integer.parseInt(colInput);

                    // Check if they have correct values from 1 to 3
                    if (rowValue < 1 || rowValue > 3 || colValue < 1 || colValue > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else {
                        int row = rowValue - 1;
                        int col = colValue - 1;

                        // Check if cell is occupied
                        if (board.isCellOccupied(row, col)) {
                            System.out.println("This cell is occupied! Choose another one!");
                        } else {
                            validInput = true;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }

            if (!validInput) {

                // Prompt the user again for input
                System.out.println("Enter the coordinates: ");
                userInput = scanner.nextLine();
            }
        } while (!validInput);

        // No issues with the input
        return userInput;
    }

}
