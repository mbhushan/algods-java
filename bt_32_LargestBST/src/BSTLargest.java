/**
 LeetCode – Largest BST Subtree (Java)

 Given a binary tree, find the largest subtree which is a Binary Search Tree (BST),
 where largest means subtree with largest number of nodes in it.

 Input:
       5
     /  \
    2    4
  /  \
 1    3

 Output: 3

 The following subtree is the maximum size BST subtree
    2
  /  \
 1    3


 Input:
         50
       /    \
     30       60
    /  \     /  \
   5   20   45    70
                /  \
              65    80
 Output: 5
 The following subtree is the maximum size BST subtree

      60
     /  \
   45    70
        /  \
       65  80
 */

public class BSTLargest {

    private Node root;
    private Node root2;

    BSTLargest() {
        this.root = null;
        this.root2 = null;
    }

    public static void main(String[] args) {

        BSTLargest bst = new BSTLargest();

        bst.buildT1();
        System.out.println("inorder traversal: ");
        bst.inorder();
        System.out.println();
        bst.findLargestBST();
    }

    public void findLargestBST() {

        Result result = new Result();

        Node [] buff = new Node[1];
        buff[0] = null;

        result = findLargestBST(this.root, buff);
        System.out.println(result);
        System.out.println("max BST size: " + result.bstSize);

        if (buff[0] == null) {
            System.out.println("buff is null!");
        } else {
            System.out.println("max BST root node val: " + buff[0].data);
        }

        System.out.println("inorder of max BST: ");
        inorder(buff[0]);

    }

    private Result findLargestBST(Node node, Node [] buff) {
        Result result = new Result();
        if (node == null) {
            result.isBST = true;
            return result;
        }

        Result left = findLargestBST(node.left, buff);
        Result right = findLargestBST(node.right, buff);

        result.low = Math.min(left.low, node.data);
        result.high = Math.max(right.high, node.data);

        if (left.isBST && right.isBST && node.data > left.high && node.data < right.low) {
            result.bstSize = 1 + left.bstSize + right.bstSize;
            result.isBST = true;
            buff[0] = node;
        } else {
            result.bstSize = Math.max(left.bstSize, right.bstSize);
            result.isBST = false;
        }
        return result;
    }

    public void inorder() {
        inorder(this.root);
    }

    private void inorder(Node node ){
        if (node == null) {
            return;
        }

        if (node.left != null) {
            inorder(node.left);
        }

        System.out.print(node.data + " ");

        if (node.right != null) {
            inorder(node.right);
        }
    }

    public void buildT1() {
        this.root = new Node(50);

        this.root.left = new Node(30);
        this.root.right = new Node(60);

        this.root.left.left = new Node(5);
        this.root.left.right = new Node(20);
        this.root.right.left = new Node(45);
        this.root.right.right = new Node(70);

        this.root.right.right.left = new Node(65);
        this.root.right.right.right = new Node(80);
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

class Result {
    int low;
    int high;
    int bstSize;
    boolean isBST;

    Result() {
        low = Integer.MAX_VALUE;
        high = Integer.MIN_VALUE;
        bstSize = 0;
        isBST = false;
    }

    @Override
    public String toString() {
        return "[low: " + low + ", high: " + high + ", BST size: " + bstSize + ", isBST: " + isBST + "]";
    }
}
