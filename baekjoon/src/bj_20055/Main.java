package bj_20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static int twoN;
    public static int K;
    public static int[] remain;
    public static boolean[] robot;
    public static int idxUp;
    public static int idxDown;
    public static Queue<Integer> queue;

    public static int check() {
        int out = 0;
        for (int remainI : remain) {
            if (remainI == 0) out++;
        }
        return out;
    }

    public static void rotate() {
        if (idxUp == 0) {
            idxUp = 2 * N - 1;
        } else {
            idxUp--;
        }
        if (idxDown == 0) {
            idxDown = 2 * N - 1;
        } else {
            idxDown--;
        }
        down();
    }

    public static void move() {
        int iterateNum = queue.size();
        for (int i = 0; i < iterateNum; i++) {
            int cur = queue.poll();
            int front = (cur + 1) % twoN;
            if (!robot[front] && (remain[front] > 0)) {
                queue.add(front);
                robot[cur] = false;
                robot[front] = true;
                remain[front]--;
            } else {
                queue.add(cur);
            }
        }
        down();
    }

    public static void down() {
        if (robot[idxDown]) {
            robot[idxDown] = false;
            queue.remove(idxDown);
        }
    }

    public static void up() {
        if (!robot[idxUp] && remain[idxUp] > 0) {
            robot[idxUp] = true;
            remain[idxUp]--;
            queue.add(idxUp);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        N = Integer.parseInt(nk[0]);
        K = Integer.parseInt(nk[1]);
        twoN = 2 * N;
        remain = new int[twoN];
        robot = new boolean[twoN];
        String[] remainStr = br.readLine().split(" ");
        for (int i = 0; i < twoN; i++) {
            remain[i] = Integer.parseInt(remainStr[i]);
        }
        idxUp = 0;
        idxDown = N - 1;
        queue = new LinkedList<>();
        int answer = 0;

        while (check() < K) {
            rotate();
            move();
            up();
            answer++;
        }
        System.out.println(answer);
    }
}
