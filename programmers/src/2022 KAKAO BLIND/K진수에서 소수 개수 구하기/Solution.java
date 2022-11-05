import java.util.*;
class Solution {
    public PriorityQueue<Long> primeSet;

    public boolean checkPrime(Long numIn) {
        if (primeSet.contains(numIn)) {
            return true;
        } else {
            if ((numIn != 2) && (numIn % 2 == 0)) return false;
            
            for (int i= 2; i <= Math.sqrt((double) numIn); i++) {
                if (numIn % i == 0) return false;
            }
            primeSet.add(numIn);
            return true;
        }
    }

    public int solution(int n, int k) {
        int answer = 0;
        primeSet = new PriorityQueue<>();

        String changed = Integer.toString(n, k);
        String[] splitted = changed.split("0");
        Arrays.sort(splitted);
        for (String strI : splitted) {
            if (strI.equals("") || strI.equals("1")) continue;
            if (checkPrime(Long.parseLong(strI))) {
                answer++;
            }
        }

        return answer;
    }
}