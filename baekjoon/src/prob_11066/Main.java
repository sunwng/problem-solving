package prob_11066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testNum = Integer.parseInt(br.readLine());
        int[][] dp;
        int[] rep;
        int[] sum;
        for (int i = 0; i < testNum; i++) {
            int totalNum = Integer.parseInt(br.readLine());
            String[] numArr = br.readLine().split(" ");
            rep = new int[totalNum+1];
            sum = new int[totalNum+1];
            dp = new int[totalNum+1][totalNum+1];
            for (int j = 1; j <= totalNum; j++) {
                rep[j] = Integer.parseInt(numArr[j-1]);
                sum[j] = sum[j-1] + rep[j];
            }
            for (int j = 1; j < totalNum; j++) {
                for (int k = 1; k+j <= totalNum; k++) {
                    int from = k;
                    int to = k + j;
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int l = from; l < to; l++) {
                        dp[from][to] = Math.min(dp[from][to], dp[from][l] + dp[l+1][to] + sum[to] - sum[from-1]);
                    }
                }
            }
            System.out.println(dp[1][totalNum]);
        }
    }
}
