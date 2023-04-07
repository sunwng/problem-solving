import java.util.*;

class Solution {
    
    class Path {
        int target;
        int cost;
        int when;
        
        public Path(int target, int cost, int when) {
            this.target = target;
            this.cost = cost;
            this.when = when;
        }
        
    }
    
    int N;
    int T;
    int answer = Integer.MAX_VALUE;
    Map<Integer, Integer> trapMap;
    List<List<Path>> path;
    
    int isTrapOn(int target, int state) {
        int targetBit = (1 << trapMap.get(target));
        if ((targetBit & state) == 0) return 0;
        else return 1;
    }
    
    boolean isTrap(int target) {
        return trapMap.containsKey(target);
    }
    
    int changeTrapState(int trap, int isTrapOn, int beforeState) {
        int trapBit = (1 << trapMap.get(trap));
        if (isTrapOn == 0) {
            return beforeState | trapBit;
        } else {
            return beforeState - trapBit;
        }
    }
    
    void search(int start, int end) {
        int[][] map = new int[N + 1][1 << T];
        for (int[] mapI : map) {
            Arrays.fill(mapI, Integer.MAX_VALUE);
        }
        map[start][0] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start, 0, 0});
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == end) {
                answer = Math.min(answer, now[1]);
                continue;
            }
            
            int isNowTrapOn = 0;
            boolean isNowTrap = isTrap(now[0]);
            if (isNowTrap) isNowTrapOn = isTrapOn(now[0], now[2]);
            
            for (Path next : path.get(now[0])) {
                int isNextTrapOn = 0;
                boolean isNextTrap = isTrap(next.target);
                if (isNextTrap) isNextTrapOn = isTrapOn(next.target, now[2]);
                
                int trapCheck = (isNowTrapOn + isNextTrapOn) % 2;
                if (next.when != trapCheck) continue;
                int nextCost = now[1] + next.cost;
                if (nextCost > map[next.target][now[2]]) continue;
                map[next.target][now[2]] = nextCost;
                int nextState = now[2];
                
                if (isNextTrap) {
                    nextState = changeTrapState(next.target, isNextTrapOn, nextState);
                }
                
                queue.add(new int[] {next.target, nextCost, nextState});
            }
        }
    }
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        N = n;
        T = traps.length;
        trapMap = new HashMap<>();
        for (int i = 0; i < T; i++) {
            trapMap.put(traps[i], i);
        }
        
        path = new ArrayList<>();
        
        for (int i = 0; i <= N; i++) {
            path.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            path.get(road[0]).add(new Path(road[1], road[2], 0));
            path.get(road[1]).add(new Path(road[0], road[2], 1));
        }
        
        search(start, end);
        
        return answer;
    }
}