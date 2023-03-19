package bj_1027;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N <= 2) {
            System.out.println(N - 1);
            return;
        }
        double[] B = new double[N];
        String[] tempStr = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            B[i] = Double.parseDouble(tempStr[i]);
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int result = 0;
            double slope = 0;
            for (int j = i - 1; j >= 0; j--) {
                double temp = (B[i] - B[j]) / (i - j);
                if (j == i - 1) {
                    slope = temp;
                    result++;
                }
                if (temp < slope) {
                    slope = temp;
                    result++;
                }
            }
            slope = 0;
            for (int j = i + 1; j < N; j++) {
                double temp = (B[i] - B[j]) / (i - j);
                if (j == i + 1) {
                    slope = temp;
                    result++;
                }
                if (temp > slope) {
                    slope = temp;
                    result++;
                }
            }
            answer = Math.max(result, answer);
        }



        System.out.println(answer);

    }
}
