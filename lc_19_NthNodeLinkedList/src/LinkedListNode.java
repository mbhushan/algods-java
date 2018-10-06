/**
 19. Remove Nth Node From End of List
 https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/

 Given a linked list, remove the n-th node from the end of list and return its head.

 Example:

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:

 Given n will always be valid.

 Follow up:

 Could you do this in one pass?

 */
public class LinkedListNode {


    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode front = dummy;
        ListNode back = dummy;


        while (n > 0) {
            front = front.next;
            --n;
        }

        while (front.next != null) {
            front = front.next;
            back = back.next;
        }


        back.next = back.next.next;


        return dummy.next;
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
