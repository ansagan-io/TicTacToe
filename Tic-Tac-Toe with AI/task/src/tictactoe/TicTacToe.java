package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean turn = true;
    private static final char[][] layoutMatrix = new char[3][3];
    private static Player player1;
    private static Player player2;

    public static void startApp(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                layoutMatrix[i][j] = ' ';
            }
        }

        String command = "";
        String[] commands = command.split(" ");
        while (commands.length != 3) {
            System.out.print("Input command: ");
            command = scanner.nextLine();
            commands = command.split(" ");
            if (commands.length != 3 || !commands[0].equals("start")){
                System.out.println("Bad parameters!");
            }

        }


        switch (commands[0]) {
            case "exit": System.exit(0);
            case "start": startGame(commands[1], commands[2]);
            default: System.exit(0);
        }
    }

    private static void startGame(String firstPlayer, String secondPlayer) {
        player1 = createPlayer(firstPlayer, 'X');
        player2 = createPlayer(secondPlayer, 'O');
        printLayout();
        enterCoordinate();
    }

    private static Player createPlayer(String player, char sign) {
        Player player3 = null;
        switch (player) {
            case "user": player3 = new User(sign);
                break;
            case "easy": player3 = new EasyLevel(sign);
                break;
            default: System.exit(0);
        }
        return player3;
    }

    private static void enterCoordinate() {
        int[] coors;
        if (turn) {
            coors = player1.getXY();
            turn = false;
            acceptCoordinates(coors[0], coors[1], player1.getSign());

        } else {
            coors = player2.getXY();
            turn = true;
            acceptCoordinates(coors[0], coors[1], player2.getSign());

        }
        printLayout();
        checkWhoWin();
    }

    public static boolean isOutOfOneAndThree(int x, int y) {
        return x < 0 || x > 2 || y < 0 || y > 2;
    }

    public static boolean isOccupied(int x, int y) {
        return layoutMatrix[x][y] != ' ';
    }


    private static void acceptCoordinates(int x, int y, char sign) {
        layoutMatrix[x][y] = sign;
    }

    private static void checkWhoWin() {
        int[] eightLineScoresOfX = countScores('X');
        int[] eightLineScoresOfO = countScores('O');
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            sum += eightLineScoresOfX[i] + eightLineScoresOfO[i];
            if(eightLineScoresOfX[i] == 3 && eightLineScoresOfO[i] == 0) {
                System.out.println("X wins");
                System.exit(0);
            } else if (eightLineScoresOfX[i] == 0 && eightLineScoresOfO[i] == 3) {
                System.out.println("O wins");
                System.exit(0);
            }
        }
        if (sum == 24) {
            System.out.println("Draw");
            System.exit(0);
        } else {
            enterCoordinate();
        }
    }

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
