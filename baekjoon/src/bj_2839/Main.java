package bj_2839;

import java.util.*;

public class Main {
    int sugarChecker (int weight) {
        int iterationIndex = (int) weight / 5;
        int minNum = -1;
        for (int i=0; i < iterationIndex+1; i++) {
            int weight_minus_5 = weight - (5 * i);
            if ((weight_minus_5 % 3) == 0) {
                int j = (int) weight_minus_5 / 3;
                int temp = i + j;
                if (minNum == -1) {
                    minNum = temp;
                } else if (temp < minNum) {
                    minNum = temp;
                }
            }
        }
        return minNum;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test_num = sc.nextInt();

        Main solution = new Main();
        int result = solution.sugarChecker(test_num);
        System.out.println(result);
    }
}
