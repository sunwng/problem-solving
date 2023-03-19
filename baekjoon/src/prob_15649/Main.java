package prob_15649;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void dfs(int totalNum, int limitNum, int[] visited, int[] resultArr) {
        if (Arrays.stream(visited).sum() == limitNum) {
            StringBuilder stb = new StringBuilder();
            for (int i = 0; i < resultArr.length; i++) {
                stb.append(resultArr[i]);
                if (i < resultArr.length - 1) stb.append(" ");
            }
            System.out.println(stb);
            return;
        }
        int[] temp;
        for (int i = 1; i <= totalNum; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            temp = resultArr;
            temp[Arrays.stream(visited).sum()-1] = i;
            dfs(totalNum, limitNum, visited, temp);
            visited[i] = 0;
        }

    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int totalNum = Integer.parseInt(input[0]);
        int limitNum = Integer.parseInt(input[1]);
        dfs(totalNum, limitNum, new int[totalNum + 1], new int[limitNum]);
    }
}
