package prob_133;
import java.util.*;

class Solution {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    Map<Integer, Node> nodes;
    Set<Integer> visit;

    void addNode(Node node) {
        Node curNode = nodes.get(node.val);
        visit.add(node.val);
        for (Node neighbor : node.neighbors) {
            int nodeNum = neighbor.val;
            if (!nodes.containsKey(nodeNum)) {
                nodes.put(nodeNum, new Node(nodeNum));
            }
            curNode.neighbors.add(nodes.get(nodeNum));
        }

        for (Node neighbor : node.neighbors) {
            if (visit.contains(neighbor.val)) continue;
            addNode(neighbor);
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        nodes = new HashMap<>();
        visit = new HashSet<>();
        nodes.put(1, new Node(1));
        addNode(node);
        return nodes.get(1);
    }
}