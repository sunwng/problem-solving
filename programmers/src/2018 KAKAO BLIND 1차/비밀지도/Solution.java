class Solution {
    
    String fillZero(String target, int n) {
        while(target.length() != n) {
            target = "0" + target;
        }
        return target;
    }
    
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        int[][] map1 = new int[n][n];
        int[][] map2 = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            String binary = fillZero(Integer.toString(arr1[i], 2), n);
            for (int j = 0; j < n; j++) {
                if (binary.charAt(j) == '1') map1[i][j] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            String binary = fillZero(Integer.toString(arr2[i], 2), n);
            for (int j = 0; j < n; j++) {
                if (binary.charAt(j) == '1') map2[i][j] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            String temp = "";
            for (int j = 0; j < n; j++) {
                if (map1[i][j] + map2[i][j] == 0) temp += " ";
                else temp += "#";
            }
            answer[i] = temp;
        }
        
        return answer;
    }
}