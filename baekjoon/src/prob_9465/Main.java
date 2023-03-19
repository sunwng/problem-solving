package prob_9465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalNum = Integer.parseInt(br.readLine());
        int colNum;
        int[][] repo;
        int[][] dp;
        for (int i = 0; i < totalNum; i++) {
            colNum = Integer.parseInt(br.readLine());
            repo = new int[2][colNum+1];
            dp = new int[2][colNum+1];
            for (int j = 0; j < 2; j++) {
                String[] temp = br.readLine().split(" ");
                for (int k = 1; k <= temp.length; k++) {
                    repo[j][k] = Integer.parseInt(temp[k-1]);
                }
            }
            dp[0][1] = repo[0][1];
            dp[1][1] = repo[1][1];
            for (int j = 2; j <= colNum; j++) {
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + repo[0][j];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + repo[1][j];
            }
            System.out.println(Math.max(dp[0][colNum], dp[1][colNum]));

        }

    }
}
