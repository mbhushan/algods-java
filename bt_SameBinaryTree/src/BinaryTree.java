import java.util.Stack;

/**

         8
     3      10
 1     6       14
    4    7  13

 Compares the receiver to another tree to
 see if they are structurally identical.
 */
public class BinaryTree {

    private Node root1;
    private Node root2;
    private Node root3;

    public BinaryTree() {
        this.root1 = null;
        this.root2 = null;
        this.root3 = null;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();


    }

   public void inorder(Node node) {
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

    public void buildBST() {
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13 };
        int [] B = {8, 3, 10, 1, 6, 14, 4, 7, 13 };
        int [] C = {8, 3, 10, 1, 6, 14, 4, 7, 23 };

        buildBST(this.root1, A);
        buildBST(this.root2, B);
        buildBST(this.root3, C);
    }

    public void buildBST(Node root, int [] A) {
        if (A == null || A.length < 1) {
            return;
        }

        for (int i=0; i<A.length; i++) {
            root = insertBST(root, A[i]);
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
