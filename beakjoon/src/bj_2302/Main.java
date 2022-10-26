package bj_2302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalNum = Integer.parseInt(br.readLine());
        int vipNum = Integer.parseInt(br.readLine());
        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= 40; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        if (vipNum == 0) {
            System.out.println(dp[totalNum]);
        } else {
            int start = 0;
            int result = 1;
            int vip;
            for (int i = 0; i < vipNum; i++) {
                vip = Integer.parseInt(br.readLine());
                result *= dp[vip-start-1];
                start = vip;
            }
            if (start != totalNum) {
                result *= dp[totalNum-start];
            }
            System.out.println(result);
        }
    }
}