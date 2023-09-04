package tictactoe;

public class GameRunner {

    public static void run(TicTacToeBoard board, Player playerOne, Player playerTwo) {
        char gameState;
        int row = 0;
        int col = 0;

        do {
            board.printBoard();

            // Player 1 move
            makeMove(board, playerOne);
            gameState = board.checkGameState(row, col);
            if (gameState != 'N') break;

            // Player 2 move
            board.printBoard();
            makeMove(board, playerTwo);
            gameState = board.checkGameState(row, col);
        } while (gameState == 'N');

        board.printBoard();
        printResult(gameState);
    }

    private static void makeMove(TicTacToeBoard board, Player player) {
        String move = player.getMove(board);
        String[] coords = move.split(" ");
        int row = Integer.parseInt(coords[0]) - 1;
        int col = Integer.parseInt(coords[1]) - 1;
        board.updateBoard(row, col, player.sign);
    }

    private static void printResult(char gameState) {
        switch (gameState) {
            case 'X' -> System.out.println("X wins");
            case 'O' -> System.out.println("O wins");
            case 'D' -> System.out.println("Draw");
        }
    }
}

