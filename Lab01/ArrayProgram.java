import java.util.*;

public class ArrayProgram {
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 3};

        Arrays.sort(arr);

        int sum = 0;
        for (int i : arr) sum += i;

        double avg = sum / (double) arr.length;

        System.out.println("Sorted: " + Arrays.toString(arr));
        System.out.println("Sum: " + sum);
        System.out.println("Avg: " + avg);
    }
}