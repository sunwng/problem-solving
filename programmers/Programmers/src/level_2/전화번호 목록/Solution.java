import java.util.*;

class Node {
    public HashMap<String, Node> childNode = new HashMap<>();
    public String nodeName;
    public boolean endCheck = false;

    Node(String strIn) {
        nodeName = strIn;
    }
    Node() {
        nodeName = null;
    }

    public void add(String strIn) {
        childNode.put(strIn, new Node(strIn));
    }

    public boolean search(String strIn) {
        return childNode.keySet().contains(strIn);
    }
    public Node getChild(String strIn) {return childNode.get(strIn);}

}

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, (s1, s2) -> s1.length() - s2.length());
        Node startNode = new Node();
        boolean answer = true;
        for (String phoneI : phone_book) {
            Node curNode = startNode;
            for (String strI : phoneI.split("")) {
                if (curNode.endCheck) {
                    answer = false;
                    return answer;
                }

                if (!curNode.search(strI)) {
                    curNode.add(strI);
                }
                curNode = curNode.getChild(strI);
            }
            curNode.endCheck = true;
        }

        return answer;
    }
}