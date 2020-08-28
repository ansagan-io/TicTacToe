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
}
