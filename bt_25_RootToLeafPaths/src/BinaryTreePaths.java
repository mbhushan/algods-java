import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 LeetCode – Binary Tree Paths (Java)

 Given a binary tree, return all root-to-leaf paths.

 Example binary tree

              8
          /       \
         3        10
        /  \       \
       1    6      14
          /  \   /
         4   7  13

 =====================
 INPUT / OUTPUT
 ====================
 inorder traversal itertive:
 1 3 4 6 7 8 10 13 14
 Printing paths from root to leafs
 [8, 3, 1]
 [8, 3, 6, 4]
 [8, 3, 6, 7]
 [8, 10, 14, 13]
 */

public class BinaryTreePaths {

    private Node root;

    BinaryTreePaths() {
        this.root = null;
    }

    public static void main(String[] args) {
        BinaryTreePaths bt = new BinaryTreePaths();
        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        bt.buildBST(A);
        bt.inorder();
        System.out.println();

        bt.printPaths();
    }

    public void printPaths() {
        List<List<Integer>> pathList = new ArrayList<>();
        List<Integer> buff = new ArrayList<>();

        printPaths(this.root, buff, pathList);

        System.out.println("Printing paths from root to leafs");
        for (List<Integer> path: pathList) {
            System.out.println(path.toString());
        }
        System.out.println();
    }

    private void printPaths(Node node, List<Integer> buff, List<List<Integer>> pathList) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            buff.add(node.data) ;
            List<Integer> path = new ArrayList<>();
            path.addAll(buff);
            pathList.add(path);
            return;
        }

        buff.add(node.data);

        if (node.left != null) {
            printPaths(node.left, buff, pathList);
            buff.remove(buff.size()-1);
        }

        if (node.right != null) {
            printPaths(node.right, buff, pathList);
            buff.remove(buff.size()-1);
        }
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
