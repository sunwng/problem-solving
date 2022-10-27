package bj_1446;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int totalNum = Integer.parseInt(temp[0]);
        int L = Integer.parseInt(temp[1]);
        PriorityQueue<int[]> queue = new PriorityQueue<>((int[] o1, int[] o2) -> o1[1] - o2[1]);
        for (int i = 0; i < totalNum; i++) {
            int[] tempArr = new int[3];
            temp = br.readLine().split(" ");
            tempArr[0] = Integer.parseInt(temp[0]);
            tempArr[1] = Integer.parseInt(temp[1]);
            tempArr[2] = Integer.parseInt(temp[2]);
            if (tempArr[1] <= L) queue.add(tempArr);
        }
        int[] dp = new int[L+1];
        for (int i = 1; i <= L ; i++) {
            dp[i] = i;
        }
        int queLen = queue.size();
        for (int i = 0; i < queLen; i++) {
            int[] curArr = queue.poll();
            int tempNum = dp[curArr[0]] + curArr[2];
            if (tempNum < dp[curArr[1]]) {
                int diff = tempNum - dp[curArr[1]];
                dp[curArr[1]] = tempNum;
                for (int j = curArr[1]+1; j <= L; j++) {
                    dp[j] += diff;
                }
            }
        }
        System.out.println(dp[L]);
    }
}