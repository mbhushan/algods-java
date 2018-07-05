import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 LeetCode – Sum Root to Leaf Numbers (Java)

 Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 Find the total sum of all root-to-leaf numbers.

 For example,


   1
  / \
 2   3
 The root-to-leaf path 1->2 represents the number 12.
 The root-to-leaf path 1->3 represents the number 13.
 Return the sum = 12 + 13 = 25.

 Binary Tree:
            6
         /     \
        2       7
      /  \      \
    1    4      9
        /  \   /
       3   5  8

 ================
 INPUT / OUTPUT
 ================
 inorder traversal itertive:
 1 2 3 4 5 6 7 8 9
 root to leaf paths:
 [6, 2, 1]
 [6, 2, 4, 3]
 [6, 2, 4, 5]
 [6, 7, 9, 8]
 numbers and sum:
 621
 6243
 6245
 6798
 total sum: 19907
 */


public class RootToLeaf {

    private Node root;

    RootToLeaf() {
        this.root = null;
    }


    public static void main(String[] args) {

        RootToLeaf btt = new RootToLeaf();

        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int [] A = {6, 2, 7, 1, 4, 9, 3, 5, 8};
        btt.buildBST(A);
        btt.inorder();
        System.out.println();

        btt.rootToLeaf();

    }

    public void rootToLeaf() {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> buff = new ArrayList<>();

        rootToLeaf(this.root, buff, result);

        StringBuffer sb = new StringBuffer();
        List<String> paths = new ArrayList<>();

        System.out.println("root to leaf paths: ");
        for (List<Integer> list: result) {
            System.out.println(list.toString());
            for (Integer s: list) {
                sb.append(s);
            }
            paths.add(sb.toString());
            sb = new StringBuffer();

        }
        long sum = 0;

        System.out.println("numbers and sum: ");
        for (String s: paths) {
            System.out.println(s);
            sum += Integer.parseInt(s);
        }
        System.out.println("total sum: " + sum);
        System.out.println();

    }

    private void rootToLeaf(Node node, List<Integer> buff, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            buff.add(node.data);
            List<Integer> temp = new ArrayList<>();
            temp.addAll(buff);
            result.add(temp);
            return;
        }

        buff.add(node.data);

        if (node.left != null) {
            rootToLeaf(node.left, buff, result);
            buff.remove(buff.size()-1);
        }

        if (node.right != null) {
            rootToLeaf(node.right, buff, result);
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
