import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K;
    static int N;
    static int[] wires;

    public static boolean checker(long target) {
        int count = 0;
        for (int wire : wires) {
//            if (target > wire) break;
            count += (wire / target);
        }
        return count >= N;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        K = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        wires = new int[K];
        for (int i = 0; i < K; i++) {
            wires[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(wires);
        long start = 1;
        long end = wires[K - 1] + 1;

        while (start != end) {
            long mid = (start + end) / 2;
            if (checker(mid)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        System.out.println(start);
    }
}