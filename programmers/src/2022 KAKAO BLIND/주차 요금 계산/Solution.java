import java.util.*;

class Solution {
    
    int timeToNumber(String time) {
        String[] splitted = time.split(":");
        return Integer.parseInt(splitted[0]) * 60 + Integer.parseInt(splitted[1]);
    }
    
    int getCost(int[] fees, int diff) {
        if (diff <= fees[0]) return fees[1];
        else {
            diff -= fees[0];
            int cost = fees[1];
            cost += (int) Math.ceil((double) diff / fees[2]) * fees[3];
            return cost;
        }
    }
    
    public int[] solution(int[] fees, String[] records) {
        int max = 23 * 60 + 59;
        
        Map<String, Integer> history = new HashMap<>();
        Map<String, Integer> times = new HashMap<>();
        for (String record : records) {
            String[] splitted = record.split(" ");
            int time = timeToNumber(splitted[0]);
            String car = splitted[1];
            if ("IN".equals(splitted[2])) {
                history.put(car, time);
            } else {
                times.putIfAbsent(car, 0);
                times.replace(car, times.get(car) + time - history.get(car));
                history.remove(car);
            }
        }
        
        for (String leftCar : history.keySet()) {
            times.putIfAbsent(leftCar, 0);
            times.replace(leftCar, times.get(leftCar) + max - (history.get(leftCar)));
        }
        
        int[] answer = new int[times.size()];
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (int[] o1, int[] o2) -> o1[0] - o2[0]
        );
        
        for (String car : times.keySet()) {
            pq.add(new int[] {Integer.parseInt(car), getCost(fees, times.get(car))});
        }
        
        int idx = 0;
        while (!pq.isEmpty()) {
            answer[idx++] = pq.poll()[1];
        }
        
        return answer;
    }
}