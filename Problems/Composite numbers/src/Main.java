import java.util.Scanner;

public class Main {

    public static boolean isComposite(long number) {
        long[] num = {2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 17, 19, 23, 27, 29, 31, 37, 41};
        if (number == 1) {
            return false;
        }
        for (long nu : num) {
            if ((number % nu == 0) && (number != nu)) {
                return true;
            }
        }
        return false;

    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final long number = scanner.nextLong();
        System.out.println(isComposite(number));
    }
}