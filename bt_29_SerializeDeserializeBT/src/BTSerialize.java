import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 LeetCode – Serialize and Deserialize Binary Tree (Java)

 Design an algorithm to serialize and deserialize a binary tree. There is no restriction
 on how your serialization/deserialization algorithm should work. You just need to ensure that
 a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

 Example binary tree

                8
            /       \
           3        10
         /  \      /  \
       1    6     9   14
          /  \        /
         4   7       13
 */

public class BTSerialize {

    private Node root;

    private Node preorderRoot;

    BTSerialize() {
        this.root = null;
        this.preorderRoot = null;
    }

    public static void main(String[] args) {

        BTSerialize bt = new BTSerialize();
        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int [] A = {8, 3, 10, 1, 6, 9, 14,  4, 7, 13};

        bt.buildBST(A);
        bt.inorder();
        System.out.println();

        System.out.println("Preorder serialization: ");
        List<String> preorder = bt.serializePreorder();

//        System.out.println("Inorder serialization: ");
//        bt.serializeInorder();

        bt.deserialize(preorder);

    }

    public void deserialize(List<String> preorder) {
        if (preorder.isEmpty()) {
            return;
        }
        this.preorderRoot = new Node(Integer.parseInt(preorder.get(0)));
        Node node = this.preorderRoot;
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        int size = preorder.size();
        int index = 1;
        while (index < size) {
            String s = preorder.get(index);
            if (!s.equals("#")) {
                while (!stack.isEmpty() && stack.peek().left != null && stack.peek().right != null) {
                    stack.pop();
                    node = stack.peek();
                }
                if (stack.peek().left == null) {
                    node.left = new Node(Integer.parseInt(s));
                    node = node.left;
                    stack.push(node);

                } else  {
                    node.right = new Node(Integer.parseInt(s));
                    node = node.right;
                    stack.push(node);
                }
            } else {
                if (preorder.get(index-1).equals("#")) {
                    stack.pop();
                    node = stack.peek();
                }
            }
            ++index;
        }

        System.out.println("inorder traversal after deserialization: ");
        inorderIterative(this.preorderRoot);

    }



    public void serializeInorder() {
        List<String> buff = new ArrayList<>();
        serializeInorder(this.root, buff);

        System.out.println(buff.toString());
    }

    private void serializeInorder(Node node, List<String> buff) {
        if (node == null) {
            return ;
        }

        if (node.left == null) {
            buff.add("#");
        } else {
            serializeInorder(node.left, buff);
        }

        buff.add(Integer.toString(node.data));

        if (node.right == null) {
            buff.add("#");
        } else {
            serializeInorder(node.right, buff);
        }
    }

    public List<String> serializePreorder() {
        List<String> buff = new ArrayList<>();
        serializePreorder(this.root, buff);

        System.out.println(buff.toString());

        return buff;
    }

    private void serializePreorder(Node node, List<String> buff) {
        if (node == null) {
            return ;
        }

        buff.add(Integer.toString(node.data));

        if (node.left == null) {
            buff.add("#");
        } else {
            serializePreorder(node.left, buff);
        }

        if (node.right == null) {
            buff.add("#");
        } else {
            serializePreorder(node.right, buff);
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
