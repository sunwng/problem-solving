import java.util.*;
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);
        for (int i = 0; i < places.length; i++) {
            ArrayList<int[]> repoP = new ArrayList<>();
            boolean[][] map = new boolean[5][5];
            for (int j = 0; j < 5; j++) {
                String[] temp = places[i][j].split("");
                for (int k = 0; k < 5; k++) {
                    if (temp[k].equals("P")) {
                        repoP.add(new int[]{j, k});
                    } else if (temp[k].equals("X")) {
                        map[j][k] = true;
                    }
                }
            }
            for (int j = 0; j < repoP.size(); j++) {
                int[] firP = repoP.get(j);
                for (int k = j + 1; k < repoP.size(); k++) {
                    int[] secP = repoP.get(k);
                    int dis = Math.abs(firP[0] - secP[0]) + Math.abs(firP[1] - secP[1]);
                    if (dis == 1) {
                        answer[i] = 0;
                        break;
                    } else if (dis == 2) {
                        int firAbg = (firP[0] + secP[0]) / 2;
                        int secAbg = (firP[1] + secP[1]) / 2;
                        if (firP[0] == secP[0]) {
                            if (!map[firAbg][secAbg]) {
                                answer[i] = 0;
                                break;
                            }
                        } else if (firP[1] == secP[1]) {
                            if (!map[firAbg][secAbg]) {
                                answer[i] = 0;
                                break;
                            }
                        } else {
                            if (!map[firP[0]][secP[1]] || !map[secP[0]][firP[1]]) {
                                answer[i] = 0;
                                break;
                            }
                        }
                    }
                }
                if (answer[i] == 0) break;
            }

        }
        return answer;
    }
}