package bj_1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalNum = Integer.parseInt(br.readLine());
        int[][] repo = new int[totalNum][2];
        for (int i = 0; i < totalNum; i++) {
            String[] temp = br.readLine().split(" ");
            repo[i][0] = Integer.parseInt(temp[0]);
            repo[i][1] = Integer.parseInt(temp[1]);
        }

        Arrays.sort(repo, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        int result = 0;
        int min = 0;
        for (int i = 0; i < totalNum; i++) {
            if (repo[i][0] >= min) {
                result++;
                min = repo[i][1];
            }
        }
        System.out.println(result);
    }
}
