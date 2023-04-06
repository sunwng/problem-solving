import java.util.*;

class Solution {
    
    long answer = 0;
    List<Long> operands;
    List<Character> operators;
    
    void multiply(List<Long> copiedOperands, List<Character> copiedOperators) {
        while (true) {
            int operatorIdx = copiedOperators.indexOf('*');
            if (operatorIdx == -1) break;
            long target1 = copiedOperands.get(operatorIdx);
            long target2 = copiedOperands.get(operatorIdx + 1);
            long result = target1 * target2;
            copiedOperators.remove(operatorIdx);
            copiedOperands.remove(operatorIdx);
            copiedOperands.remove(operatorIdx);
            copiedOperands.add(operatorIdx, result);
        }
    }
    
    void plus(List<Long> copiedOperands, List<Character> copiedOperators) {
        while (true) {
            int operatorIdx = copiedOperators.indexOf('+');
            if (operatorIdx == -1) break;
            long target1 = copiedOperands.get(operatorIdx);
            long target2 = copiedOperands.get(operatorIdx + 1);
            long result = target1 + target2;
            copiedOperators.remove(operatorIdx);
            copiedOperands.remove(operatorIdx);
            copiedOperands.remove(operatorIdx);
            copiedOperands.add(operatorIdx, result);
        }
    }
    
    void minus(List<Long> copiedOperands, List<Character> copiedOperators) {
        while (true) {
            int operatorIdx = copiedOperators.indexOf('-');
            if (operatorIdx == -1) break;
            long target1 = copiedOperands.get(operatorIdx);
            long target2 = copiedOperands.get(operatorIdx + 1);
            long result = target1 - target2;
            copiedOperators.remove(operatorIdx);
            copiedOperands.remove(operatorIdx);
            copiedOperands.remove(operatorIdx);
            copiedOperands.add(operatorIdx, result);
        }
    }
    
    long calculate(String priority) {
        List<Long> copiedOperands = new LinkedList<>(operands);
        List<Character> copiedOperators = new LinkedList<>(operators);
        
        for (int i = 0; i < 3; i++) {
            char charI = priority.charAt(i);
            if (charI == '*') multiply(copiedOperands, copiedOperators);
            else if (charI == '+') plus(copiedOperands, copiedOperators);
            else minus(copiedOperands, copiedOperators);
        }
        
        return Math.abs(copiedOperands.get(0));
    }
    
    void search(String[] pool, String priority, boolean[] visit) {
        if (priority.length() == 3) {
            answer = Math.max(answer, calculate(priority));
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            search(pool, priority + pool[i], visit);
            visit[i] = false;
        }
    }
    
    void splitExpression(String expression) {
        String num = "";
        for (int i = 0; i < expression.length(); i++) {
            char charI = expression.charAt(i);
            if (charI == '*' || charI == '+' || charI == '-') {
                operands.add(Long.parseLong(num));
                operators.add(charI);
                num = "";
            } else {
                num += charI;
            }
        }
        operands.add(Long.parseLong(num));
    }
    
    public long solution(String expression) {
        String[] pool = new String[] {"*", "+", "-"};
        operands = new LinkedList<>();
        operators = new LinkedList<>();
        splitExpression(expression);
        search(pool, "", new boolean[3]);
        return answer;
    }
}