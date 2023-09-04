package tictactoe;

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
        for (char[] row : board) {
            for (char cell : row) {
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
