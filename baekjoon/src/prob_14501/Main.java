package prob_14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int endDay = Integer.parseInt(br.readLine());
        int[][] repo = new int[endDay+1][2];
        int[] dp = new int[endDay+2];
        for (int i = 1; i <= endDay; i++) {
            String[] temp = br.readLine().split(" ");
            repo[i][0] = Integer.parseInt(temp[0]);
            repo[i][1] = Integer.parseInt(temp[1]);
        }
        int expDay;
        for (int i = endDay; i > 0; i--){
            expDay = i + repo[i][0];
            if (expDay <= endDay + 1) {
                dp[i] = Math.max(repo[i][1] + dp[expDay], dp[i+1]);
            } else {
                dp[i] = dp[i+1];
            }
        }
        System.out.println(dp[1]);
    }
}