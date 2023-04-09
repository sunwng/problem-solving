import java.util.*;

class Solution {
    
    String removeBracket(String target) {
        return target.substring(2, target.length() - 2);
    }
    
    public int[] solution(String s) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(
            (int[] o1, int[] o2) -> o1.length - o2.length
        );
        
        
        String total = removeBracket(s);
        String[] subsetsStr = total.split("\\},\\{");
        
        for (String subsetStr : subsetsStr) {
            String[] numsStr = subsetStr.split(",");
            int[] nums = new int[numsStr.length];
            for (int i = 0; i < numsStr.length; i++) {
                nums[i] = Integer.parseInt(numsStr[i]);
            }
            queue.add(nums);
        }
        
        List<Integer> answer = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] nums = queue.poll();
            for (int num : nums) {
                if (answer.contains(num)) continue;
                answer.add(num);
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}