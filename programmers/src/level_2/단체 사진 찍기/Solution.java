class Solution {
    String[] name = {"A", "C", "F", "J", "M", "N", "R", "T"};
    String[] constraints;
    int result;

    public boolean check(String dataIn) {
        for (String constraint : constraints) {
            String[] temp = constraint.split("");
            String firStr = temp[0];
            String secStr = temp[2];

            String comp = temp[3];
            int compNum = Integer.parseInt(temp[4]);

            if (dataIn.contains(firStr) && dataIn.contains(secStr)) {
                int diff = Math.abs(dataIn.indexOf(firStr) - dataIn.indexOf(secStr)) - 1;
                if (comp.equals(">")) {
                    if (diff <= compNum) return false;
                } else if (comp.equals("<")) {
                    if (diff >= compNum) return false;
                } else {
                    if (diff != compNum) return false;
                }
            }
        }
        return true;
    }

    public void dfs(String dataIn, boolean[] visited, int depth) {
        if (depth == 8) {
            if (check(dataIn)) {
                result++;
            }
            return;
        }
        String curStr;
        for (int i = 0; i < 8; i++) {
            if (visited[i]) continue;
            curStr = name[i];
            visited[i] = true;
            if (check(dataIn + curStr)) {
                dfs(dataIn + curStr, visited, depth+1);
            }
            visited[i] = false;
        }
    }

    public int solution(int n, String[] data) {
        constraints = data;
        result = 0;
        boolean[] visited = new boolean[8];
        dfs("", visited, 0);

        return result;
    }
}