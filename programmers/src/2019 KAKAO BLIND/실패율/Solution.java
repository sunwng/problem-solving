import java.util.*;

class Solution {
    
    class Stage {
        int num;
        double rate;
        
        public Stage(int num, double rate) {
            this.num = num;
            this.rate = rate;
        }
    }
    
    public int[] solution(int N, int[] stages) {
        int[][] count = new int[N + 2][3]; // 0 넘버, 1 클리어, 2 시도
        for (int i = 1; i <= N; i++) {
            count[i][0] = i;
        }
        for (int stage : stages) {
            for (int i = 1; i < stage; i++) {
                count[i][1]++;
                count[i][2]++;
            }
            count[stage][2]++;
        }
        double[] rate = new double[N + 1];
        List<Stage> results = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            double temp = 0.0;
            if (count[i][2] != 0) temp = 1 - (double) count[i][1] / count[i][2];
            results.add(new Stage(i, temp));
        }
        Collections.sort(results, (Stage o1, Stage o2) -> {
            if (o1.rate == o2.rate) return o1.num - o2.num;
            else return Double.compare(o2.rate, o1.rate);
        });
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = results.get(i).num;
        }
        
        return answer;
    }
}