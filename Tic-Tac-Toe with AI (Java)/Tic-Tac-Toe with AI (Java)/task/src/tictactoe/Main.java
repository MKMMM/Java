package tictactoe;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

class TicTacToeBoard {
    private final char[][] board;

    public TicTacToeBoard() {
        // Initialize the board with empty spaces
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '_';
            }
        }
    }

    // Copy constructor used by the hardMove method
    public TicTacToeBoard(TicTacToeBoard oldBoard) {
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(oldBoard.board[i], 0, this.board[i], 0, 3);
        }
    }


    // Default getter
    public char[][] getBoard() {
        return board;
    }

    public void printBoard() {

        // Print the board
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public void updateBoard(int row, int col, char sign) {

        // Update the board if the cell is empty
        if (board[row][col] == '_') {
            board[row][col] = sign;
        }
    }

    public char checkGameState(int lastRow, int lastCol) {
        // Checks the game state for each player and returns a status value

        // If there is a winning condition for 'X'
        if (isWon(board, 'X', lastRow, lastCol)) {
            return 'X';
        }

        // If there is a winning condition for 'O'
        if (isWon(board, 'O', lastRow, lastCol)) {
            return 'O';
        }

        // Check for empty cells
        for (char[] row: board) {
            for (char cell: row) {
                if (cell == '_') {
                    return 'N'; // Game not over yet
                }
            }
        }

        // If no winner and no empty cells, it's a draw
        return 'D';
    }

    public boolean isCellOccupied(int row, int col) {

        // Returns a boolean value if a cell is occupied by a player
        return board[row][col] == 'X' || board[row][col] == 'O';
    }

    public boolean isCellEmpty(int row, int col) {

        // Returns a boolean value if a cell is empty
        return board[row][col] == '_';
    }

    // Method to check if the game is over
    public boolean isGameOver() {
        // The game is over if a player has won or there are no empty cells left.
        return this.hasPlayerWon('X') || this.hasPlayerWon('O') || !this.hasEmptyCell();
    }
    // Method to check if there's any empty cell left
    public boolean hasEmptyCell() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isCellEmpty(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean hasPlayerWon(char playerSign) {
        for (int i = 0; i < 3; i++) {
            // Checking horizontal lines
            if (board[i][0] == playerSign && board[i][1] == playerSign && board[i][2] == playerSign) {
                return true;
            }
            // Checking vertical lines
            if (board[0][i] == playerSign && board[1][i] == playerSign && board[2][i] == playerSign) {
                return true;
            }
        }
        // Checking diagonals
        if (board[0][0] == playerSign && board[1][1] == playerSign && board[2][2] == playerSign) {
            return true;
        }
        return board[2][0] == playerSign && board[1][1] == playerSign && board[0][2] == playerSign;
    }


    public static boolean isWon(char[][] tickTacBoard, char sign, int lastRow, int lastCol) {
        // Check the row
        boolean rowWon = true;
        for (int i = 0; i < 3; i++) {
            if (tickTacBoard[lastRow][i] != sign) {
                rowWon = false;
                break;
            }
        }

        // Check the column
        boolean colWon = true;
        for (int i = 0; i < 3; i++) {
            if (tickTacBoard[i][lastCol] != sign) {
                colWon = false;
                break;
            }
        }

        // Check the main diagonal
        boolean mainDiagWon = true;
        if (lastRow == lastCol) {
            for (int i = 0; i < 3; i++) {
                if (tickTacBoard[i][i] != sign) {
                    mainDiagWon = false;
                    break;
                }
            }
        } else {
            mainDiagWon = false;
        }

        // Check the anti-diagonal
        boolean antiDiagWon = true;
        if (lastRow + lastCol == 2) {
            for (int i = 0; i < 3; i++) {
                if (tickTacBoard[i][2 - i] != sign) {
                    antiDiagWon = false;
                    break;
                }
            }
        } else {
            antiDiagWon = false;
        }

        return rowWon || colWon || mainDiagWon || antiDiagWon;
    }

}

abstract class Player {
    protected char sign;

    public Player(char sign) {
        this.sign = sign;
    }

    public abstract String getMove(TicTacToeBoard board);
}

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

class AIPlayer extends Player {

    private final String difficulty;

    public AIPlayer(char sign, String difficulty) {
        super(sign);
        this.difficulty = difficulty;

    }

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public String getMove(TicTacToeBoard board) {
        System.out.println("Making move level \"" + getDifficulty() + "\"");

        if (Objects.equals(getDifficulty(), "easy")) {
            return easyMove(board);
        } else if (Objects.equals(getDifficulty(), "medium")) {
            String move = mediumMove(board, sign);
            if (move != null) {
                return move;
            }
            move = mediumMove(board, getOppositeSign(sign));
            if (move != null) {
                return move;
            }
            return easyMove(board);
        } else if (Objects.equals(getDifficulty(), "hard")) {
            return hardMove(board, sign);
        }

        throw new IllegalArgumentException("Invalid difficulty level");
    }


    private static String easyMove(TicTacToeBoard board) {

        // Easy move method generating a random set of coordinates
        int upper = 3;
        int lower = 1;

        Random random = new Random();
        String computerMove;

        // Do its thAng until finding an empty cell
        do {
            int rowValue = random.nextInt(upper - lower + 1) + lower;
            int colValue = random.nextInt(upper - lower + 1) + lower;

            if (board.isCellEmpty(rowValue - 1, colValue - 1)) {
                computerMove = rowValue + " " + colValue;
                break;
            }
        } while (true);

        return computerMove;
    }

    private static String mediumMove(TicTacToeBoard board, char playerSign) {

        // Check rows and columns - iterating through the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[i][j] == '_') {
                    // Check rows - each point takes a modulo (it returns the adjecent fields)
                    // and checking if it matches the player's sign
                    if ((board.getBoard()[i][(j + 1) % 3] == playerSign && board.getBoard()[i][(j + 2) % 3] == playerSign) ||
                            (board.getBoard()[(i + 1) % 3][j] == playerSign && board.getBoard()[(i + 2) % 3][j] == playerSign)) {
                        return (i + 1) + " " + (j + 1); // Winning move found
                    }
                    // Check diagonals from one corner
                    if (i == j && board.getBoard()[(i + 1) % 3][(j + 1) % 3] == playerSign && board.getBoard()[(i + 2) % 3][(j + 2) % 3] == playerSign) {
                        return (i + 1) + " " + (j + 1); // Winning move found
                    }
                    // Check diagonals from the other corner
                    if (i + j == 2 && board.getBoard()[(i + 1) % 3][(j + 2) % 3] == playerSign && board.getBoard()[(i + 2) % 3][(j + 1) % 3] == playerSign) {
                        return (i + 1) + " " + (j + 1); // Winning move found
                    }
                }
            }
        }
        // Computer says no....
        return null; // No winning move found
    }

    private static char getOppositeSign(char sign) {
        return sign == 'X' ? 'O' : 'X';
    }

    private static String hardMove(TicTacToeBoard board, char playerSign) {
        int bestScore = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;

        TicTacToeBoard copyBoard = new TicTacToeBoard(board); // Create a copy of the board

        // Check if the middle cell is empty
        if (board.isCellEmpty(1, 1)) {
            return "2 2"; // Return the coordinates for the middle cell
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isCellEmpty(i, j)) {
                    copyBoard.updateBoard(i, j, playerSign); // Update the copy board
                    int score = minimax(copyBoard, 9, false, playerSign, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    copyBoard.updateBoard(i, j, '_'); // Reset the copy board

                    if (score > bestScore) {
                        bestRow = i;
                        bestCol = j;
                        bestScore = score;
                    }
                }
            }
        }

        if (bestRow == -1 || bestCol == -1) {
            return "-1 -1"; // Return an invalid move when no winning move or empty cell is found
        }

        return (bestRow + 1) + " " + (bestCol + 1);
    }




    private static int minimax(TicTacToeBoard board, int depth, boolean isMaximizing, char playerSign, int alpha, int beta) {
        char opponentSign = getOppositeSign(playerSign);

        if (board.isGameOver()) {
            if (board.hasPlayerWon(opponentSign)) {
                return -10; // Opponent wins
            } else if (board.hasPlayerWon(playerSign)) {
                return 10; // Player wins
            } else {
                return 0; // It's a draw
            }
        }

        if (depth <= 0) {
            return 0; // Reached maximum depth, return a neutral score
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isCellEmpty(i, j)) {
                        board.updateBoard(i, j, playerSign);
                        int score = minimax(board, depth - 1, false, playerSign, alpha, beta);
                        board.updateBoard(i, j, '_'); // Undo move
                        bestScore = Math.max(score, bestScore);
                        alpha = Math.max(alpha, bestScore);

                        if (beta <= alpha) {
                            return bestScore;
                        }
                    }
                }
            }

            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isCellEmpty(i, j)) {
                        board.updateBoard(i, j, opponentSign);
                        int score = minimax(board, depth - 1, true, playerSign, alpha, beta);
                        board.updateBoard(i, j, '_'); // Undo move
                        bestScore = Math.min(score, bestScore);
                        beta = Math.min(beta, bestScore);

                        if (beta <= alpha) {
                            return bestScore;
                        }
                    }
                }
            }

            return bestScore;
        }
    }


}

