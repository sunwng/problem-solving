import java.util.*;

class Solution {
    
    boolean isAlphabet(char target) {
        return target >= 97 && target <= 122;
    }
    
    boolean isNumber(char target) {
        return target >= 48 && target <= 57;
    }
    
    boolean isAllowedCharacter(char target) {
        return target == 45 || target == 46 || target == 95;
    }
    
    String deleteCharacters(String target) {
        StringBuilder sb = new StringBuilder();
        for (char charI : target.toCharArray()) {
            if (isAlphabet(charI) || isNumber(charI) || isAllowedCharacter(charI)) {
                sb.append(charI);   
            }
        }
        return sb.toString();
    }
    
    String removeDuplicateComma(String target) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (char charI : target.toCharArray()) {
            if (charI == '.') {
                if (flag) continue;
                flag = true;
            } else {
                flag = false;
            }
            sb.append(charI);
        }
        return sb.toString();
    }
    
    public String solution(String new_id) {
        new_id = new_id.toLowerCase();
        new_id = deleteCharacters(new_id);
        new_id = removeDuplicateComma(new_id);
        
        if (new_id.length() > 0 && new_id.charAt(0) == '.') new_id = new_id.substring(1);
        if (new_id.length() > 0 && new_id.charAt(new_id.length() - 1) == '.') {
            new_id = new_id.substring(0, new_id.length() - 1);
        }
        if (new_id.isBlank()) new_id = "a";
        if (new_id.length() >= 16) {
            if (new_id.charAt(14) == '.') new_id = new_id.substring(0, 14);
            else new_id = new_id.substring(0, 15);
        } else if (new_id.length() <= 2) {
            while (new_id.length() < 3) {
                new_id += new_id.charAt(new_id.length() - 1);
            }
        }
        
        return new_id;
    }
}