import java.util.*;
class Solution {
    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();

        int pointer = k;
        int size = n;
        Stack<Integer> stack = new Stack<>();
        for (String cmdI : cmd) {
            String[] cmdSpec = cmdI.split(" ");
            if (cmdSpec[0].equals("U")) {
                pointer -= Integer.parseInt(cmdSpec[1]);
            } else if (cmdSpec[0].equals("D")) {
                pointer += Integer.parseInt(cmdSpec[1]);
            } else if (cmdSpec[0].equals("C")) {
                stack.add(pointer);
                size--;
                if (pointer == size) pointer--;
            } else {
                if (stack.pop() <= pointer) pointer++;
                size++;
            }
        }
        for (int i = 0; i < size; i++) {
            answer.append("O");
        }
        while (!stack.isEmpty()) {
            answer.insert(stack.pop(), "X");
        }
        return answer.toString();
    }
}