import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long x  = scanner.nextLong();
        long y = scanner.nextLong();
        long product = 1;
        for (long i = x; i < y; i++) {
            product *= i;
        }
        System.out.println(product);
    }
}