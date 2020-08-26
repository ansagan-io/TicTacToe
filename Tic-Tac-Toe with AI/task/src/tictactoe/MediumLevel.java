package tictactoe;

import java.util.Random;

public class MediumLevel extends Player{
    private final Random random = new Random();

    public MediumLevel(char sign) {
        super(sign);
    }

    @Override
    public int[] getXY() {
        char enemySign;
        if(this.getSign() == 'X') {
            enemySign = 'O';
        }else {
            enemySign = 'X';
        }
        int[] scoresOfMe = TicTacToe.countScores(this.getSign());
        int[] scoresOfEnemy = TicTacToe.countScores(enemySign);
        for (int i = 0; i < 8; i++) {
            if (scoresOfMe[i] == 2){
                for (int[] coors : TicTacToe.coordinates.get(i)) {
                    if(!TicTacToe.isOccupied(coors[0], coors[1])){
                        System.out.println("Making move level \"medium\"");
                        return coors;
                    }
                }
            } else if(scoresOfEnemy[i] == 2){
                for (int[] coors : TicTacToe.coordinates.get(i)) {
                    if(!TicTacToe.isOccupied(coors[0], coors[1])){
                        System.out.println("Making move level \"medium\"");
                        return coors;
                    }
                }
            }
        }
        int x = random.nextInt(3);
        int y = random.nextInt(3);
        while (TicTacToe.isOccupied(x, y)) {
            x = random.nextInt(3);
            y = random.nextInt(3);
        }
        System.out.println("Making move level \"medium\"");
        return new int[]{x, y};
    }
}
