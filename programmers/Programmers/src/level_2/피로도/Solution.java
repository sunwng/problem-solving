class Solution {
    int answer;
    public void dfs(int k, int[][] dungeons, boolean[] visited, int count) {
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && dungeons[i][0] <= k) {
                visited[i] = true;
                dfs(k - dungeons[i][1], dungeons, visited, count+1);
                visited[i] = false;
            }
        }
        answer = Math.max(answer, count);
    }
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        dfs(k, dungeons, new boolean[dungeons.length], 0);
        return answer;
    }
}