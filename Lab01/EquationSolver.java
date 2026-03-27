import java.util.Scanner;

public class EquationSolver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Solve ax + b = 0");
        double a = sc.nextDouble();
        double b = sc.nextDouble();

        if (a == 0) {
            if (b == 0) System.out.println("Infinite solutions");
            else System.out.println("No solution");
        } else {
            System.out.println("x = " + (-b / a));
        }

        System.out.println("Solve ax^2 + bx + c = 0");
        double a2 = sc.nextDouble();
        double b2 = sc.nextDouble();
        double c2 = sc.nextDouble();

        double delta = b2*b2 - 4*a2*c2;

        if (a2 == 0) {
            System.out.println("Not quadratic");
        } else if (delta < 0) {
            System.out.println("No real root");
        } else if (delta == 0) {
            System.out.println("x = " + (-b2/(2*a2)));
        } else {
            double x1 = (-b2 + Math.sqrt(delta)) / (2*a2);
            double x2 = (-b2 - Math.sqrt(delta)) / (2*a2);
            System.out.println("x1 = " + x1 + ", x2 = " + x2);
        }

        sc.close();
    }
}