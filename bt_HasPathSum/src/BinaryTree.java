import java.util.Stack;

/**
 * Created by manib on 5/20/18.

              8
          3      10
      1     6       14
          4   7  13

 7. hasPathSum()
 Given a tree and a sum, returns true if there is a path from the root
 down to a leaf, such that adding up all the values along the path
 equals the given sum.
 Strategy: subtract the node value from the sum when recurring down,
 and check to see if the sum is 0 when you run out of tree.
 */
public class BinaryTree {

    private Node root;

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13 };
        System.out.println("A.len: " + A.length);
        bt.buildBST(A);
        bt.inorder();
        System.out.println();
        int [] targetSum = {45, 12, 21, 29};

        for (int i=0; i<targetSum.length; i++) {
            System.out.println("has path sum " + targetSum[i] + "? " + bt.hasPathSum(targetSum[i]));
        }
    }

    public boolean hasPathSum(int sum) {
        Node node = this.root;

        return hasPathSum(node, sum);
    }

    public boolean hasPathSum(Node node, int sum) {
        if (node == null) {
            return sum == 0;
        }

        return hasPathSum(node.left, sum - node.data) || hasPathSum(node.right, sum - node.data);
    }


    public void inorder() {
        Node node = this.root;
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (node == null) {
                node = stack.pop();
                System.out.print(node.data + " ");
                node = node.right;
            }
        }
    }

    public void buildBST(int [] A) {
        if (A == null || A.length < 1) {
            return;
        }

        for (int i=0; i<A.length; i++) {
            this.root = insertBST(this.root, A[i]);
        }
    }

    public Node insertBST(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value <= node.data) {
            node.left = insertBST(node.left, value);
        } else {
            node.right = insertBST(node.right, value);
        }

        return node;
    }

}

class Node {
    Integer data;
    Node left;
    Node right;

    public Node(Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}