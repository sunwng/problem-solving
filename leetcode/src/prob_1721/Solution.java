package prob_1721;

class Solution {

    public class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode swapNodes(ListNode head, int k) {
        int n = 0;
        ListNode[] nodes = new ListNode[100000];

        ListNode cur = head;
        while (true) {
            nodes[n++] = cur;
            if (cur.next == null) {
                break;
            }
            cur = cur.next;
        }
        int another = n - k;
        int temp = nodes[another].val;
        nodes[another].val = nodes[k - 1].val;
        nodes[k - 1].val = temp;
        return head;
    }
}
