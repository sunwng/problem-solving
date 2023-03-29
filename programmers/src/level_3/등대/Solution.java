import java.util.*;

class Solution {
    int N;
    List<List<Integer>> map;
    Set<Integer> lights;
    
    boolean isLeaf(int next, int current) {
        return (map.get(next).size() == 1) && (map.get(next).get(0) == current);
    }
    
    void search(int current, int before) {
        boolean flag = false;
        for (int next : map.get(current)) {
            if (next == before) continue;
            if (isLeaf(next, current)) {
                lights.add(current);
            } else {
                search(next, current);
            }
            if (!lights.contains(next)) flag = true;
        }
        if (flag) lights.add(current);
    }
    
    public int solution(int n, int[][] lighthouse) {
        N = n;
        lights = new HashSet<>();
        map = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] link : lighthouse) {
            map.get(link[0]).add(link[1]);
            map.get(link[1]).add(link[0]);
        }
        search(1, 0);
        return lights.size();
    }
}