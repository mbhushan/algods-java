import java.util.Stack;

/**"
 LeetCode – Lowest Common Ancestor of a Binary Tree (Java)

 Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

             8
          /    \
         3     10
       /  \     \
      1    6    14
         /  \    \
        4   7    13
 */
public class LCABinaryTree {

    private Node root;

    LCABinaryTree() {
        this.root = null;
    }

    public static void main(String[] args) {
        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        LCABinaryTree bt = new LCABinaryTree();

        bt.buildBST(A);
        System.out.println("inorder traversal: ");
        bt.inorder();

        System.out.println();
        int first = 1;
        int second = 7;
        bt.findLCA(first, second);
    }

    public void findLCA(int first, int second) {
        Node node = findLCA(this.root, first, second);

        System.out.println("LCA is: " + node.data);

        System.out.println("Checking LCA with Node presence: ");
        boolean [] firstNode = new boolean[1];
        boolean [] secondNode = new boolean[1];
        firstNode[0] = false;
        secondNode[0] = false;

        node = findLCANodeWithNodeCheck(this.root, first, second, firstNode, secondNode);
        if (firstNode[0] && secondNode[0]) {
            System.out.println("LCA findLCANodeWithNodeCheck: " + node.data);
        } else {
            System.out.println("One or both search nodes not present in Binary Tree");
        }

    }

    private Node findLCANodeWithNodeCheck(Node node, int first, int second, boolean [] firstNode, boolean []
            secondNode) {
        if (node == null) {
            return null;
        }

        if (node.data == first) {
            firstNode[0] = true;
        }

        if (node.data == second) {
            secondNode[0] = true;
        }

        if (node.data == first || node.data == second) {
            return node;
        }

        Node left = findLCANodeWithNodeCheck(node.left, first, second, firstNode, secondNode);
        Node right = findLCANodeWithNodeCheck(node.right, first, second, firstNode, secondNode);

        if (left != null && right != null) {
            return node;
        }

        if (left == null && right == null) {
            return null;
        }

        return (left == null ? right : left);
    }

    private Node findLCA(Node node, int first, int second) {
        if (node == null) {
            return null;
        }

        if (node.data == first || node.data == second) {
            return node;
        }

        Node left = findLCA(node.left, first, second);
        Node right = findLCA(node.right, first, second);

        if (left != null && right != null) {
            return node;
        }

        if (left == null && right == null) {
            return null;
        }

        return (left == null ? right : left);
    }

    public void buildBST(int [] A) {
        if (A == null) {
            return;
        }

        for (int i=0; i<A.length; i++) {
            this.root = insertIntoBST(this.root, A[i]);
        }
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

    Node (Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
