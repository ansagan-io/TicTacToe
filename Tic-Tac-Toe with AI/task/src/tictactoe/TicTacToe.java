package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean turn = true;
    private static final char[][] layoutMatrix = new char[3][3];
    private static final Random random = new Random();

    public static void startGame(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                layoutMatrix[i][j] = ' ';
            }
        }
        printLayout();
        enterCoordinate();
        /*System.out.print("Enter cells: ");
        String cellLayout = scanner.nextLine();
        while (cellLayout.length() != 9 || cellLayout.replaceAll("X", "").replaceAll("O", "").replaceAll("_", "").length() > 0) {
            System.out.print("Enter cells: ");
            cellLayout = scanner.nextLine();
        }*/
    }

    private static void enterCoordinate() {
        if (turn) {
            System.out.print("Enter the coordinates: ");
            String[] inputSplit = scanner.nextLine().split(" ", 2);
            int[] xy = new int[2];
            try {
                for (int i = 1; i >= 0; i--) {
                    xy[i] = Integer.parseInt(inputSplit[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                enterCoordinate();
            }
            if (isOutOfOneAndThree(xy[0], xy[1])) {
                System.out.println("Coordinates should be from 1 to 3!");
                enterCoordinate();
            } else if(isOccupied(xy[0], xy[1])){
                System.out.println("This cell is occupied! Choose another one!");
                enterCoordinate();
            } else {
                turn = false;
                acceptCoordinates(xy[0], xy[1]);
            }
        } else {
            AIEasyTurn();
        }

    }

    private static boolean isOutOfOneAndThree(int i, int i1) {
        return (i < 1 || i > 3) || (i1 < 1 || i1 > 3);
    }

    private static boolean isOccupied(int i, int i1) {
        return layoutMatrix[3-i][i-1] == 'X' || layoutMatrix[3-i1][i1-1] == 'O';
    }

    private static void AIEasyTurn() {
        int x = random.nextInt(4);
        int y = random.nextInt(4);
        while (isOutOfOneAndThree(x, y) || isOccupied(y, y)){
            x = random.nextInt(4);
            y = random.nextInt(4);
        }
        
    }

    private static void acceptCoordinates(int x, int y) {
//        int xCount = count(layoutMatrix, 'X');
//        int yCount = count(layoutMatrix, 'O');
//        if (xCount <= yCount) {
//            layoutMatrix[3-x][y-1] = 'X';
//        } else {
//            layoutMatrix[3-x][y-1] = 'O';
//        }
        layoutMatrix[3-x][y-1] = 'X';
        printLayout();
        checkWhoWin();
    }

    private static void checkWhoWin() {
        int[] eightLineScoresOfX = countScores('X');
        int[] eightLineScoresOfO = countScores('O');
        for (int i = 0; i < 8; i++) {
            if(eightLineScoresOfX[i] == 3 && eightLineScoresOfO[i] == 0) {
                System.out.println("X wins");
                System.exit(0);
            } else if (eightLineScoresOfX[i] == 0 && eightLineScoresOfO[i] == 3) {
                System.out.println("O wins");
                System.exit(0);
            }
        }
        if (count() == 0) {
            System.out.println("Draw");
            System.exit(0);
        } else {
            enterCoordinate();
        }
    }

    private static int count() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (layoutMatrix[i][j] == ' ') {
                    count++;
                }
            }
        }
        return count;
    }

/*    public static char[][] parseCellLayout(String cellLayout) {
        char[][] temp = new char[3][3];
        short count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cellLayout.charAt(count) == '_') {
                    temp[i][j] = ' ';
                } else {
                    temp[i][j] = cellLayout.charAt(count);
                }
                count++;
            }
        }
        return temp;
    }*/

    public static void printLayout(){
        System.out.println("---------");
        for (char[] first:layoutMatrix) {
            System.out.print("| ");
            for (char chere:first) {
                System.out.print(chere + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static int[] countScores(char x) {
        int[] countedLine = new int[8];
        int length = 0;
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (layoutMatrix[i][j] == x) {
                    count++;
                }
            }
            countedLine[length] = count;
            count = 0;
            length++;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (layoutMatrix[j][i] == x) {
                    count++;
                }
            }
            countedLine[length] = count;
            count = 0;
            length++;
        }

        for (int i = 0; i < 3; i++) {
            if (layoutMatrix[i][i] == x) {
                count++;
            }
        }
        countedLine[length] = count;
        length++;
        count = 0;
        for (int i = 0; i < 3; i++) {
            if (layoutMatrix[i][2-i] == x) {
                count++;
            }
        }
        countedLine[length] = count;

        return countedLine;
    }
}
