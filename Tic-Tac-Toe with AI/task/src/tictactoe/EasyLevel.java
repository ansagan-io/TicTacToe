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
}
