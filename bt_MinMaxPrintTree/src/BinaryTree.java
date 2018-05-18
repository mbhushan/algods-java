import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by manib on 5/13/18.

        8
     3      10
  1     6       14
      4   7  13
 */
public class BinaryTree {

    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13 };
        System.out.println("A.len: " + A.length);
        bt.buildBST(A);
        bt.inorder();
        System.out.println();
        System.out.println("min value: " + bt.minValue());
        System.out.println("max value: " + bt.maxValue());

        System.out.println("preorder traversal: ");
        bt.preorder();
        System.out.println();
        System.out.println("postorder traversal: ");
        bt.postorder();
        System.out.println();
        System.out.println("binary tree inorder traversal: ");
        bt.inroder_iterative();
        System.out.println();
        System.out.println("binary tree preorder traversal: ");
        bt.preorder_iterative();
        System.out.println();
        System.out.println("binary tree postorder traversal: ");
        bt.postorder_iterative();
        System.out.println();
        System.out.println("binary tree level order (no new line): ");
        bt.level_order_no_newline();
        System.out.println();
        System.out.println("binary tree level order with new line: ");
        bt.level_order_newline();
        System.out.println();

    }

    public void level_order_newline() {
        if (this.root == null) {
            return;
        }
        Node node = this.root;
        Node marker = new Node(null);

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        queue.add(marker);

        while (!queue.isEmpty()) {
            node = queue.remove();
            if (node.equals(marker)) {
                System.out.println();
                if (!queue.isEmpty()) {
                    queue.add(marker);
                }
            } else {
                System.out.print(node.data + " ");
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public void level_order_no_newline() {
        Queue<Node> queue = new LinkedList<>();
        Node node = this.root;
        if (node == null) {
            return;
        }
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.remove();
            System.out.print(node.data + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public void postorder_iterative() {
        Node node = this.root;
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<Node>();
        stack1.push(node);
        while (!stack1.isEmpty()) {
            node = stack1.pop();
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
            stack2.push(node);
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }
    }

    public void preorder_iterative() {
        Node node = this.root;
        Stack<Node> stack = new Stack<Node>();

        while (node != null || !stack.isEmpty()) {
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

    public void inroder_iterative() {
        Node node = this.root;
        Stack<Node> stack = new Stack<Node>();
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

    public Integer maxValue() {
        return maxValue(this.root);
    }

    private Integer maxValue(Node node) {

        int maxValue = Integer.MIN_VALUE;
        while (node != null) {
            if (node.data > maxValue) {
                maxValue = node.data;
            }
            node = node.right;
        }

        return maxValue;
    }

    public Integer minValue() {
        return minValue(this.root);
    }

    private Integer minValue(Node node) {

        int minValue = Integer.MAX_VALUE;
        while (node != null) {
            if (node.data < minValue) {
                minValue = node.data;
            }
            node = node.left;
        }

        return minValue;
    }


    public void postorder() {
        postorder(this.root);
    }

    private void postorder(Node node) {
        if (node == null) {
            return ;
        }

        if (node.left != null) {
            postorder(node.left);
        }
        if (node.right != null) {
            postorder(node.right);
        }
        System.out.print(node.data + " ");
    }

    public void preorder() {
        preorder(this.root);
    }

    private void preorder(Node node) {
        if (node == null) {
            return ;
        }
        System.out.print(node.data + " ");

        if (node.left != null) {
            preorder(node.left);
        }
        if (node.right != null) {
            preorder(node.right);
        }
    }

    public void inorder() {
        inorder(this.root);
    }

    private void inorder(Node node) {
        if (node == null) {
            return ;
        }
        if (node.left != null) {
            inorder(node.left);
        }
        System.out.print(node.data + " ");
        if (node.right != null) {
            inorder(node.right);
        }
    }

    public void buildBST(int [] A) {
        if (A == null || A.length < 1) {
            return;
        }
        for (int i=0; i<A.length; i++) {
            root = insertIntoBST(root, A[i]);
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

    public Node(Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        Node node = (Node) obj;

        return super.equals(obj);
    }
}
