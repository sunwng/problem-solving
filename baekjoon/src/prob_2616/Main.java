package bj_2616;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        String[] peopleNumStr = br.readLine().split(" ");
        int[] peopleNum = new int[totalNum+1];
        for (int i = 1; i <= totalNum; i++) {
            peopleNum[i] = Integer.parseInt(peopleNumStr[i-1]);
        }

        int limit = Integer.parseInt(br.readLine());
        int[] sum = new int[totalNum+1];
        for (int i = 1; i <= totalNum; i++) {
            sum[i] = sum[i - 1] + peopleNum[i];
        }

        int[][] dp = new int[4][totalNum+1];
        for (int i = 1; i <= 3; i++) {
            for (int j = limit * i; j <= totalNum; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - limit] + sum[j] - sum[j - limit]);
            }
        }
        System.out.println(dp[3][totalNum]);


    }
}
