import java.util.*;

class Solution {
    boolean[][] map;
    List<int[]> persons;
    int[] answer;
    int N;
    
    int getDistance(int[] first, int[] second) {
        return Math.abs(first[0] - second[0]) + Math.abs(first[1] - second[1]);
    }
    
    int check() {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                int[] first = persons.get(i);
                int[] second = persons.get(j);
                if (getDistance(first, second) > 2) continue;
                if (first[0] == second[0]) {
                    if (!map[first[0]][(first[1] + second[1]) / 2]) return 0;
                } else if (first[1] == second[1]) {
                    if (!map[(first[0] + second[0]) / 2][first[1]]) return 0;
                } else {
                    if (!map[first[0]][second[1]]) return 0;
                    if (!map[second[0]][first[1]]) return 0;
                }
            }
        }
        return 1;
    }
    
    
    public int[] solution(String[][] places) {
        N = places.length;
        answer = new int[N];
        persons = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            map = new boolean[5][5];
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (places[i][r].charAt(c) == 'X') {
                        map[r][c] = true;
                    } else if (places[i][r].charAt(c) == 'P') {
                        persons.add(new int[] {r, c});
                    }
                }
            }
            answer[i] = check();
            persons.clear();
        }
        
        return answer;
    }
}