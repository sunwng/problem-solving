import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String[] strNums = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strNums[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(strNums, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });
        if (strNums[0].equals("0")) {
            return "0";
        } else {
            return String.join("", strNums);
        }
    }
}