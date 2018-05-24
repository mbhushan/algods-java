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

        bt.buildBST();
        bt.inorder();

    }

    public void inorder() {
        System.out.println("inorder traversal roo1: ");
        inorder(this.root1);
        System.out.println();
        System.out.println("inorder traversal root2: ");
        inorder(this.root2);
        System.out.println();
        System.out.println("inorder traversal root3: ");
        inorder(this.root3);
        System.out.println();

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

        this.root1 = buildBST(this.root1, A);
        this.root2 = buildBST(this.root2, B);
        this.root3 = buildBST(this.root3, C);
    }

    public Node buildBST(Node root, int [] A) {
        if (A == null || A.length < 1) {
            return null;
        }

        for (int i=0; i<A.length; i++) {
            root = insertBST(root, A[i]);
        }

        return root;
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
