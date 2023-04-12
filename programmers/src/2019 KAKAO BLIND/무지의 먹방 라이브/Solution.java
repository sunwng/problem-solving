import java.util.*;

class Solution {
    int N;
    
    public int solution(int[] food_times, long k) {
        List<int[]> foods = new LinkedList<>();
        N = food_times.length;
        for (int i = 0; i < N; i++) {
            foods.add(new int[] {i + 1, food_times[i]});
        }
        Collections.sort(foods, (int[] o1, int[] o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            else return o1[1] - o2[1];
        });
        
        int prev = 0;
        while (true) {
            if (foods.isEmpty()) return -1;
            long temp = (long) (foods.get(0)[1] - prev) * foods.size();
            if (temp > k) break;
            k -= temp;
            prev = foods.get(0)[1];
            foods.remove(0);
        }
        
        if (foods.size() == 1) return foods.get(0)[0];
        
        Collections.sort(foods, (int[] o1, int[] o2) -> o1[0] - o2[0]);
        int answer = (int) (k % foods.size());
        
        return foods.get(answer)[0];
    }
}