import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(method(8));
    }

    public static int method(int n) {
        if (n == 0) {
            return 1;
        } else {
            return 2 * method(n - 1);
        }
    }
}