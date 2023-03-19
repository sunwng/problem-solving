package bj_4673;

import java.util.*;

public class Main {
    ArrayList<Integer> checker() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i=1; i < 10001; i++) {
            int sum = i;
            String num_str = Integer.toString(i);
            String[] num_str_arr = num_str.split("");
            for (String j : num_str_arr) {
                sum += Integer.parseInt(j);
            }
            result.add(sum);
        }
//        return result.stream().mapToInt(i->i).toArray();
        return result;
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int test_num = sc.nextInt();

        Main solution = new Main();
        ArrayList<Integer> result = solution.checker();
        for (int i = 1; i < 10001; i++) {
            if (result.contains(i)) {
                continue;
            } else {
                System.out.println(i);
            }
        }
    }
}
