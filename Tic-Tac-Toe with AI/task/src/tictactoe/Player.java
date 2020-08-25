package tictactoe;

public class Player {
    private char sign;

    public Player(char sign) {
        this.sign = sign;
    }

    public int[] getXY() {
        return new int[3];
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }
}
