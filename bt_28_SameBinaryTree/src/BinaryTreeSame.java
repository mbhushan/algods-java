import java.util.Stack;

/**
 Leetcode – Same Tree

 Two binary trees are considered the same if they have identical structure and nodes have the same value.


 */
public class BinaryTreeSame {

    private Node root;
    private Node root2;

    BinaryTreeSame() {
        this.root = null;
        this.root2 = null;
    }

    public static void main(String [] args) {

        BinaryTreeSame bt = new BinaryTreeSame();
        bt.buildBT();
        bt.buildBT2();

        System.out.println("inorder of broken BST: ");
        bt.inorder1();
        System.out.println();
        System.out.println();
        System.out.println("inorder of broken BST: ");
        bt.inorder2();
    }

    public void inorder1() {
        inorderIterative(this.root);
    }

    public void inorder2() {
        inorderIterative(this.root2);
    }

    private void inorderIterative(Node node) {
        Stack<Node> stack = new Stack<>();
        Node oldNode = node;
        Node first = null;
        Node second = null;
        Node prev = null;

        while (node != null || !stack.isEmpty()) {
            if (node == null) {
                node = stack.pop();
                System.out.print(node.data + " ");
                prev = node;
                node = node.right;
            }
            if (node != null) {
                if (prev != null && prev.data > node.data) {
                    if (first == null) {
                        first = prev;
                        second = node;
                    } else {
                        second = node;
                    }
                }
                stack.push(node);
                node = node.left;
            }
        }

        System.out.println();
        System.out.println("swapped nodes are: ");
        if (first == null) {
            System.out.println("No swapped nodes found!");
        } else {
            System.out.println(first.data + " and " + second.data);
            int buff = first.data;
            first.data = second.data;
            second.data = buff;
        }

        System.out.println("fixed binary search tree: ");
        inorderRec(oldNode);
    }

//    public void inorderRec() {
//        inorderRec(this.root2);
//    }

    private void inorderRec(Node node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            inorderRec(node.left);
        }

        System.out.print(node.data + " ");

        if (node.right != null) {
            inorderRec(node.right);
        }
    }

    public void buildBT() {
        this.root = new Node(15);
        this.root.left = new Node(9);
        this.root.right = new Node(28);

        this.root.left.left = new Node(20);
        this.root.right.left = new Node(5);
        this.root.right.right = new Node(34);

        this.root.left.left.left = new Node(2);
        this.root.left.left.right = new Node(6);

        this.root.right.right.left = new Node(30);
        this.root.right.right.right = new Node(41);

    }

    public void buildBT2() {
        this.root2 = new Node(15);
        this.root2.left = new Node(9);
        this.root2.right = new Node(28);

        this.root2.left.left = new Node(6);
        this.root2.right.left = new Node(20);
        this.root2.right.right = new Node(34);

        this.root2.left.left.left = new Node(2);
        this.root2.left.left.right = new Node(5);

        this.root2.right.right.left = new Node(30);
        this.root2.right.right.right = new Node(41);

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
