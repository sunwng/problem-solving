package bj_14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int H = Integer.parseInt(input[0]);
        int W = Integer.parseInt(input[1]);
        int[] repo = new int[W];
        int result = 0;
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < W; i++) {
            repo[i] = Integer.parseInt(temp[i]);
        }
        for (int i = 1; i < W - 1; i++) {
            int left = 0;
            int right = 0;
            for (int j = 0; j < i; j++) {
                left = Math.max(left, repo[j]);
            }
            for (int j = i + 1; j < W; j++) {
                right = Math.max(right, repo[j]);
            }
            if (repo[i] < left && repo[i] < right) {
                result += (Math.min(left, right) - repo[i]);
            }
        }
        System.out.println(result);
    }
}