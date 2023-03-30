package prob_2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] towers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        towers = new int[N + 1];
        String[] given = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            towers[i] = Integer.parseInt(given[i - 1]);
        }

        int[] answer = new int[N + 1];

        int prev = 0;
        int zeroCount = 0;
        Stack<Integer> stack = new Stack<>();
        stack.add(0);

        for (int i = 1; i <= N; i++) {
            if (towers[i] < towers[i - 1]) stack.add(i - 1);
            if (towers[i] > towers[stack.peek()]) {
                while (stack.size() != 1) {
                    if (towers[stack.peek()] > towers[i]) break;
                    stack.pop();
                }
            }
            answer[i] = stack.peek();
            zeroCount += answer[i];
        }
        if (zeroCount == 0) {
            System.out.println(0);
            return;
        }
        StringJoiner sj = new StringJoiner(" ");
        for (int i = 1; i <= N; i++) {
            sj.add(Integer.toString(answer[i]));
        }
        System.out.println(sj);
    }
}
