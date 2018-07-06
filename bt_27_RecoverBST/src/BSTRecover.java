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

       15
      / \
     9   28
    /   / \
   6  20  34
  / \    /  \
 2   5  30  41

 ===================
 INPUT / OUTPUT
 ===================
 inorder traversal of broken BST:
 2 20 6 9 15 5 28 30 34 41
 swapped nodes are:
 20 and 5
 fixed binary search tree:
 2 5 6 9 15 20 28 30 34 41

 inorder traversal of broken BST:
 2 6 5 9 15 20 28 30 34 41
 swapped nodes are:
 6 and 5
 fixed binary search tree:
 2 5 6 9 15 20 28 30 34 41
 */

public class BSTRecover {

    private Node root;
    private Node root2;

    BSTRecover() {
        this.root = null;
        this.root2 = null;
    }

    public static void main(String [] args) {

        BSTRecover bt = new BSTRecover();
        bt.buildBT();

        System.out.println("inorder traversal of broken BST: ");
        bt.inorder1();
        System.out.println();
        System.out.println();

        bt.buildBT2();
        System.out.println("inorder traversal of broken BST: ");
        bt.inorder2();
    }

    public void inorder1() {
        inorderIterative(this.root);
    }

    public void inorder2() {
        inorderIterative(this.root2);
    }

    private void inorderIterative(Node node) {
        Stack<Node> stack = new Stack<>();
        Node oldNode = node;
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
                       second = node;
                   } else {
                       second = node;
                   }
                }
                stack.push(node);
                node = node.left;
            }
        }

        System.out.println();
        System.out.println("swapped nodes are: ");
        if (first == null) {
            System.out.println("No swapped nodes found!");
        } else {
            System.out.println(first.data + " and " + second.data);
            int buff = first.data;
            first.data = second.data;
            second.data = buff;
        }

        System.out.println("fixed binary search tree: ");
        inorderRec(oldNode);
    }

//    public void inorderRec() {
//        inorderRec(this.root2);
//    }

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

    public void buildBT2() {
        this.root2 = new Node(15);
        this.root2.left = new Node(9);
        this.root2.right = new Node(28);

        this.root2.left.left = new Node(6);
        this.root2.right.left = new Node(20);
        this.root2.right.right = new Node(34);

        this.root2.left.left.left = new Node(2);
        this.root2.left.left.right = new Node(5);

        this.root2.right.right.left = new Node(30);
        this.root2.right.right.right = new Node(41);

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
