import java.util.*;
class Node {
    List<Integer> linkedNode;
    int num;
    Node(int nodeNum) {
        num = nodeNum;
        linkedNode = new ArrayList<>();
    }
    public void link(int nodeNum) {
        linkedNode.add(nodeNum);
    }
    public int[] getLinkedNode() {
        return linkedNode.stream().mapToInt(i -> i).toArray();
    }
    public int getLinkNum() {
        return linkedNode.size();
    }
}

class Solution {
    int count;

    public void find(int nodeC, Node[] nodeList, boolean[] visitCheck) {
        if (visitCheck[nodeC]) {return;}
        count++;
        visitCheck[nodeC] = true;
        for (int nodeN : nodeList[nodeC].getLinkedNode()) {
            find(nodeN, nodeList, visitCheck);
        }
    }

    public int solution(int n, int[][] wires) {
        int answer = 101;
        Node[] nodeList = new Node[n];
        for (int i = 0; i < n; i++) {
            nodeList[i] = new Node(i);
        }
        for (int[] wireI : wires) {
            nodeList[wireI[0] - 1].link(wireI[1] - 1);
            nodeList[wireI[1] - 1].link(wireI[0] - 1);
        }
        for (int i = 0; i < n; i++) {
            if (nodeList[i].getLinkNum() <= 1) {continue;}
            for (int nodeI : nodeList[i].getLinkedNode()) {
                count = 0;
                boolean[] visitCheck = new boolean[n];
                visitCheck[i] = true;
                find(nodeI, nodeList, visitCheck);
                answer = Math.min(answer, Math.abs((n-count) - count));
            }
        }
        return answer;
    }
}