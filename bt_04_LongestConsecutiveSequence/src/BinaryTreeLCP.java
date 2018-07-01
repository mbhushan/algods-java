import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node in the tree
 * along the parent-child connections. The longest consecutive path need to be from
 * parent to child (cannot be the reverse).
 *
 ================
 INPUT / OUTPUT
 ================
 inorder traversal itertive:
 1 3 4 6 7 8 9 10 13 14
 LCP: 3
 LCP size iteratively: 3
 Check is binary tree is BST
 is BST: true
 */


public class BinaryTreeLCP {
    private Node root;

    BinaryTreeLCP() {
        this.root = null;
    }

    public static void main(String [] args) {
        BinaryTreeLCP btt = new BinaryTreeLCP();

        int[] A = {9, 3, 10, 1, 6, 14, 4, 7, 13, 8};
        btt.buildBST(A);


        System.out.println("inorder traversal itertive: ");
        btt.inorder();
        System.out.println();

        btt.findLCP();
        btt.findLCPIterative();

        System.out.println("Check is binary tree is BST ");
        btt.isBST();
    }

    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     */
    public void isBST(){
        boolean ans = isBST(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("is BST: " + ans);
    }

    private boolean isBST(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.data < min || node.data > max) {
            return false;
        }

        return (isBST(node.left, min, node.data-1) && isBST(node.right, node.data+1, max));

    }

    public void findLCPIterative() {
        Node node = this.root;

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int lcpSize = 0;
        int leftSize = 0;
        int rightSize = 0;

        while(!queue.isEmpty()) {
            node = queue.remove();

            if (node.left != null) {
                queue.add(node.left);
                if (node.data+1 == node.left.data) {
                    leftSize += 1;
                } else {
                    leftSize = 1;
                }
            }
            if (node.right != null) {
                queue.add(node.right);
                if (node.data+1 == node.right.data) {
                    rightSize += 1;
                } else {
                    rightSize = 1;
                }
            }
            lcpSize = Math.max(lcpSize, Math.max(leftSize, rightSize));
        }

        System.out.println("LCP size iteratively: " + lcpSize);
    }

    public void findLCP() {

        List<Integer> result = new ArrayList<>(1);
        result.add(Integer.MIN_VALUE);

        Integer buff = Integer.MIN_VALUE;

        findLCP(this.root, result);
        System.out.println("LCP: " + result.get(0));
        //System.out.println("LCP with buff: " + buff);
    }

    private int findLCP(Node node, List<Integer> result) {
        if (node == null) {
            return 0;
        }

        int left = findLCP(node.left, result);
        int right = findLCP(node.right, result);

        int leftSize = 0;
        int rightSize = 0;

        if (node.left == null) {
            leftSize = 1;
        } else if (node.data+1 == node.left.data){
            leftSize = left+1;
        } else {
            leftSize = 1;
        }

        if (node.right == null) {
            rightSize = 1;
        } else if (node.data+1 == node.right.data) {
            rightSize = right + 1;
        } else {
            rightSize = 1;
        }

        int ans = Math.max(Math.max(leftSize, rightSize), result.get(0));
        result.set(0, ans);

        return Math.max(leftSize, rightSize);
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
