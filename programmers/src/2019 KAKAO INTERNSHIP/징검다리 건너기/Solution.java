import java.util.*;

class Solution {
    int N;
    int[] tree;
    int[] arr;
    
    int init(int start, int end, int node) {
        if (start == end) {
            tree[node] = arr[start];
            return tree[node];
        }
        int mid = (start + end) / 2;
        
        tree[node] = Math.max(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
        return tree[node];
    }
    
    int getMax(int start, int end, int node, int left, int right) {
        if (end < left || start > right) return 0;
        if (start >= left && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return Math.max(getMax(start, mid, node * 2, left, right), getMax(mid + 1, end, node * 2 + 1, left, right));
    }
    
    
    public int solution(int[] stones, int k) {
        N = stones.length;
        arr = stones;
        tree = new int[N * 4];
        
        init(0, N - 1, 1);
        
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= N - k; i++) {
            answer = Math.min(answer, getMax(0, N - 1, 1, i, i + k - 1));
        }
        
        return answer;
    }
}