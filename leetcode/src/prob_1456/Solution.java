package prob_1456;

class Solution {

    int N;
    int[] tree;
    int[] arr;

    int get(int start, int end, int node, int left, int right) {
        if (left > end || right < start ) return 0;
        else if (start >= left && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return get(start, mid, node * 2, left, right) + get(mid + 1, end, node * 2 + 1, left, right);
    }

    int init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    public int maxVowels(String s, int k) {
        N = s.length();
        arr = new int[N];

        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        for (int i = 0; i < N; i++) {
            if (vowels.contains(s.charAt(i))) arr[i] = 1;
        }
        tree = new int[N * 4];
        init(0, N - 1, 1);
        int answer = 0;
        for (int i = 0; i <= N - k; i++) {
            answer = Math.max(answer, get(0, N - 1, 1, i, i + k - 1));
        }

        return answer;
    }
}
