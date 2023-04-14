import java.util.*;

class Solution {
    
    int N = 50;
    String[][] table;
    int[][] merge;
    
    public String[] solution(String[] commands) {
        table = new String[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                table[i][j] = "EMPTY";
            }
        }
        merge = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                merge[i][j] = i * 50 + j;
            }
        }
        List<String> answer = new ArrayList<>();
        for (String cmd : commands) {
            String[] split = cmd.split(" ");
            String action = split[0];
            if ("UPDATE".equals(action) && (split.length == 4)) { // 단일 변경
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);
                String value = split[3];
                updateAll(merge[r][c], value);
            } else if ("UPDATE".equals(action) && (split.length == 3)) { // 전체 변경
                String target = split[1];
                String value = split[2];
                for (int i = 1; i <= 50; i++) {
                    for (int j = 1; j <= 50; j++) {
                        if (table[i][j].equals(target)) table[i][j] = value;
                    }
                }
            } else if ("MERGE".equals(action)) { // 병합
                int r1 = Integer.parseInt(split[1]);
                int c1 = Integer.parseInt(split[2]);
                int r2 = Integer.parseInt(split[3]);
                int c2 = Integer.parseInt(split[4]);
                
                String value = table[r1][c1];
                if ("EMPTY".equals(value) && !"EMPTY".equals(table[r2][c2])) value = table[r2][c2];
                if (merge[r1][c1] < merge[r2][c2]) {
                    mergeAll(merge[r2][c2], merge[r1][c1]);
                } else {
                    mergeAll(merge[r1][c1], merge[r2][c2]);
                }
                updateAll(merge[r1][c1], value);
            } else if ("UNMERGE".equals(action)) { // 해제
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);
                String backup = table[r][c];
                updateAll(merge[r][c], "EMPTY");
                unmerge(merge[r][c]);
                table[r][c] = backup;
            } else { // 출력
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);
                answer.add(table[r][c]);
            }
        }
        return answer.toArray(String[]::new);
    }
    
    void unmerge(int target) {
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                if (merge[i][j] == target) merge[i][j] = i * 50 + j;
            }
        }
    }
    
    void updateAll(int target, String value) {
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                if (merge[i][j] == target) table[i][j] = value;
            }
        }
    }
    
    void mergeAll(int prev, int target) {
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                if (merge[i][j] == prev) merge[i][j] = target;
            }
        }
    }
    
}