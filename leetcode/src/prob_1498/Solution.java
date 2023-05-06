package prob_1498;

import java.util.*;

class Solution {

    int MOD = 1_000_000_007;

    int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) left = mid + 1;
            else right = mid - 1;
        }
        return right;
    }

    public int numSubseq(int[] nums, int target) {
        int N = nums.length;
        Arrays.sort(nums);

        int[] power = new int[N + 1];
        power[0] = 1;
        for (int i = 1; i <= N; i++) {
            power[i] = (power[i - 1] * 2) % MOD;
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] > target) break;
            int idx = binarySearch(nums, target - nums[i], i, N - 1);
            if (idx < i) continue;
            answer = (answer + power[idx - i]) % MOD;
        }
        return answer;
    }
}
