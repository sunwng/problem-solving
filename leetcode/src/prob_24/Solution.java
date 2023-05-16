package prob_24;

class Solution {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode swapPairs(ListNode head) {
        int count = 1;
        ListNode cur = head;
        ListNode prev = cur;

        while (cur != null) {
            if (count % 2 == 0) {
                int temp = prev.val;
                prev.val = cur.val;
                cur.val = temp;
            }
            prev = cur;
            cur = cur.next;
            count++;
        }
        return head;
    }
}
