import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 Connect nodes at same level
 Write a function to connect all the adjacent nodes at the same level in a binary tree.
 Structure of the given Binary Tree node is like following.

 struct node{
 int data;
 struct node* left;
 struct node* right;
 struct node* nextRight;
 }
 Initially, all the nextRight pointers point to garbage values. Your function should set
 these pointers to point next right for each node.

 Example

 Input Tree
     A
    / \
   B   C
  / \   \
 D   E   F


 Output Tree
     A--->NULL
    / \
   B-->C-->NULL
  / \   \
 D-->E-->F-->NULL

 ===========================================================
 Example binary tree (to be connected at each level)
           8
         /    \
        3     10
      /  \     \
     1    6    14
        /  \   /
       4   7  13

 Example Binary Tree:
          1
      /      \
     2        3
    / \      /  \
   4   5    6    7
  / \           / \
 8   9        10   11

 ===================
 INPUT / OUTPUT
 ===================
 inorder traversal:
 1 3 4 6 7 8 10 13 14 connecting levels
 print levels
 8 -> null
 3 -> 10 -> null
 1 -> 6 -> 14 -> null
 */

public class BTConnectLevels {

    private Node root;

    BTConnectLevels() {
        this.root = null;
    }

    public static void main(String[] args) {
        BTConnectLevels bt = new BTConnectLevels();

        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        bt.buildBST(A);

        System.out.println("inorder traversal: ");
        bt.inorder();

        System.out.println("connecting levels");
        bt.connectLevelOrder();

        System.out.println("print levels");
        bt.printLevelNode();


    }

    public void printLevelNode() {
        Node node = this.root;

        while (node != null) {
            Node curr = node;
            while (curr != null) {
                System.out.print(curr.data + " -> ");
                curr = curr.next;
            }
            System.out.println("null");
            node = node.left;
        }
    }

    public void connectLevelOrder() {
        Node node = this.root;

        Queue<Node> queue = new LinkedList<>();
        Node marker = new Node(null);

        queue.add(node);
        queue.add(marker);

        while (!queue.isEmpty()) {
            node = queue.remove();
            if (!queue.isEmpty() && !queue.peek().equals(marker)) {
                node.next = queue.peek();
            }

            if (node.equals(marker)) {
                if (!queue.isEmpty()) {
                    queue.add(marker);
                }
            }

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public void buildBST(int [] A) {
        if (A == null) {
            return;
        }

        for (int i=0; i<A.length; i++) {
            this.root = insertIntoBST(this.root, A[i]);
        }
    }


    public void inorder() {
        inorder(this.root);
    }

    private void inorder(Node node ){
        Stack<Node> stack = new Stack();

        while(node != null || !stack.isEmpty()) {

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

    public Node insertIntoBST(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data <= node.data) {
            node.left = insertIntoBST(node.left, data);
        } else {
            node.right = insertIntoBST(node.right, data);
        }
        return node;
    }
}

class Node {
    Integer data;
    Node left;
    Node right;
    Node next;

    Node (Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.next = null;
    }
}
