package prob_71;

import java.util.*;

class Solution {
    public String simplifyPath(String path) {
        StringJoiner sj = new StringJoiner("/");
        String[] splitted = path.substring(1).split("/");
        Deque<String> deque = new ArrayDeque<>();
        for (String str : splitted) {
            if (str.isEmpty()) continue;
            if (".".equals(str)) continue;
            if ("..".equals(str)) {
                if (deque.isEmpty()) continue;
                deque.removeLast();
            }
            else deque.addLast(str);
        }
        while (!deque.isEmpty()) {
            sj.add(deque.poll());
        }


        return "/" + sj.toString();
    }
}
