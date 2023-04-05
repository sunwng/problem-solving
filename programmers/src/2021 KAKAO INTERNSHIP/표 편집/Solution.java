import java.util.*;

class Solution {
    
    class Node {
        Node prev;
        Node next;
        boolean alive;
        public Node() {
            alive = true;
        }
    }
    Stack<Node> stack;
    
    public String solution(int n, int k, String[] cmd) {
        stack = new Stack<>();
        
        Node[] table = new Node[n];
        table[0] = new Node();
        for (int i = 1; i < n; i++) {
            table[i] = new Node();
            table[i - 1].next = table[i];
            table[i].prev = table[i - 1];
        }
        
        Node current = table[k];
        
        for (String command : cmd) {
            if (command.startsWith("D")) {
                int step = Integer.parseInt(command.split(" ")[1]);
                for (int i = 0; i < step; i++) {
                    current = current.next;
                }
            } else if (command.startsWith("U")) {
                int step = Integer.parseInt(command.split(" ")[1]);
                for (int i = 0; i < step; i++) {
                    current = current.prev;
                }
            } else if (command.startsWith("C")) {
                current.alive = false;
                stack.add(current);
                if (current.next == null ) {
                    current.prev.next = null;
                    current = current.prev;
                } else if (current.prev == null) {
                    current.next.prev = null;
                    current = current.next;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    current = current.next;
                }
            } else {
                Node target = stack.pop();
                target.alive = true;
                if (target.prev != null) {
                    target.prev.next = target;
                }
                if (target.next != null) {
                    target.next.prev = target;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (Node node : table) {
            if (node.alive) sb.append("O");
            else sb.append("X");
        }
        
        return sb.toString();
    }
}