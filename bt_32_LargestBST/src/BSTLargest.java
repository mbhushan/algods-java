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
       65    80
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
        bst.inorder();
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
