class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        int[] student = new int[n+2];
        for (int idx : lost) {
            student[idx]--;
        }
        for (int idx : reserve) {
            student[idx]++;
        }

        for (int i = 1; i <= n; i++) {
            if (student[i] == 1) {
                if (student[i-1] == -1) {
                    student[i-1]++;
                    student[i]--;
                } else if (student[i+1] == -1) {
                    student[i+1]++;
                    student[i]--;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (student[i] >= 0) {
                answer++;
            }
        }


        return answer;
    }
}