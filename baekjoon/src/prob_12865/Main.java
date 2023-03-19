package prob_12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String temp = br.readLine();
        String[] tempArr = temp.split(" ");
        int num = Integer.parseInt(tempArr[0]);
        int max = Integer.parseInt(tempArr[1]);
        int[][] dp = new int[num+1][max+1];
        int[][] repo = new int[num+1][2];
        int weight;
        int value;
        for (int i = 1; i <= num; i++) {
            temp = br.readLine();
            tempArr = temp.split(" ");
            repo[i][0] = Integer.parseInt(tempArr[0]);
            repo[i][1] = Integer.parseInt(tempArr[1]);
        }
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= max; j++) {
                if (j - repo[i][0] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j-repo[i][0]] + repo[i][1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[num][max]);
    }
}
