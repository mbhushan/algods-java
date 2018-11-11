public class LinkedList {

   private ListNode root;

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        ll.init();
        ll.printList();
        System.out.println();
        ll.printRev();
    }

    public void printRev() {
        helper(root);
    }

    private void helper(ListNode node) {
        if (node == null) {
            return;
        }
        helper(node.next);
        System.out.print(node.val + " ");
    }

    public void printList() {
        ListNode node = root;

        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }

        System.out.println();
    }

    public void init() {
        root = new ListNode(5);
        root.next = new ListNode(4);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(2);
        root.next.next.next.next = new ListNode(1);
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
