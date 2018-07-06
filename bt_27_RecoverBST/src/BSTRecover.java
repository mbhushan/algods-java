import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 LeetCode – Recover Binary Search Tree (Java)
 Two elements of a binary search tree (BST) are swapped by mistake. Recover the tree without changing its structure.

 Java Solution
 Inorder traveral will return values in an increasing order. So if an element is less than its previous element,
 the previous element is a swapped node.

       15
      / \
     9   28
    /   / \
   20  5  34
  / \    /  \
 2   6  30  41

 ===================
 INPUT / OUTPUT
 ===================
 */

public class BSTRecover {

    private Node root;

    BSTRecover() {
        this.root = null;
    }

    public static void main(String [] args) {

        BSTRecover bt = new BSTRecover();
        bt.buildBT();

        System.out.println("inorder iterative: ");
        bt.inorder();
        System.out.println();
    }

    public void inorder() {
        inorderIterative(this.root);
    }

    private void inorderIterative(Node node) {
        Stack<Node> stack = new Stack<>();
        List<Node> swappedNodes = new ArrayList<>();
        Node first = null;
        Node second = null;

        Node prev = null;

        while (node != null || !stack.isEmpty()) {
            if (node == null) {
                node = stack.pop();
                System.out.print(node.data + " ");
                prev = node;
                node = node.right;
            }
            if (node != null) {
                if (prev != null && prev.data > node.data) {
                   if (first == null) {
                       first = prev;
                   } else {
                       second = node;
                   }
                }
                stack.push(node);
                node = node.left;
            }
        }

        System.out.println("swapped nodes are: ");
        System.out.println(first.data + " and " + second.data);

        int buff = first.data;
        first.data = second.data;
        second.data = buff;

        inorderRec(this.root);
    }

    private void inorderRec(Node node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            inorderRec(node.left);
        }

        System.out.print(node.data + " ");

        if (node.right != null) {
            inorderRec(node.right);
        }
    }

    public void buildBT() {
        this.root = new Node(15);
        this.root.left = new Node(9);
        this.root.right = new Node(28);

        this.root.left.left = new Node(20);
        this.root.right.left = new Node(5);
        this.root.right.right = new Node(34);

        this.root.left.left.left = new Node(2);
        this.root.left.left.right = new Node(6);

        this.root.right.right.left = new Node(30);
        this.root.right.right.right = new Node(41);

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
