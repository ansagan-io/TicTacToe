package tictactoe;

import java.util.Random;

public class EasyLevel extends Player{
    private final Random random = new Random();
    public EasyLevel(char sign) {
        super(sign);
    }

    @Override
    public int getXArr() {
        int x = random.nextInt(9);
        while (TicTacToe.isOccupiedArr(x)){
            x = random.nextInt(9);
        }
        System.out.println("Making move level \"easy\"");
        return x;
    }

    /*@Override
    public int[] getXY() {
        int x = random.nextInt(3);
        int y = random.nextInt(3);
        while (TicTacToe.isOccupied(x, y)){
            x = random.nextInt(3);
            y = random.nextInt(3);
        }
        System.out.println("Making move level \"easy\"");
        return new int[]{x, y};
    }*/
}
