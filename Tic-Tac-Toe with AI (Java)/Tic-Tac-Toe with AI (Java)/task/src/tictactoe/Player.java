package tictactoe;

abstract class Player {
    protected char sign;

    public Player(char sign) {
        this.sign = sign;
    }

    public abstract String getMove(TicTacToeBoard board);
}
