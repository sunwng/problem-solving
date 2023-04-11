import java.util.*;

class Solution {
    public int solution(String dartResult) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < dartResult.length(); i++) {
            char cur = dartResult.charAt(i);
            if (cur == 'S') {
                continue;
            } else if (cur == 'D') {
                list.set(list.size() - 1, (int) Math.pow(list.get(list.size() - 1), 2));
            } else if (cur == 'T') {
                list.set(list.size() - 1, (int) Math.pow(list.get(list.size() - 1), 3));
            } else if (cur == '*') {
                list.set(list.size() - 1, list.get(list.size() - 1) * 2);
                if (list.size() > 1) {
                    list.set(list.size() - 2, list.get(list.size() - 2) * 2);
                }
            } else if (cur == '#') {
                list.set(list.size() - 1, list.get(list.size() - 1) * -1);
            } else {
                if (i != dartResult.length() - 1 && cur == '1' && dartResult.charAt(i + 1) == '0') {
                    list.add(Integer.parseInt(dartResult.substring(i, i + 2)));
                    i++;
                } else {
                    list.add(cur - '0');
                }
            }
        }
        return list.stream().mapToInt(i -> i).sum();
    }
}