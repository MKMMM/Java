package tictactoe;

import java.util.Objects;
import java.util.Random;

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
