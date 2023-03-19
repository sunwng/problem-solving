package prob_1629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static long calculate(long A, long B, long C) {
        if (B == 1) {
            return A % C;
        }
        long temp = calculate(A, B / 2, C);
        if (B % 2 == 0) {
            return temp * temp % C;
        } else {
            return (temp * temp % C) * A % C;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long A = Integer.parseInt(input[0]);
        long B = Integer.parseInt(input[1]);
        long C = Integer.parseInt(input[2]);

        System.out.println(calculate(A, B, C));
    }
}