package prob_662;

import common.TreeNode;
import java.util.*;

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        int answer = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        root.val = 1;
        queue.add(root);

        while (!queue.isEmpty()) {
            int start = 0;
            int end = 0;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode now = queue.poll();
                if (i == 0) start = now.val;
                if (i == size - 1) end = now.val;

                if (now.left != null) {
                    now.left.val = now.val * 2;
                    queue.add(now.left);
                }
                if (now.right != null) {
                    now.right.val = now.val * 2 + 1;
                    queue.add(now.right);
                }
            }
            answer = Math.max(answer, end - start + 1);
        }

        return answer;
    }
}
