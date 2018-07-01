import java.util.Stack;

/**
 Convert a given Binary Tree to Doubly Linked List | Set 1
 Given a Binary Tree (Bt), convert it to a Doubly Linked List(DLL). The left and right pointers
 in nodes are to be used as previous and next pointers respectively in converted DLL. The order
 of nodes in DLL must be same as Inorder of the given Binary Tree. The first node of Inorder traversal
 (left most node in BT) must be head node of the DLL.

 ==============
 input / output
 ==============
 inorder traversal itertive:
 1 3 4 6 7 8 10 13 14
 printing the DLL:
 1 -> 3 -> 4 -> 6 -> 7 -> 8 -> 10 -> 13 -> 14 -> null
 */

public class BinaryTreeDLL {

    private Node root;

    BinaryTreeDLL() {
        this.root = null;

    }

    public static void main(String[] args) {
        BinaryTreeDLL btt = new BinaryTreeDLL();
        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int[] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        btt.buildBST(A);


        System.out.println("inorder traversal itertive: ");
        btt.inorder();
        System.out.println();

        btt.flattenBT_To_DLL();
    }


    public void flattenBT_To_DLL() {
        Node node = this.root;
        node = flattenBT_DLL(node);

        //lets go the begining of the DLL.
        while (node.left != null) {
            node = node.left;
        }

        System.out.println("printing the DLL: ");
        //print the DLL
        while (node != null) {
            System.out.print(node.data + " -> ");
            node = node.right;
        }

        System.out.print("null");
    }

    private Node flattenBT_DLL(Node node) {

        if (node == null) {
            return null;
        }

        if (node.left != null) {
            Node left = flattenBT_DLL(node.left);

            //find the inorder predecessor. we will link predecessor to curr node.
            while (left.right != null) {
                left = left.right;
            }
            left.right = node;
            node.left = left;
        }

        if (node.right != null) {
            Node right = flattenBT_DLL(node.right);

            while(right.left != null) {
                right = right.left;
            }
            right.left = node;
            node.right = right;
        }

        return node;
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