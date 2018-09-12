/**
 23. Merge k Sorted Lists
 https://leetcode.com/problems/merge-k-sorted-lists/description/

 Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

 Example:

 Input:
 [
 1->4->5,
 1->3->4,
 2->6
 ]
 Output: 1->1->2->3->4->4->5->6

 */

public class MergeSortedLists {

    private ListNode n1;
    private ListNode n2;
    private ListNode n3;

    ListNode [] lists;

    MergeSortedLists () {
        lists = new ListNode[3];
    }


    public static void main(String[] args) {
        MergeSortedLists ms = new MergeSortedLists();

        //ListNode [] lists = new ListNode[3];

        ms.init();
        ms.printLists();

    }

    public void init() {
        n1 = new ListNode(1);
        n1.next = new ListNode((4));
        n1.next.next = new ListNode(5);
        lists[0] = n1;

        n2 = new ListNode(1);
        n2.next = new ListNode(3);
        n2.next.next = new ListNode(4);
        lists[1] = n2;

        n3 = new ListNode(2);
        n3.next = new ListNode(6);
        lists[2] = n3;
    }

    public void printLists() {
        for (int i=0; i<lists.length; i++) {

            ListNode node = lists[i];

            while (node != null) {
                System.out.print(node.val + " ");
                node = node.next;
            }
            System.out.println();
        }
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
