import java.util.Comparator;
import java.util.PriorityQueue;

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

 ================
 INPUT / OUTPUT
 ================
 1 4 5
 1 3 4
 2 6
 pq: [[ index:0; node: 1], [ index:1; node: 1], [ index:2; node: 2]]
 printing result:
 1->1->2->3->4->4->5->6->null

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

        ms.merge();

    }

    public void merge() {
        //mergeKLists(lists);
        mergeKListsGood(lists);
    }

    public ListNode mergeKListsGood(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;
        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.length ,(a,b) -> a.val-b.val);
        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;

        for (ListNode node:lists)
            if (node!=null)
                queue.add(node);

        System.out.println("queue: " + queue);
        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;

            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null) {
            return null;
        }

        if (lists.length < 1) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }

        ListNode head = null;

        //init pq and put initial elements.
        PriorityQueue<PQNode> pq = new PriorityQueue<PQNode>(10, new NodeComparator());
        for (int i=0; i<lists.length; i++) {
            if (lists[i] != null) {
                pq.add(new PQNode(i, lists[i]));
            }
        }

        System.out.println("pq: " + pq);

        ListNode curr = null;
        while (!pq.isEmpty()) {
            PQNode pqNode = pq.remove();
            int index = pqNode.index;

            if (head == null) {
                head = pqNode.node;
                curr = head;
            } else {
                curr.next = pqNode.node;
                curr = curr.next;
            }

            lists[index] = lists[index].next;
            if (lists[index] != null) {
                pq.add(new PQNode(index, lists[index]));
            }
        }

        printResult(head);

        return head;
    }

    private void printResult(ListNode node) {
        System.out.println("printing result: ");
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("null");
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

class PQNode {
    int index;
    ListNode node;

    PQNode(int i, ListNode n) {
        index = i;
        node = n;
    }

    @Override
    public String toString() {

        return "[ index:" + index + "; node: " + node.val + "]";
    }
}

class NodeComparator implements Comparator<PQNode> {

    @Override
    public int compare(PQNode o1, PQNode o2) {
        if (o1.node.val > o2.node.val) {
            return 1;
        } else if (o1.node.val < o2.node.val) {
            return -1;
        } else {
            return 0;
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

    @Override
    public String toString() {

        return "[node: " + val + "]";
    }
}


