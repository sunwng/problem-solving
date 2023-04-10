import java.util.*;

class Solution1 {
    
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
        
        int[] count = new int[playTime];
        
        for (String log : logs) {
            String[] splitted = log.split("-");
            int start = timeToInt(splitted[0]);
            int end = timeToInt(splitted[1]);
            for (int i = start; i < end; i++) {
                count[i]++;
            }
        }
        
        long[] sum = new long[playTime];
        sum[0] = count[0];
        for (int i = 1; i < playTime; i++) {
            sum[i] = sum[i - 1] + count[i];
        }
        
        int answer = 0;
        long max = sum[advTime - 1];
        long cur = max;
        
        for (int i = advTime; i < playTime; i++) {
            cur = sum[i] - sum[i - advTime];
            if (cur > max) {
                answer = i - advTime + 1;
                max = cur;
            }
        }
        
        
        return intToTime(answer);
    }
}