package prob_1939;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int idx;
        int weight;
        Node (int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    static int N;
    static int M;
    static List<List<Node>> islands;

    public static boolean checkWeight(int start, int end, int weight) {
        boolean[] visit = new boolean[N];
        visit[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == end) return true;
            for (Node node : islands.get(current)) {
                if (visit[node.idx]) continue;
                if (node.weight < weight) continue;
                visit[node.idx] = true;
                queue.add(node.idx);
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        islands = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            islands.add(new ArrayList<>());
        }

        int left = 1;
        int right = 0;

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int first = Integer.parseInt(input[0]) - 1;
            int second = Integer.parseInt(input[1]) - 1;
            int weight = Integer.parseInt(input[2]);
            right = Math.max(right, weight);
            islands.get(first).add(new Node(second, weight));
            islands.get(second).add(new Node(first, weight));
        }

        input = br.readLine().split(" ");
        int start = Integer.parseInt(input[0]) - 1;
        int end = Integer.parseInt(input[1]) - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (checkWeight(start, end, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(right);
    }
}