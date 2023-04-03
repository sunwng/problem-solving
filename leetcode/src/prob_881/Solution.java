package prob_881;

import java.util.*;

class Solution {

    int N;

    int binarySearch(int[] people, int target, int start, boolean[] visit) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (people[mid] <= target) left = mid + 1;
            else right = mid - 1;
        }

        while (right > start) {
            if (!visit[right]) break;
            right--;
        }
        return right;
    }

    public int numRescueBoats(int[] people, int limit) {
        N = people.length;
        Arrays.sort(people);

        int answer = 0;

        boolean[] visit = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            answer++;
            if (people[i] > limit - people[i]) continue;
            int another = binarySearch(people, limit - people[i], i, visit);
            visit[another] = true;
        }

        return answer;
    }
}