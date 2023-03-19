package bj_11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        int[] rep = new int[totalNum];
        int[] dpInc = new int[totalNum];
        int[] dpDec = new int[totalNum];
        int[] result = new int[totalNum];

        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < totalNum; i++) {
            rep[i] = Integer.parseInt(temp[i]);
        }

        for (int i = 0; i < totalNum; i++) {
            dpInc[i] = 1;
            dpDec[totalNum-1-i] = 1;

            for (int j = 0; j < i; j++) {
                if (rep[i] > rep[j] && dpInc[i] <= dpInc[j]) {
                    dpInc[i] = dpInc[j] + 1;
                }
            }
            for (int j = totalNum - 1; j > totalNum - 1 - i; j--) {
                if (rep[totalNum-1-i] > rep[j] && dpDec[totalNum-1-i] <= dpDec[j]) {
                    dpDec[totalNum-1-i] = dpDec[j] + 1;
                }
            }
        }
        for (int i = 0; i < totalNum; i++) {
            result[i] = dpInc[i] + dpDec[i];
        }
        Arrays.sort(result);
        System.out.println(result[totalNum - 1] - 1);
    }
}
