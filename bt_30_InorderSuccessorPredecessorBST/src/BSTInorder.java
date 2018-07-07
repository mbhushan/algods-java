import java.util.Stack;

/**
 LeetCode – Inorder Successor in BST (Java)

 Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

               8
          /       \
         3        10
       /  \         \
     1    6         14
         /  \      /
        4   7    13
 */
public class BSTInorder {

    private Node root;

    public static void main(String[] args) {
        BSTInorder bt = new BSTInorder();
        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        bt.buildBST(A);
        bt.inorder();
        System.out.println();

        int [] values = {1, 3, 8, 13, 14, 10};

        for (int i=0; i<values.length; i++) {
            bt.inorderSuccessor(values[i]);
        }
    }

    public void inorderSuccessor(int n) {
        Node node = this.root;
        inorderSuccessor(this.root, n);
    }

    private Integer inorderSuccessor(Node node, int n) {
       Node ptr = null;
       boolean found = false;

       while (node != null) {
           if (n < node.data) {
               ptr = node;
               node = node.left;
           } else if (n > node.data){
               node = node.right;
           } else if (n == node.data){
               found = true;
               break;
           }
       }

       if (node != null && node.right != null) {
           node = node.right;
           while (node.left != null) {
               node = node.left;
           }
           ptr = node;
       }

       if (found && ptr != null) {
           System.out.println("successor of " + n + ": " + ptr.data);
           return ptr.data;
       }

        System.out.println("successor NOT found for " + n);

       return null;


    }

    public void inorder() {
        System.out.println("inorder traversal itertive: ");
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

    public void buildBST(int [] A) {
        if (A == null) {
            return;
        }

        for (int i=0; i<A.length; i++) {
            this.root = insertIntoBST(this.root, A[i]);
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

    Node(Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
