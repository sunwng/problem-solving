package prob_1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int target;
    public static int N;
    public static String[] cand;
    public static String[] aeiou = {"a", "e", "i", "o", "u"};

    public static boolean check(String strIn) {
        int count = 0;
        for (String strI : aeiou) {
            if (strIn.contains(strI)) count++;
        }
        if (count == 0) return false;
        if (target - count < 2) return false;

        return true;
    }

    public static void find(String cur, int depth) {
        if (depth == N) {
            if (check(cur) && cur.length() == target) {
                System.out.println(cur);
            }
            return;
        }
        find(cur + cand[depth], depth + 1);
        find(cur, depth + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        target = Integer.parseInt(temp[0]);
        N = Integer.parseInt(temp[1]);
        cand = br.readLine().split(" ");
        Arrays.sort(cand);
        find("", 0);

    }
}