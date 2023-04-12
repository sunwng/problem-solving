import java.util.*;

class Solution {
    
    int N;
    int preIdx = 0;
    int postIdx = 0;
    int[][] answer;
    int[] left;
    int[] right;
    int[][] pos;
    Queue<int[]> wait;
    
    int getLeaf(int node, int[] target) {
        if (pos[node - 1][0] > target[0]) {
            if (left[node] == -1) return node;
            else return getLeaf(left[node], target);
        } else {
            if (right[node] == -1) return node;
            else return getLeaf(right[node], target);
        }
    }
    
    void makeTree(int root) {
        wait.poll();
        while (!wait.isEmpty()) {
            int[] now = wait.poll();
            int parent = getLeaf(root, now);
            if (now[0] > pos[parent - 1][0]) {
                right[parent] = now[2];
            } else {
                left[parent] = now[2];
            }
        }
    }
    
    void postorder(int node) {
        if (left[node] != -1) postorder(left[node]);
        if (right[node] != -1) postorder(right[node]);
        answer[1][postIdx++] = node;
    }
    
    void preorder(int node) {
        answer[0][preIdx++] = node;
        if (left[node] != -1) preorder(left[node]);
        if (right[node] != -1) preorder(right[node]);
    }
    
    public int[][] solution(int[][] nodeinfo) {
        pos = nodeinfo;
        N = nodeinfo.length;
        if (N == 1) {
            answer = new int[2][1];
            answer[0][0] = 1;
            answer[1][0] = 1;
            return answer;
        }
        left = new int[N + 1];
        right = new int[N + 1];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        wait = new PriorityQueue<>((int[] o1, int[] o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            else return o2[1] - o1[1];
        });
        int root = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            wait.add(new int[] {nodeinfo[i][0], nodeinfo[i][1], i + 1});
            if (nodeinfo[i][1] > max) {
                max = nodeinfo[i][1];
                root = i + 1;
            }
        }
        makeTree(root);
        answer = new int[2][N];
        preorder(root);
        postorder(root);
        
        return answer;
    }
}