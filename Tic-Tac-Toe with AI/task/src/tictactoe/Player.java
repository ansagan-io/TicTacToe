package tictactoe;

abstract class Player {
    private final char sign;

    public Player(char sign) {
        this.sign = sign;
    }

    public abstract int getXArr();

    public char getSign() {
        return sign;
    }
}
