import java.util.Arrays;
import java.util.Stack;

/**
 LeetCode – Convert Sorted Array to Binary Search Tree (Java)

 Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

 ============
 INPUT/OUTPUT
 ============
 inorder traversal post building BST:
 1 2 3 4 5 6 7 8
 */

public class BSTBuild {

    private Node root;

    BSTBuild() {
        this.root = null;
    }

    public static void main(String[] args) {
        BSTBuild bst = new BSTBuild();

        int [] A = {4, 2, 5, 1, 6, 7, 3, 8};

        bst.buildBST(A);

        System.out.println("inorder traversal post building BST: ");
        bst.inorder();

    }

    public void buildBST(int [] A) {
        if (A == null) {
            return;
        }

        Arrays.sort(A);

        this.root = buildBST(this.root, A, 0, A.length-1);
    }

    private Node buildBST(Node node, int [] A, int low, int high) {
        if (low > high) {
            return null;
        }

        int mid = low + (high - low) / 2;
        int rootVal = A[mid];
        node = new Node(rootVal);

        node.left = buildBST(node.left, A, low, mid-1);
        node.right = buildBST(node.right, A, mid+1, high);

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