public class Main {
    public static void RunGame(TicTacToeBoard board, Player playerOne, Player playerTwo) {
        char gameState;
        int row = 0;
        int col = 0;

        do {
            board.printBoard();
            // Player 1 move
            String playerOneMove = playerOne.getMove(board);
            String[] coords = playerOneMove.split(" ");
            row = Integer.parseInt(coords[0]) - 1;
            col = Integer.parseInt(coords[1]) - 1;
            board.updateBoard(row, col, playerOne.sign);

            // First game state check in case of P1 win
            gameState = board.checkGameState(row, col);
            if (gameState != 'N') {
                break;
            }

            board.printBoard();
            // Player 2 move
            String playerTwoMove = playerTwo.getMove(board);
            coords = playerTwoMove.split(" ");
            row = Integer.parseInt(coords[0]) - 1;
            col = Integer.parseInt(coords[1]) - 1;
            board.updateBoard(row, col, playerTwo.sign);

            // Update game state in case of P2 win
            gameState = board.checkGameState(row, col);
        } while (gameState == 'N');

        board.printBoard();

        // When game state is one of the following print result
        switch (gameState) {
            case 'X' -> System.out.println("X wins");
            case 'O' -> System.out.println("O wins");
            case 'D' -> System.out.println("Draw");
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        // Main loop and main menu
        while (true) {
            System.out.println("Input command: ");
            userInput = scanner.nextLine().trim();
            String[] commands = userInput.split(" ");

            if (commands.length == 3) {
                if (commands[0].equalsIgnoreCase("start")) {
                    String player1Type = commands[1];
                    String player2Type = commands[2];
                    Player player1, player2;

                    if (player1Type.equals("user")) {
                        player1 = new HumanPlayer('X');
                    } else if (player1Type.startsWith("easy")) {
                        player1 = new AIPlayer('X', "easy");
                    } else if (player1Type.startsWith("medium")) {
                        player1 = new AIPlayer('X', "medium");
                    } else if (player1Type.startsWith("hard")) {
                        player1 = new AIPlayer('X', "hard");
                    } else {
                        System.out.println("Invalid player type");
                        continue;
                    }

                    if (player2Type.equals("user")) {
                        player2 = new HumanPlayer('O');
                    } else if (player2Type.startsWith("easy")) {
                        player2 = new AIPlayer('O', "easy");
                    } else if (player2Type.startsWith("medium")) {
                        player2 = new AIPlayer('O', "medium");
                    } else if (player2Type.startsWith("hard")) {
                        player2 = new AIPlayer('O', "hard");
                    } else {
                        System.out.println("Invalid player type");
                        continue;
                    }

                    TicTacToeBoard board = new TicTacToeBoard();
                    RunGame(board, player1, player2);

                } else {
                    System.out.println("Unknown command");
                }
            } else if (commands.length == 1 && commands[0].equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

}