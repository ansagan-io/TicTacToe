package tictactoe;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicTacToe {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean turn = true;
    private static final char[][] layoutMatrix = new char[3][3];

    private static Player player1;
    private static Player player2;

    static Map<Integer, int[][]> coordinates = new HashMap<>();

    public static void startApp(){
        coordinates = createCoordinates();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                layoutMatrix[i][j] = ' ';
            }
        }

        while (true) {
            System.out.print("Input command: ");
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            if (commands[0].equals("exit")) {
                System.exit(0);
            }
            if (commands.length != 3 || !commands[0].equals("start")){
                System.out.println("Bad parameters!");
                continue;
            }
            startGame(commands[1], commands[2]);
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
            case "medium": player3 = new MediumLevel(sign);
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

    static int[] countScores(char x) {
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

    private static Map<Integer,int[][]> createCoordinates() {
        Map<Integer,int[][]> map = new HashMap<>();
        int[][] zero = {{0, 0},{0, 1},{0, 2}};
        map.put(0, zero);
        int[][] first = {{1, 0},{1, 1},{1, 2}};
        map.put(1, first);
        int[][] second = {{2, 0},{2, 1},{2, 2}};
        map.put(2, second);
        int[][] third = {{0, 0},{1, 0},{2, 0}};
        map.put(3, third);
        int[][] forth = {{0, 1},{1, 1},{2, 1}};
        map.put(4, forth);
        int[][] fifth = {{0, 2},{1, 2},{2, 2}};
        map.put(5, fifth);
        int[][] sixth = {{0, 0},{1, 1},{2, 2}};
        map.put(6, sixth);
        int[][] seventh = {{0, 2},{1, 1},{2, 0}};
        map.put(7, seventh);
        return map;
    }
}
