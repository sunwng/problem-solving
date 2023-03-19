package prob_1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        int[] arr = new int [totalNum];
        int[] dp = new int[totalNum];
        String[] strArr = br.readLine().split(" ");
        int sum;
        for (int i = 0; i < totalNum; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }
        dp[0] = arr[0];
        for (int i = 1; i < totalNum; i++) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
        }


        Arrays.sort(dp);
        System.out.println(dp[totalNum-1]);
    }
}
