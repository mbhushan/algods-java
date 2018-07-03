import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 LeetCode – Minimum Depth of Binary Tree (Java)

 Given a binary tree, find its minimum depth.

 The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 */

public class BinaryTreeMinDepth {

    private Node root;

    BinaryTreeMinDepth() {
        this.root = null;
    }

    public static void main(String[] args) {
        BinaryTreeMinDepth bt = new BinaryTreeMinDepth();

        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        //int[] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        int [] A = {30, 9, 200, 150, 700};
        bt.buildBST(A);

        System.out.println("inorder traversal itertive: ");
        bt.inorder();
        System.out.println();

        bt.minDepth();

        System.out.println("");
        bt.minDepth1();
        //System.out.println("min depth iterative: ");
        bt.minDepthIterative();

        bt.minDepth2();


    }

    public void minDepth2() {
        int depth = minDepth2(this.root);
        System.out.println("min depth2: " + depth);
    }

    private int minDepth2(Node node) {
        if (node == null) {
            return 0;
        }

        int ldepth = minDepth2(node.left);
        int rdepth = minDepth2(node.right);

        return Math.min(ldepth, rdepth)+1;
    }

    public void minDepthIterative() {
        Node node = this.root;
        Queue<Node> queue = new LinkedList<>();
        Node marker = new Node(null);

        queue.add(node);
        queue.add(marker);
        int depth = 0;
        while (!queue.isEmpty()) {
            node = queue.remove();

            if (node.equals(marker)) {
                ++depth;
                if (!queue.isEmpty()) {
                    queue.add(marker);
                }
            }

            if (node.left == null && node.right == null) {
                break;
            }

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }

        System.out.println("min depth iterative: " + (depth+1));

    }

    public void minDepth1() {
        int depth = minDepth1(this.root);
        System.out.println("mindepth1 sol: " + depth);
    }

    public int minDepth1(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }
        int ldepth = 1;
        int rdepth = 1;
        if (node.left != null) {
            ldepth = minDepth1(node.left);
        }

        if (node.right != null) {
            rdepth = minDepth1(node.right);
        }

        return Math.min(ldepth, rdepth)+1;

    }

    public void minDepth() {
        List<Integer> list = new ArrayList<>(1);
        list.add(Integer.MAX_VALUE);
        minDepth(this.root, list, 1);

        System.out.println("min depth: " + list.get(0));
    }

    private void minDepth(Node node, List<Integer> result, int depth) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            int size = result.get(0);
            if (depth < size) {
                result.set(0, depth);
            }
            return;
        }


        minDepth(node.left, result, depth+1);
        minDepth(node.right, result, depth+1);
        --depth;
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
}

class Node {
    Integer data;
    Node left;
    Node right;

    Node (Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
