package tictactoe;

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
    }

    @Override
    public int getXArr() {
        if(this.getSign() == 'X') {
            this.enemySign = 'O';
        }else {
            this.enemySign = 'X';
        }
        if (count > 2) {
            Move bestMove = findBestMove(TicTacToe.getLayoutArray());
            System.out.println("Making move level \"hard\"");
            return bestMove.index;
        } else {
            count++;
            int x = random.nextInt(9);
            while (TicTacToe.isOccupiedArr(x)) {
                x = random.nextInt(9);
            }
            System.out.println("Making move level \"hard\"");
            return x;
        }
    }

    private Move findBestMove(char[] board) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.index = -1;
        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = this.getSign();
                // compute evaluation function for this
                // move.
                int moveVal = minimax(board, 0, false);
                // Undo the move
                board[i] = ' ';
                // If the value of the current move is
                // more than the best value, then update
                // best/
                if (moveVal > bestVal) {
                    bestMove.index = i;
                    bestVal = moveVal;
                }
            }
        }
        return bestMove;
    }

    private int minimax(char[] board, int depth, boolean isMax) {
        int score = evaluate(board);
        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10) {
            return score - depth;
        }
        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10) {
            return score + depth;
        }
        // If there are no more moves and
        // no winner then it is a tie
        if (!isMovesLeft(board))
            return 0;
        // If this maximizer's move
        int best;
        if (isMax) {
            best = -1000;
            // Traverse all cells
            for (int i = 0; i < 9; i++) {
                // Check if cell is empty
                if (board[i] == ' ') {
                    // Make the move
                    board[i] = this.getSign();
                    // Call minimax recursively and choose
                    // the maximum value
                    best = Math.max(best, minimax(board, depth + 1, !isMax));
                    // Undo the move
                    board[i] = ' ';
                }
            }
        }
        // If this minimizer's move
        else {
            best = 1000;
            // Traverse all cells
            for (int i = 0; i < 9; i++) {
                // Check if cell is empty
                if (board[i] == ' ') {
                    // Make the move
                    board[i] = enemySign;
                    // Call minimax recursively and choose
                    // the minimum value
                    best = Math.min(best, minimax(board, depth + 1, !isMax));
                    // Undo the move
                    board[i] = ' ';
                }
            }
        }
        return best;
    }

    private int evaluate(char[] b) {
        int[][] scores = TicTacToe.countScoresArr('X', 'O');
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
        for (int i = 0; i < 3; i++)
            if (board[i] == ' ')
                return true;
        return false;
    }
}
