import java.util.*;

class Solution {
    int N;
    
    List<String> parse(String target) {
        List<String> splitted = new ArrayList<>();
        for (char charI : target.toCharArray()) {
            if (charI == '#') {
                int lastIdx = splitted.size() - 1;
                splitted.set(lastIdx, splitted.get(lastIdx) + charI);
            } else splitted.add(Character.toString(charI));
        }
        return splitted;
    }
    
    int timeToInt(String time) {
        int timeInt = 0;
        timeInt += Integer.parseInt(time.substring(0, 2)) * 60;
        timeInt += Integer.parseInt(time.substring(3));
        return timeInt;
    }
    
    boolean check(String base, String target, int playTime) {
        String merged = "";
        List<String> splitted = parse(target);
        int length = splitted.size();
        int repeat = playTime / length;
        int left = playTime % length;
        
        for (int i = 0; i < repeat; i++) {
            merged += target;
        }
        for (int i = 0; i < left; i++) {
            merged += splitted.get(i);
        }
        
        int idx = merged.indexOf(base);
        while (idx != -1) {
            if (idx + base.length() < merged.length()) {
                if (merged.charAt(idx + base.length()) != '#') return true;
                else merged = merged.substring(idx + base.length());
            } else return true;
            idx = merged.indexOf(base);
        }
        return false;
    }
    
    
    public String solution(String m, String[] musicinfos) {
        N = parse(m).size();
        
        String answer = "(None)";
        int max = 0;
        
        for (String musicInfo : musicinfos) {
            String[] splitted = musicInfo.split(",");
            int playTime = timeToInt(splitted[1]) - timeToInt(splitted[0]);
            String title = splitted[2];
            if (check(m, splitted[3], playTime)) {
                if (playTime > max) {
                    max = playTime;
                    answer = title;
                }
            }
        }
        
        return answer;
    }
}