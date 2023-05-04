package prob_649;

import java.util.*;

class Solution {

    int N;
    boolean[] rights;

    Optional<String> check(String senate) {
        int R = 0;
        int D = 0;
        for (int i = 0; i < N; i++) {
            if (!rights[i]) continue;
            if (senate.charAt(i) == 'R') R++;
            else D++;
            if (R != 0 && D != 0) return Optional.empty();
        }
        if (R == 0) return Optional.of("Dire");
        else return Optional.of("Radiant");
    }

    void banNearest(String senate, int idx) {
        char party = senate.charAt(idx);

        for (int i = 1; i < N; i++) {
            int newIdx = (i + idx) % N;
            if (senate.charAt(newIdx) == party) continue;
            if (!rights[newIdx]) continue;
            rights[newIdx] = false;
            return;
        }
    }

    public String predictPartyVictory(String senate) {
        N = senate.length();
        rights = new boolean[N];
        Arrays.fill(rights, true);

        while (true) {
            Optional<String> result = check(senate);
            if (result.isPresent()) return result.get();

            for (int i = 0; i < N; i++) {
                if (!rights[i]) continue;
                banNearest(senate, i);
            }
        }
    }
}
