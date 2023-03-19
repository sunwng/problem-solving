package prob_1065;

import java.util.*;

public class Main {
    int checker(int num) {
        int result;
//        int numDigits = (int) Math.log10(num);
//        boolean[] numCheck = new boolean[num];
        int[] numCheck = new int[num];
        for (int i = 1; i < num+1; i++) {
            int numDigits = (int) Math.log10(i);
            if (numDigits > 1) {
                String cur_num_str = Integer.toString(i);
                int[] nums = Arrays.stream(cur_num_str.split("")).mapToInt(Integer::parseInt).toArray();
                int diff = nums[0] - nums[1];
                for (int j = 1; j < numDigits; j++) {
                    int diff_temp = nums[j] - nums[j+1];
                    if (diff == diff_temp) {
//                        numCheck[i] = true;
                        numCheck[i-1] = 1;
                    } else {
//                        numCheck[i] = false;
                        numCheck[i-1] = 0;
                    }
                }
            } else {
//                numCheck[i] = true;
                numCheck[i-1] = 1;
            }
        }
        result = Arrays.stream(numCheck).sum();

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test_num = sc.nextInt();
//        int test_num = 1000;
        Main solution = new Main();
        int result = solution.checker(test_num);
        System.out.println(result);
    }
}
