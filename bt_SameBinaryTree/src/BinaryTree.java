/**

         8
     3      10
 1     6       14
    4    7  13

 Compares the receiver to another tree to
 see if they are structurally identical.
 */
public class BinaryTree {

    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

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
