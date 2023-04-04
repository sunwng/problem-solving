import java.util.*;

class Solution {
    
    Map<Character, Integer> result;
    
    char getBigger(String target) {
        char first = target.charAt(0);
        char second = target.charAt(1);
        return result.get(first) >= result.get(second) ? first : second;
    }
    
    public String solution(String[] survey, int[] choices) {
        result = new HashMap<>();
        result.put('R', 0);
        result.put('T', 0);
        result.put('C', 0);
        result.put('F', 0);
        result.put('J', 0);
        result.put('M', 0);
        result.put('A', 0);
        result.put('N', 0);
        
        for (int i = 0; i < survey.length; i++) {
            if (choices[i] > 4) {
                result.replace(survey[i].charAt(1), result.get(survey[i].charAt(1)) + Math.abs(choices[i] - 4));
            } else if (choices[i] < 4) {
                result.replace(survey[i].charAt(0), result.get(survey[i].charAt(0)) + Math.abs(choices[i] - 4));
            }
        }
        
        String answer = "";
        answer += getBigger("RT");
        answer += getBigger("CF");
        answer += getBigger("JM");
        answer += getBigger("AN");
        
        
        return answer;
    }
}