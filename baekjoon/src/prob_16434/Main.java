package prob_16434;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] rooms;

    static boolean check(long hp) {
        long curHp = hp;
        long curAk = K;
        for (int i = 0; i < N; i++) {
            if (rooms[i][0] == 1) {
                int count = (int) Math.ceil((double) rooms[i][2] / curAk) - 1;
                curHp -= ((long) rooms[i][1] * count);
                if (curHp < 1) return false;
            } else {
                curAk += rooms[i][1];
                curHp = Math.min(curHp + rooms[i][2], hp);
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        N = Integer.parseInt(given[0]);
        K = Integer.parseInt(given[1]);
        rooms = new int[N][3];

        for (int i = 0; i < N; i++) {
            given = br.readLine().split(" ");
            rooms[i][0] = Integer.parseInt(given[0]);
            rooms[i][1] = Integer.parseInt(given[1]);
            rooms[i][2] = Integer.parseInt(given[2]);
        }

        long left = 1;
        long right = Long.MAX_VALUE;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (check(mid)) right = mid - 1;
            else left = mid + 1;
        }
        System.out.println(left);

    }
}