import java.util.Stack;

/**
 LeetCode – Convert Sorted List to Binary Search Tree (Java)

 Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

 ===============
 INPUT / OUTPUT
 ==============
 printing list:
 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> null
 building bst from sorted list done!
 inorder traversal:
 1 2 3 4 5 6 7 8 9 10

 */

public class BSTList {

    private Node root;
    private ListNode head;
    private static ListNode curr;

    BSTList() {
        this.root = null;
        this.head = null;
    }

    public static void main(String[] args) {

        BSTList bt = new BSTList();

        int [] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        bt.buildList(A);
        System.out.println("printing list: ");
        bt.printList();

        bt.sortedListToBST();

        System.out.println("inorder traversal: ");
        bt.inorder();

    }

    public void sortedListToBST() {
        int len = getListLength();
        int start = 0;
        curr = this.head;

        this.root = sortedListToBST(start, len-1);
        System.out.println("building bst from sorted list done!");
    }

    private Node sortedListToBST( int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;

        Node left = sortedListToBST(start, mid - 1);
        Node node = new Node(curr.val);
        curr = curr.next;
        Node right = sortedListToBST( mid + 1, end);

        node.left = left;
        node.right = right;

        return node;
    }

    private int getListLength() {
        int len = 0;
        ListNode node = this.head;

        while (node != null) {
            ++len;
            node = node.next;
        }

        return len;
    }

    public void buildList(int [] A) {
        if (A == null) {
            return;
        }

        ListNode node = null;

        for (int i=0; i<A.length; i++) {
            ListNode temp = new ListNode(A[i]);
            if (this.head == null) {
                this.head = temp;
                node = this.head;
            } else {
                node.next = temp;
                node = node.next;
            }
        }
    }

    public void printList() {
        ListNode node = this.head;

        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println("null");
    }

    public void inorder() {
        inorderIterative(this.root);
    }

    private void inorderIterative(Node node) {
        Stack<Node> stack = new Stack<>();

        while (node != null || !stack.isEmpty()) {
            if (node == null) {
                node = stack.pop();
                System.out.print(node.data + " ");
                node = node.right;
            }
            if (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }
}

class Node {
    Integer data;
    Node left;
    Node right;

    Node(Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(Integer x) {
        val = x;
        next = null;
    }
}
