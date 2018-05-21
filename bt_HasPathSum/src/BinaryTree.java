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

 8. printPaths()
 Given a binary tree, prints out all of its root-to-leaf
 paths, one per line. Uses a recursive helper to do the work.

 9. mirror() Solution (Java)

 Changes the tree into its mirror image.
 So the tree...
     4
    / \
   2   5
  / \
 1  3
 is changed to...
     4
    / \
   5  2
  / \
 3   1
 Uses a recursive helper that recurs over the tree,
 swapping the left/right pointers.
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

        System.out.println("print root to leaf paths: ");
        bt.printPaths();
        System.out.println("preorder iterative before mirror: ");
        bt.preorder_iter();
        System.out.println();
        bt.mirrorBT();
        System.out.println("preorder iterative after mirror: ");
        bt.preorder_iter();
        System.out.println();
    }

    public void mirrorBT() {
        mirrorBT(this.root);
    }

    public void mirrorBT(Node node) {
        if (node == null) {
            return;
        }
        mirrorBT(node.left);
        mirrorBT(node.right);
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    public void preorder_iter() {
        Node node = this.root;
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();

        while(node != null || !stack.isEmpty()) {
            if (node == null) {
                node = stack.pop();
            }
            System.out.print(node.data + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            node = node.left;
        }
    }

    public void printPaths() {
        int [] paths = new int[100];
        printPaths(this.root, paths, 0);
    }

    public void printPaths(Node node, int [] paths, int len) {
        if (node == null) {
            return;
        }
        paths[len++] = node.data;

        if (node.left == null && node.right == null) {
            printPathData(paths, len);
        } else {
            printPaths(node.left, paths, len);
            printPaths(node.right, paths, len);
        }
    }

    public void printPathData(int [] paths, int len) {
        for (int i=0; i<len; i++) {
            System.out.print(paths[i] + " ");
        }
        System.out.println();
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