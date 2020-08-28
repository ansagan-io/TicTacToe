package tictactoe;

import java.util.Random;

public class MediumLevel extends Player{

    private final Random random = new Random();

    public MediumLevel(char sign) {
        super(sign);
    }

    @Override
    public int getXArr() {
        char enemySign;
        if(this.getSign() == 'X') {
            enemySign = 'O';
        }else {
            enemySign = 'X';
        }
        int[][] scores = TicTacToe.countScoresArr(this.getSign(), enemySign);

        for (int i = 0; i < 8; i++) {
            if (scores[0][i] == 2){
                for (int spot : TicTacToe.map.get(i)) {
                    if(!TicTacToe.isOccupiedArr(spot)){
                        System.out.println("Making move level \"medium\"");
                        return spot;
                    }
                }
            } else if(scores[1][i] == 2){
                for (int spot : TicTacToe.map.get(i)) {
                    if(!TicTacToe.isOccupiedArr(spot)){
                        System.out.println("Making move level \"medium\"");
                        return spot;
                    }
                }
            }
        }
        int x = random.nextInt(9);
        while (TicTacToe.isOccupiedArr(x)) {
            x = random.nextInt(3);
        }
        System.out.println("Making move level \"medium\"");
        return x;
    }

    /*@Override
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
    }*/
}
