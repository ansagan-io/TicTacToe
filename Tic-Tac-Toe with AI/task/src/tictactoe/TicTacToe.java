package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startGame(){
        System.out.print("Enter cells: ");
        String cellLayout = scanner.nextLine();
        while (cellLayout.length() != 9 || cellLayout.replaceAll("X", "").replaceAll("O", "").replaceAll("_", "").length() > 0) {
            System.out.print("Enter cells: ");
            cellLayout = scanner.nextLine();
        }
        char[][] layoutMatrix = parseCellLayout(cellLayout);
        printLayout(layoutMatrix);
        enterCoordinate(layoutMatrix);
    }

    public static void checkWhoWin(char[][] layoutMatrix) {
        int[] eightLineScoresOfX = countScores(layoutMatrix, 'X');
        int[] eightLineScoresOfO = countScores(layoutMatrix, 'O');
        for (int i = 0; i < 8; i++) {
            if(eightLineScoresOfX[i] == 3 && eightLineScoresOfO[i] == 0) {
                System.out.println("X wins");
                System.exit(0);
            } else if (eightLineScoresOfX[i] == 0 && eightLineScoresOfO[i] == 3) {
                System.out.println("O wins");
                System.exit(0);
            }
        }
        if (count(layoutMatrix, ' ') == 0) {
            System.out.println("Draw");
            System.exit(0);
        } else {
            System.out.println("Game not finished");
        }
    }

    private static void enterCoordinate(char[][] layoutMatrix) {
        System.out.print("Enter the coordinates: ");
        String[] inputSplit = scanner.nextLine().split(" ", 2);
        int[] xy = new int[2];
        try {
            for (int i = 0; i < 2; i++) {
                xy[i] = Integer.parseInt(inputSplit[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            enterCoordinate(layoutMatrix);
        }

        if ((xy[0] < 1 || xy[0] > 3) || (xy[1] < 1 || xy[1] > 3) ) {
            System.out.println("Coordinates should be from 1 to 3!");
            enterCoordinate(layoutMatrix);
        }

        if (layoutMatrix[3-xy[1]][xy[0]-1] == 'X' || layoutMatrix[3-xy[1]][xy[0]-1] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            enterCoordinate(layoutMatrix);
        }
        acceptCoordinates(layoutMatrix, xy[1], xy[0]);
    }

    private static void acceptCoordinates(char[][] layoutMatrix, int x, int y) {
        int xCount = count(layoutMatrix, 'X');
        int yCount = count(layoutMatrix, 'O');
        if (xCount <= yCount) {
            layoutMatrix[3-x][y-1] = 'X';
        } else {
            layoutMatrix[3-x][y-1] = 'O';
        }
        printLayout(layoutMatrix);
        checkWhoWin(layoutMatrix);
    }

    private static int count(char[][] layoutMatrix, char x) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (layoutMatrix[i][j] == x) {
                    count++;
                }
            }
        }
        return count;
    }

    public static char[][] parseCellLayout(String cellLayout) {
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
    }

    public static void printLayout(char[][] matrix){
        System.out.println("---------");
        for (char[] first:matrix) {
            System.out.print("| ");
            for (char chere:first) {
                System.out.print(chere + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static int[] countScores(char[][] layoutMatrix, char x) {
        int[] countedLine = new int[8];
        int length = 0;
        for (int i = 0; i < 3; i++) {
            int count = 0;
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
            int count = 0;
            for (int j = 0; j < 3; j++) {
                if (layoutMatrix[j][i] == x) {
                    count++;
                }
            }
            countedLine[length] = count;
            count = 0;
            length++;
        }

        int count = 0;
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
