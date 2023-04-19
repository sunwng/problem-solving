package prob_1372;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {

    int max = 0;

    void solve(TreeNode node, int depth, int prev) { // left -> 0, right -> 1
        if (node == null) {
            max = Math.max(max, depth - 1);
            return;
        }

        if (prev == 0) {
            solve(node.right, depth + 1, 1);
            solve(node.left, 1, 0);
        } else if (prev == 1){
            solve(node.left, depth + 1, 0);
            solve(node.right, 1, 1);
        }
    }

    public int longestZigZag(TreeNode root) {
        solve(root, 0, 0);
        solve(root, 0, 1);
        return max;
    }
}