package bj_1253;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalNum = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> repo = new HashMap<>();
        int[] repoArr = new int[totalNum];
        int curNum;
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < totalNum; i++) {
            curNum = Integer.parseInt(temp[i]);
            repoArr[i] = curNum;
            if (repo.get(curNum) == null) {
                repo.put(curNum, 1);
            } else {
                repo.compute(curNum, (k, v) -> v + 1);
            }
        }
        int result = 0;
        int sum;
        for (int i = 0; i < totalNum - 1; i++) {
            for (int j = i + 1; j < totalNum; j++) {
                sum = repoArr[i] + repoArr[j];
                if (repo.containsKey(sum)) {
                    if (repoArr[i] == 0 & repoArr[j] == 0) {
                        if (repo.get(sum) >= 3) {
                            result += repo.get(sum);
                            repo.remove(sum);
                        }
                    } else if (repoArr[i] == 0 || repoArr[j] == 0) {
                        if (repo.get(sum) >= 2) {
                            result += repo.get(sum);
                            repo.remove(sum);
                        }
                    } else {
                        result += repo.get(sum);
                        repo.remove(sum);
                    }
                }
            }
        }
        System.out.println(result);
    }
}
