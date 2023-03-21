package prob_14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int firstIdx = 0;
    static int lastIdx = 7;
    static int leftIdx = 6;
    static int rightIdx = 2;
    static List<List<Integer>> gears;

    public static int calculate() {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            if (gears.get(i).get(0) == 1) {
                result += Math.pow(2, i);
            }
        }
        return result;
    }

    public static void rotate(int target, int direction) {
        if (direction == 1) {
            int temp = gears.get(target).get(lastIdx);
            gears.get(target).remove(lastIdx);
            gears.get(target).add(0, temp);
        } else {
            int temp = gears.get(target).get(firstIdx);
            gears.get(target).remove(firstIdx);
            gears.get(target).add(temp);
        }
    }

    public static void checkLeft(int target, int direction) {
        if (target == 0) return;
        if ((gears.get(target).get(leftIdx) + gears.get(target - 1).get(rightIdx)) == 1) {
            checkLeft(target - 1, direction * -1);
            rotate(target - 1, direction);
        }
    }

    public static void checkRight(int target, int direction) {
        if (target == 3) return;
        if ((gears.get(target).get(rightIdx) + gears.get(target + 1).get(leftIdx)) == 1) {
            checkRight(target + 1, direction * -1);
            rotate(target + 1, direction);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gears = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Integer> gear = new LinkedList<>();
            String[] init = br.readLine().split("");
            for (String dir : init) {
                gear.add(Integer.parseInt(dir));
            }
            gears.add(gear);
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] given = br.readLine().split(" ");
            int target = Integer.parseInt(given[0]) - 1;
            int direction = Integer.parseInt(given[1]);
            checkLeft(target, direction * -1);
            checkRight(target, direction * -1);
            rotate(target, direction);
        }
        System.out.println(calculate());
    }
}