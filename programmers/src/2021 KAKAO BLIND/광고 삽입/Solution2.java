import java.util.*;

class Solution2 {
    
    String intToTime(int timeInt) {
        StringJoiner sj = new StringJoiner(":");
        
        int hour = timeInt / 3600;
        sj.add(String.format("%02d", hour));
        
        int left = timeInt % 3600;
        int minute = left / 60;
        sj.add(String.format("%02d", minute));
        sj.add(String.format("%02d", left % 60));
        
        return sj.toString();
    }
    
    int timeToInt(String timeStr) {
        int timeInt = 0;
        String[] timeStrArr = timeStr.split(":");
        timeInt += Integer.parseInt(timeStrArr[0]) * 3600;
        timeInt += Integer.parseInt(timeStrArr[1]) * 60;
        timeInt += Integer.parseInt(timeStrArr[2]);
        return timeInt;
    }
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = timeToInt(play_time);
        int advTime = timeToInt(adv_time);
        
        int[] count = new int[playTime + 1];
        
        for (String log : logs) {
            String[] splitted = log.split("-");
            int start = timeToInt(splitted[0]);
            int end = timeToInt(splitted[1]);
            count[start]++;
            count[end]--;
        }
        
        for (int i = 1; i <= playTime; i++) {
            count[i] += count[i - 1];
        }
        
        int answer = 0;
        long max = 0;
        for (int i = 0; i < advTime; i++) max += count[i];
        long temp = max;
        
        for (int i = 1; i <= playTime - advTime; i++) {
            temp = temp - count[i - 1] + count[i + advTime - 1];
            if (temp > max) {
                max = temp;
                answer = i;
            }
        }
        
        
        return intToTime(answer);
    }
}