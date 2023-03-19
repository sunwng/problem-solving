package bj_1978;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalNum = Integer.parseInt(br.readLine());
        String listNum_str = br.readLine();
        int[] listNum = Arrays.stream(listNum_str.split(" ")).mapToInt(Integer::parseInt).toArray();
        int primeCount = 0;
        for (int i = 0; i < totalNum; i++) {
            boolean flag = false;
            if (listNum[i] == 1) {
                continue;
            }
            for (int j = 2; j < listNum[i]; j++) {
                if (listNum[i] % j == 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            primeCount += 1;
        }

        System.out.println(primeCount);
    }
}
