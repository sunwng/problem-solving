import java.util.*;

class Solution {
    
    int timeToInt(String time) {
        int timeInt = 0;
        timeInt += Integer.parseInt(time.substring(0, 2)) * 60;
        timeInt += Integer.parseInt(time.substring(3));
        return timeInt;
    }
    
    String intToTime(int timeInt) {
        String time = "";
        time += String.format("%02d", timeInt / 60);
        time += ":";
        time += String.format("%02d", timeInt % 60);
        return time;
    }
    
    public String solution(int n, int t, int m, String[] timetable) {
        int bus = 9 * 60;
        PriorityQueue<Integer> timeQueue = new PriorityQueue<>();
        for (String timeI : timetable) {
            timeQueue.add(timeToInt(timeI));
        }
        
        while (n > 1) {
            int count = 0;
            while (timeQueue.peek() <= bus && count < m) {
                timeQueue.poll();
                count++;
            }
            
            n--;
            bus += t;
        }
        
        if (timeQueue.isEmpty()) return intToTime(bus);
        
        List<Integer> lastBus = new ArrayList<>();
        
        while (!timeQueue.isEmpty() && timeQueue.peek() <= bus) {
            lastBus.add(timeQueue.poll());
        }
        int answer = 0;
        if (lastBus.size() >= m) {
            answer = lastBus.get(m - 1) - 1;
        } else {
            answer = bus;
        }
        
        
        return intToTime(answer);
    }
}