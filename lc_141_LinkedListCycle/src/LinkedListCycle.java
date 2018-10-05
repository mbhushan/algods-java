/**
 141. Linked List Cycle
 https://leetcode.com/problems/linked-list-cycle/description/
 Given a linked list, determine if it has a cycle in it.

 Follow up:
 Can you solve it without using extra space?

 */
public class LinkedListCycle {


    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == null) {
                return false;
            }

            if (fast.equals(slow)) {
                return true;
            }

        }

        return false;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
