package bj_2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int solution(int[] numArr, int[] homePos) {
        int homeNum = numArr[0];
        int routNum = numArr[1];
        Arrays.sort(homePos);

        int left = 1;
        int right = homePos[homeNum-1] - homePos[0];
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int start = homePos[0];
            int count = 1;
            int distance = 0;
            for (int i = 1; i < homeNum; i++) {
                distance = homePos[i] - start;
                if (distance >= mid) {
                    count++;
                    start = homePos[i];
                }
            }
            if (count >= routNum) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();
        int[] numArr = Arrays.stream(num.split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] homePos = new int[numArr[0]];
        for (int i = 0; i < numArr[0]; i++) {
            homePos[i] = Integer.parseInt(br.readLine());
        }
        int answer = solution(numArr, homePos);

        System.out.println(answer);
    }
}
