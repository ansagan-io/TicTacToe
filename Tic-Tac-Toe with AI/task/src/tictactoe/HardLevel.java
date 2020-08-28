package tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HardLevel extends Player {
    private int fc = 0;

    private char enemySign;

    public HardLevel(char sign) {
        super(sign);
    }

    @Override
    public int getXArr() {

        if(this.getSign() == 'X') {
            enemySign = 'O';
        }else {
            enemySign = 'X';
        }

        Map<String, Integer> bestSpot = minimax(TicTacToe.getLayoutArray(), this.getSign());

        return bestSpot.get("score");
    }

    private Map<String, Integer> minimax(char[] newBoard, char sign){
        fc++;

        int[] availableSpots = emptySpots(newBoard);

        if (winning(newBoard, enemySign)){
            return new HashMap<>(){
                {
                    put("score", -10);
                }
            };
        }

        else if (winning(newBoard, this.getSign())){
            return new HashMap<>(){
                {
                    put("score", 10);
                }
            };
        }
        else if (availableSpots.length == 0){
            return new HashMap<>(){
                {
                    put("score", 0);
                }
            };
        }

        ArrayList<Map<String, Integer>> moves = new ArrayList<>();

        for (int availableSpot : availableSpots) {
            //create an object for each and store the index of that spot that was stored as a number in the object's index key
            Map<String, Integer> move = new HashMap<>();
            move.put("index", (int) newBoard[availableSpot]);

            // set the empty spot to the current player
            newBoard[availableSpot] = sign;

            //if collect the score resulted from calling minimax on the opponent of the current player
            Map<String, Integer> result;
            if (sign == this.getSign()) {
                result = minimax(newBoard, this.enemySign);
            } else {
                result = minimax(newBoard, this.getSign());
            }
            move.put("score", result.get("score"));

            //reset the spot to empty
            newBoard[availableSpot] = Character.highSurrogate(move.get("index"));

            // push the object to the array
            moves.add(move);
        }

        // if it is the computer's turn loop over the moves and choose the move with the highest score
        int bestMove = 0;
        if(sign == this.getSign()){
            int bestScore = -10000;
            for(int i = 0; i < moves.size(); i++){
                if(moves.get(i).get("score") > bestScore){
                    bestScore = moves.get(i).get("score");
                    bestMove = i;
                }
            }
        }else {
        // else loop over the moves and choose the move with the lowest score
            int bestScore = 10000;
            for(int i = 0; i < moves.size(); i++){
                if(moves.get(i).get("score") < bestScore){
                    bestScore = moves.get(i).get("score");
                    bestMove = i;
                }
            }
        }

// return the chosen move (object) from the array to the higher depth
        return moves.get(bestMove);
    }

    private boolean winning(char[] board, char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }

    private int[] emptySpots(char[] newBoard) {
        int[] spots = new int[9];
        int length = 0;

        for (int i = 0; i < 9; i++) {
            if (newBoard[i] == ' ') {
                spots[length] = i;
                length++;
            }
        }
        return spots;
    }
}
