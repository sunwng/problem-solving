package prob_1774;

import javax.xml.stream.XMLOutputFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int x;
        int y;
        double dis;
        public Node(int x, int y, double dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }

    static int N, M;
    static int[] parent;

    static int find(int target) {
        if (parent[target] == target) return target;
        else return parent[target] = find(parent[target]);
    }

    static boolean union(int target1, int target2) {
        int parent1 = find(target1);
        int parent2 = find(target2);
        if (parent1 == parent2) return false;
        if (parent1 < parent2) parent[parent2] = parent1;
        else parent[parent1] = parent2;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] given = br.readLine().split(" ");
        N = Integer.parseInt(given[0]);
        M = Integer.parseInt(given[1]);
        int[][] gods = new int[N][2];
        for (int i = 0; i < N; i++) {
            given = br.readLine().split(" ");
            gods[i][0] = Integer.parseInt(given[0]);
            gods[i][1] = Integer.parseInt(given[1]);
        }

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }


        int count = 0;
        for (int i = 0; i < M; i++) {
            given = br.readLine().split(" ");
            if (union(Integer.parseInt(given[0]), Integer.parseInt(given[1]))) {
                count++;
            }

        }

        PriorityQueue<Node> pq = new PriorityQueue<>((Node o1, Node o2) -> Double.compare(o1.dis, o2.dis));

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double dist = Math.pow((long) (gods[i][0] - gods[j][0]), 2) + Math.pow((long) (gods[i][1] - gods[j][1]), 2);
                pq.add(new Node(i + 1, j + 1, Math.sqrt(dist)));
            }
        }
        double answer = 0;
        while (count < N - 1) {
            Node now = pq.poll();
            if (!union(now.x, now.y)) continue;
            answer += now.dis;
            count++;
        }
        System.out.printf("%.2f", answer);
    }
}
