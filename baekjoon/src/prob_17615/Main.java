package prob_17615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalNum = Integer.parseInt(br.readLine());
        String balls = br.readLine();
        String[] ballsArr = balls.split("");
        int[] result = new int[2];
        String pivot;
        String cur;
        boolean check;
        int blue = 0, red = 0;

        pivot = ballsArr[0];
        check = false;
        for (int i = 1; i < totalNum; i++) {
            cur = ballsArr[i];
            if (pivot.equals(cur) && !check) continue;
            else {
                check = true;
                if (cur.equals("B")) {
                    blue++;
                } else {
                    red++;
                }
            }
        }
        result[0] = Math.min(blue, red);
        blue = 0;
        red = 0;
        check = false;
        pivot = ballsArr[totalNum-1];
        for (int i = totalNum-2; i >= 0; i--) {
            cur = ballsArr[i];
            if (pivot.equals(cur) && !check) continue;
            else {
                check = true;
                if (cur.equals("B")) {
                    blue++;
                } else {
                    red++;
                }
            }
        }
        result[1] = Math.min(blue, red);
        System.out.println(Math.min(result[0], result[1]));
    }
}