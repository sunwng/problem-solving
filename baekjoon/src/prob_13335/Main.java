package prob_13335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int w;
    static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        w = Integer.parseInt(str[1]);
        L = Integer.parseInt(str[2]);
        int result = 0;

        Queue<Integer> trucks = new LinkedList<>();
        str = br.readLine().split(" ");
        for (String strI : str) {
            trucks.add(Integer.parseInt(strI));
        }
        Queue<int[]> status = new LinkedList<>();

        result++;
        status.add(new int[]{trucks.poll(), 1});
        int weight = status.peek()[0];

        while (!status.isEmpty()) {
            if (status.peek()[1] == w) {
                weight -= status.poll()[0];
            }
            int size = status.size();
            for (int i = 0; i < size; i++) {
                int[] change = status.poll();
                change[1]++;
                status.add(change);
            }
            result++;
            if (trucks.isEmpty()) continue;
            if (weight + trucks.peek() <= L) {
                int newTruck = trucks.poll();
                weight += newTruck;
                status.add(new int[]{newTruck, 1});
            }

        }

        System.out.println(result);

    }
}
