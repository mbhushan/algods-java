/**

 143. Reorder List
 https://leetcode.com/problems/reorder-list/description/

 Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 You may not modify the values in the list's nodes, only nodes itself may be changed.

 Example 1:
 Given 1->2->3->4, reorder it to 1->4->2->3.

 Example 2:
 Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

 ===============
 INPUT / OUTPUT
 ===============

 */

public class ReorderList {

    private ListNode head;

    public static void main(String[] args) {
        ReorderList rl = new ReorderList();

        rl.buildList();

        rl.reverseList();

    }

    public void reorderList(ListNode head) {

    }

    public void reverseList() {

        System.out.println("original List: ");
        printList(this.head);

        this.head = reverseList(this.head);
        //this.head = reverseListRec(this.head);

        System.out.println("reversed list: ");
        printList(this.head);

        int i = 1;
        ListNode node = this.head;
        while (i-- > 0) {
            node = node.next;
        }

        System.out.println("List1: ");
        printList(node);

        node.next = reverseList(node.next);

        System.out.println("List: ");
        printList(this.head);

    }

    public void printList(ListNode node) {

        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }

        System.out.println("null");
    }

    public ListNode reverseList(ListNode node) {

        if (node == null || node.next == null) {
            return node;
        }

        ListNode curr = node.next;
        ListNode prev = node;
        prev.next = null;

        while (curr != null) {

            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;

        }

        return prev;
    }

    public ListNode reverseListRec(ListNode node) {

        if (node == null || node.next == null) {
            return node;
        }

        ListNode curr = node.next;
        ListNode prev = node;
        prev.next = null;

        ListNode rest = reverseListRec(curr);
        curr.next = prev;

        return rest;

    }

    public void buildList() {

        this.head = new ListNode(1);
        this.head.next = new ListNode(2);
        this.head.next.next = new ListNode(3);
        this.head.next.next.next = new ListNode(4);
        this.head.next.next.next.next = new ListNode(5);
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
