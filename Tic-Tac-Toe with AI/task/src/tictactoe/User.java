package tictactoe;

import java.util.Scanner;

public class User extends Player{
    private final Scanner scanner= new Scanner(System.in);

    public User(char sign) {
        super(sign);
    }

    @Override
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
    }
}
