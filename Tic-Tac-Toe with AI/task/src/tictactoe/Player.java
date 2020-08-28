package tictactoe;

abstract class Player {
    private final char sign;

    public Player(char sign) {
        this.sign = sign;
    }

    public abstract int[] getXY();

    public char getSign() {
        return sign;
    }
}
