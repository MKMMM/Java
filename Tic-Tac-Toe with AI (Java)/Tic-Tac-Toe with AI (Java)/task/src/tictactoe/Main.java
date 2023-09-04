package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.println("Input command: ");
            userInput = scanner.nextLine().trim();
            String[] commands = userInput.split(" ");

            if (commands.length == 3 && commands[0].equalsIgnoreCase("start")) {
                Player player1 = createPlayer(commands[1], 'X');
                Player player2 = createPlayer(commands[2], 'O');

                if (player1 == null || player2 == null) {
                    System.out.println("Invalid player type");
                    continue;
                }

                TicTacToeBoard board = new TicTacToeBoard();
                GameRunner.run(board, player1, player2);
            } else if (commands.length == 1 && commands[0].equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    private static Player createPlayer(String type, char sign) {
        return switch (type.toLowerCase()) {
            case "user" -> new HumanPlayer(sign);
            case "easy" -> new AIPlayer(sign, "easy");
            case "medium" -> new AIPlayer(sign, "medium");
            case "hard" -> new AIPlayer(sign, "hard");
            default -> null;
        };
    }
}
