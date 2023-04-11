import java.util.*;

class Solution {
    
    class Node {
        char idx;
        int sum;
        Map<Character, Node> childs;
        
        public Node(char idx) {
            this.idx = idx;
            this.sum = 0;
            this.childs = new HashMap<>();
        }
        
        boolean hasChild(char target) {
            return this.childs.containsKey(target);
        }
        
        void addChild(Node child) {
            this.childs.put(child.idx, child);
        }
        
        Node getChild(char target) {
            return childs.get(target);
        }
        
    }
    
    public int solution(String[] words) {
        Node root = new Node('0');
        for (String word : words) {
            Node current = root;
            for (char charI : word.toCharArray()) {
                if (!current.hasChild(charI)) {
                    Node child = new Node(charI);
                    current.addChild(child);
                }
                current = current.getChild(charI);
                current.sum++;
            }
        }
        int answer = 0;
        for (String word : words) {
            Node current = root;
            for (char charI : word.toCharArray()) {
                current = current.getChild(charI);
                answer++;
                if (current.sum == 1) break;
            }
        }
        
        
        return answer;
    }
}