import java.util.*;

class Solution {
    
    int parse(String time) {
        int timeInt = 0;
        timeInt += Integer.parseInt(time.substring(0, 2)) * 3600 * 1000;
        timeInt += Integer.parseInt(time.substring(3, 5)) * 60 * 1000;
        timeInt += (int) (Double.parseDouble(time.substring(6)) * 1000);
        return timeInt;
    }
    
    int getMS(String ms) {
        return (int) (Double.parseDouble(ms.substring(0, ms.length() - 1)) * 1000);
    }
    
    public int solution(String[] lines) {
        int answer = 0;
        for (int i = 0; i < lines.length; i++) {
            int count = 1;
            String[] splitted = lines[i].split(" ");
            int baseStart = parse(splitted[1]);
            int baseEnd = baseStart + 999;
            
            for (int j = i + 1; j < lines.length; j++) {
                String[] splitted2 = lines[j].split(" ");
                int end = parse(splitted2[1]);
                int start = end - getMS(splitted2[2]) + 1;
                if (baseEnd >= start && baseEnd <= end) count++;
                else if (end >= baseStart && end <= baseEnd) count++;
            }
            answer = Math.max(answer, count);
        }
        
        
        return answer;
    }
}