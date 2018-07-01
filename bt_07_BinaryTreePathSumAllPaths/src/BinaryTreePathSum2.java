import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 LeetCode – Path Sum II (Java)

 Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

 For example, given the below binary tree and sum = 22,


       5
      / \
     4   8
    /   / \
   11  13  4
  /  \    / \
 7    2  5   1

 the method returns the following:

 [
 [5,4,11,2],
 [5,8,4,5]
 ]

 ===================
 INPUT / OUTPUT
 ===================
 inorder iterative:
 7 11 2 4 5 13 8 5 4 1
 Paths in binary tree with sum: 22
 [5, 4, 11, 2]
 [5, 8, 4, 5]

 */

public class BinaryTreePathSum2 {

    private Node root;

    BinaryTreePathSum2() {
        this.root = null;
    }

    public static void main(String [] args) {

        BinaryTreePathSum2 bt = new BinaryTreePathSum2();
        bt.buildBT();

        System.out.println("inorder iterative: ");
        bt.inorder();
        System.out.println();

        int sum = 22;
        bt.findSumPaths(sum);

    }

    public void findSumPaths(int sum) {
        List<List<Integer>> resultPaths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        path.add(this.root.data);
        findSumPaths(this.root, sum - this.root.data, resultPaths, path);


        System.out.println("Paths in binary tree with sum: " + sum);
        for (List<Integer> result: resultPaths) {
            System.out.println(result);
        }
    }

    public void findSumPaths(Node node, int sum, List<List<Integer>> resultPaths, List<Integer> path) {

        //check if leaf node
        if (node.left == null && node.right == null && 0 == sum) {
            //path.add(node.data);
            List<Integer> temp = new ArrayList<>();
            temp.addAll(path);
            resultPaths.add(temp);
            return;
        }

        //search on the left subtree
        if (node.left != null) {
            path.add(node.left.data);
            findSumPaths(node.left, sum - node.left.data, resultPaths, path);
            path.remove(path.size()-1);
        }

        //search on the right subtree
        if (node.right != null) {
            path.add(node.right.data);
            findSumPaths(node.right, sum - node.right.data, resultPaths, path);
            path.remove(path.size()-1);
        }
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

    public void buildBT() {
        this.root = new Node(5);
        this.root.left = new Node(4);
        this.root.right = new Node(8);

        this.root.left.left = new Node(11);
        this.root.right.left = new Node(13);
        this.root.right.right = new Node(4);

        this.root.left.left.left = new Node(7);
        this.root.left.left.right = new Node(2);

        this.root.right.right.left = new Node(5);
        this.root.right.right.right = new Node(1);

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
