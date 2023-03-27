import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] test = new int[4][2];
        test[0] = new int[]{1, 2};
        test[1] = new int[]{7, 1};
        test[2] = new int[]{8, 9};
        test[3] = new int[]{2, 4};
        Arrays.sort(test, (int[] o1, int[] o2) -> o1[1] - o2[1]);
        for (int[] testi : test) {
            System.out.println(Arrays.toString(testi));
        }
    }
}