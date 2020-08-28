package tictactoe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User extends Player{
    private final Scanner scanner= new Scanner(System.in);
    final Map<int[], Integer> coordinateConvert = new HashMap<>();

    public User(char sign) {
        super(sign);
    }

    @Override
    public int getXArr() {
        setCoordinateConvert();

        while (true) {
            int[] xy = new int[2];
            while (true) {
                try {
                    System.out.print("Enter the coordinates: ");
                    String[] inputSplit = scanner.nextLine().split(" ", 2);
                    if (inputSplit.length != 2) {
                        System.out.println("Coordinate should be at least two value");
                        continue;
                    }
                    for (int i = 0; i < 2; i++) {
                        xy[i] = Integer.parseInt(inputSplit[i]);
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                }
            }
            int x = getValue(coordinateConvert, xy);
            if (isOutOfOneAndThree(xy[0], xy[1])) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if(TicTacToe.isOccupiedArr(x)){
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            return x;
        }
    }

    private int getValue(Map<int[], Integer> coordinateConvert, int[] xy) {
        for (Map.Entry<int[], Integer> pairs:coordinateConvert.entrySet()) {
            if (Arrays.equals(pairs.getKey(), xy)) {
                return pairs.getValue();
            }
        }
        return 0;
    }

    private boolean isOutOfOneAndThree(int x, int y) {
        return x < 1 || x > 3 || y < 1 || y > 3;
    }

    /*@Override
    public int[] getXY() {
        while (true) {
            int[] xy = new int[2];
            while (true) {
                try {
                    System.out.print("Enter the coordinates: ");
                    String[] inputSplit = scanner.nextLine().split(" ", 2);
                    if (inputSplit.length != 2) {
                        System.out.println("Coordinate should be at least two value");
                        continue;
                    }
                    for (int i = 0; i < 2; i++) {
                        xy[i] = Integer.parseInt(inputSplit[i]);
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                }
            }
            int x = 3 - xy[1];
            int y= xy[0] - 1;
            if (TicTacToe.isOutOfOneAndThree(x, y)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if(TicTacToe.isOccupied(x, y)){
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            return new int[]{x, y};
        }
    }*/

    public void setCoordinateConvert(){
        for (int i = 0; i < 3; i++) {
            int[] temp = new int[2];
            temp[0] = i + 1;
            temp[1] = 3;
            coordinateConvert.put(temp, i);
        }
        for (int i = 3; i < 6; i++) {
            int[] temp = new int[2];
            temp[0] = i - 2;
            temp[1] = 2;
            coordinateConvert.put(temp, i);
        }
        for (int i = 6; i < 9; i++) {
            int[] temp = new int[2];
            temp[0] = i - 5;
            temp[1] = 1;
            coordinateConvert.put(temp, i);
        }
    }
}
