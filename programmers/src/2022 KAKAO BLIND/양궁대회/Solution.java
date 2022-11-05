import java.util.*;
class Solution {
    public int N;
    public int[] arrA;
    public int[] answer;
    public int max;

    public void dfs(int[] arrL, int depth, int[] info) {
        if (depth == N) {
            int A = 0;
            int L = 0;
            for (int i = 0; i < 11; i++) {
                if (arrL[i] == 0 && info[i] == 0) {
                    continue;
                } else if (arrL[i] > info[i]) {
                    L += (10 - i);
                } else {
                    A += (10 - i);
                }
            }
            if ((L - A) >= max) {
                max = L - A;
                answer = arrL.clone();
            }
            return;
        }
        for (int i = 0; i < 11 && arrL[i] <= info[i]; i++) {
            arrL[i]++;
            dfs(arrL, depth+1, info);
            arrL[i]--;
        }
    }
    public int[] solution(int n, int[] info) {
        max = 0;
        N = n;
        arrA = info;
        answer = new int[]{-1};
        dfs(new int[11], 0, info);
        return answer;
    }
}