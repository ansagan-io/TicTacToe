package tictactoe;

import java.util.Arrays;
import java.util.Random;

public class HardLevel extends Player {
    private char enemySign;
    private final Random random = new Random();
    private int count = 0;

    public HardLevel(char sign) {
        super(sign);
    }

    static class Move {
        int index;
        int score;

        public static Move max(Move best, Move minimax) {
            if (best.score > minimax.score) {
                return best;
            }
            else {
                return minimax;
            }
        }

        public static Move min(Move best, Move minimax) {
            if (best.score < minimax.score) {
                return best;
            }
            else {
                return minimax;
            }
        }
    }

    @Override
    public int getXArr() {
        if(this.getSign() == 'X') {
            this.enemySign = 'O';
        }else {
            this.enemySign = 'X';
        }
        if (this.count > 1) {
            char[] newArray = Arrays.copyOfRange(TicTacToe.getLayoutArray(), 0, TicTacToe.getLayoutArray().length);
            Move bestMove = findBestMove(newArray);
            System.out.println("Making move level \"hard\"");
            return bestMove.index;
        } else {
            int x = random.nextInt(9);
            while (TicTacToe.isOccupiedArr(x)) {
                x = random.nextInt(9);
            }
            System.out.println("Making move level \"hard\"");
            this.count++;
            return x;
        }
    }

    private Move findBestMove(char[] board) {
        int bestVal = -1000;
        Move bestMove = new Move();
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = this.getSign();

                Move indexScoreAndDepth = minimax(board, 0, false);

                indexScoreAndDepth.index = i;
                // Undo the move
                board[i] = ' ';
                // If the value of the current move is
                // more than the best value, then update
                // best/
                if (indexScoreAndDepth.score > bestVal) {
                    bestVal = indexScoreAndDepth.score;
                    bestMove = indexScoreAndDepth;
                }
            }
        }

        return bestMove;
    }

    private Move minimax(char[] board, int depth, boolean isMax) {
        int score = evaluate(board);
        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10) {
            Move move = new Move();
            move.score = score - depth;
            return move;
        }
        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10) {
            Move move = new Move();
            move.score = score + depth;
            return move;
        }
        // If there are no more moves and
        // no winner then it is a tie
        if (!isMovesLeft(board)) {
            Move move = new Move();
            move.score = 0;
            return move;
        }
        // If this maximizer's move
        Move best = new Move();
        if (isMax) {
            best.score = -10000;
            // Traverse all cells
            for (int i = 0; i < 9; i++) {
                // Check if cell is empty
                if (board[i] == ' ') {
                    // Make the move
                    board[i] = this.getSign();
                    // Call minimax recursively and choose
                    // the maximum value
                    best = Move.max(best, minimax(board, depth + 1, false));
                    // Undo the move
                    board[i] = ' ';
                }
            }
        }
        // If this minimizer's move
        else {
            best.score = 10000;
            // Traverse all cells
            for (int i = 0; i < 9; i++) {
                // Check if cell is empty
                if (board[i] == ' ') {
                    // Make the move
                    board[i] = enemySign;
                    // Call minimax recursively and choose
                    // the minimum value
                    best = Move.min(best, minimax(board, depth + 1, true));
                    // Undo the move
                    board[i] = ' ';
                }
            }
        }
        return best;
    }

    private int evaluate(char[] b) {
        int[][] scores = TicTacToe.countScoresArr(b,'X', 'O');
        for (int i = 0; i < 8; i++) {
            if (scores[0][i] == 3) {
                return +10;
            } else if (scores[1][i] == 3) {
                return -10;
            }
        }
        // Else if none of them have won then return 0
        return 0;
    }

    private boolean isMovesLeft(char[] board) {
        for (int i = 0; i < 9; i++)
            if (board[i] == ' ')
                return true;
        return false;
    }
}
