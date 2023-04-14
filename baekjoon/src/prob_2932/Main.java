package prob_2932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Target {
        int idx;
        int curX;
        int curY;
        int nextX;
        int nextY;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        int N = Integer.parseInt(given[0]);
        int K = Integer.parseInt(given[1]);
        List<Target> cmds = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            given = br.readLine().split(" ");
            int num = Integer.parseInt(given[0]);
            int x = Integer.parseInt(given[1]) - 1;
            int y = Integer.parseInt(given[2]) - 1;
            Target target = new Target();
            target.idx = num;
            target.curX = (num - 1) / N;
            target.curY = (num - 1) % N;
            target.nextX = x;
            target.nextY = y;
            cmds.add(target);
        }

        for (int i = 0; i < K; i++) {
            Target now = cmds.get(i);
            int moveY = now.nextY - now.curY;
            if (moveY < 0) moveY += N;
            int moveX = now.nextX - now.curX;
            if (moveX < 0) moveX += N;

            System.out.println(moveX + moveY);

            for (int j = i + 1; j < K; j++) {
                Target next = cmds.get(j);
                if (next.curX == now.curX) next.curY = (next.curY + moveY) % N;
                if (next.curY == now.nextY) next.curX = (next.curX + moveX) % N;
            }
        }


    }
}