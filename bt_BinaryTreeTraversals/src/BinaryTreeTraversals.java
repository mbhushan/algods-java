import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 Preorder,
 Inorder,
 Postorder,
 Level Order,
 Level Order II,
 Vertical Order

 ===============
 INPUT / OUTPUT
 ===============
 inorder traversal recursive:
 1 3 4 6 7 8 10 13 14
 inorder traversal itertive:
 1 3 4 6 7 8 10 13 14
 preorder traversal recursive:
 8 3 1 6 4 7 10 14 13
 preorder traversal iterative:
 8 3 1 6 4 7 10 14 13
 postorder traversal recursive:
 1 4 7 6 3 13 14 10 8
 postorder traversal iterative (2 stack):
 1 4 7 6 3 13 14 10 8
 postorder traversal iterative (1 stack):
 1 4 7 6 3 13 14 10 8

 level order with new line on each level:
 8
 3 10
 1 6 14
 4 7 13
 */
public class BinaryTreeTraversals {

    private Node root;

    BinaryTreeTraversals() {
        this.root = null;
    }

    public static void main(String [] args) {
        BinaryTreeTraversals btt = new BinaryTreeTraversals();

        // https://en.wikipedia.org/wiki/Binary_search_tree#/media/File:Binary_search_tree.svg
        int [] A = {8, 3, 10, 1, 6, 14, 4, 7, 13};
        btt.buildBST(A);
        btt.inorder();
        System.out.println();
        btt.preorder();
        System.out.println();
        btt.postorder();

        System.out.println();
        System.out.println("level order with new line on each level: ");
        btt.levelOrder();
        System.out.println();
        System.out.println("level order bottoms up: ");
        btt.levelOrderBottomsup();

        System.out.println("vertical order of a binary tree: ");
        btt.verticalOrder();
    }

    public void verticalOrder() {
        Map<Integer, List<Node>> map = new HashMap<>();
        int index = 0;
        verticalOrder(this.root, map, index);

        Set<Integer> keys = map.keySet();
        List sortedList = new ArrayList(keys);
        Collections.sort(sortedList);
        Iterator<Integer> iter = sortedList.iterator();
        while (iter.hasNext()) {
            int i = iter.next();
            List<Node> list = map.get(i);
            Iterator<Node> it = list.iterator();
            while (it.hasNext()) {
                Node node = it.next();
                System.out.print(node.data + " ");
            }
            System.out.println();
        }
    }

    private void verticalOrder(Node node, Map<Integer, List<Node>> map, int index) {

        if (node == null) {
            return;
        }
        List<Node> list = new ArrayList<>();
        if (map.containsKey(index)) {
             list = map.get(index);

        }
        list.add(node);
        map.put(index, list);

        if (node.left != null) {
            verticalOrder(node.left, map, index-1);
        }
        if (node.right != null) {
            verticalOrder(node.right, map, index+1);
        }

    }



    public void levelOrderBottomsup() {
        levelOrderBottomsup(this.root);
    }

    //Given a binary tree, return the bottom-up level order traversal of its nodes' values.
    private void levelOrderBottomsup(Node node) {

        Queue<Node> queue = new LinkedList<>();
        Node marker = new Node(null);
        queue.add(node);
        queue.add(marker);
        List<Node> levelList = new ArrayList<>();
        Stack<List<Node>> stack = new Stack<>();

        while (!queue.isEmpty()) {
            node = queue.remove();

            if (node.equals(marker)) {
                stack.push(levelList);
                if (!queue.isEmpty()) {
                    queue.add(marker);
                }
                levelList = new ArrayList<>();
            } else {
                levelList.add(node);
            }

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        while (!stack.isEmpty()) {
            List<Node> list = stack.pop();
            Iterator<Node> it = list.iterator();
            while (it.hasNext()) {
                node = it.next();
                System.out.print(node.data + " ");
            }
            System.out.println();
        }
    }

    public void levelOrder() {
        levelOrder(this.root);
    }

    private void levelOrder(Node node) {
        Queue<Node> queue = new LinkedList<>();
        Node marker = new Node(null);
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

    public void inorder() {
        System.out.println("inorder traversal recursive: ");
        inorder(this.root);
        System.out.println();
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

    private void inorder(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            inorder(node.left);
        }
        System.out.print(node.data + " ");
        if (node.right != null) {
            inorder(node.right);
        }
    }

    public void preorder() {
        System.out.println("preorder traversal recursive: ");
        preorder(this.root);
        System.out.println();
        System.out.println("preorder traversal iterative: ");
        preorderIterative(this.root);
    }

    private void preorderIterative(Node node) {
        Stack<Node> stack = new Stack<Node>();

        while (node != null || !stack.isEmpty()) {
            if (node == null) {
                node = stack.pop();
            }

            if (node != null) {
                System.out.print(node.data + " ");
                if (node.right != null) {
                    stack.push(node.right);
                }
                node = node.left;
            }
        }
    }

    private void preorder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data + " ");
        if (node.left != null) {
            preorder(node.left);
        }

        if (node.right != null) {
            preorder(node.right);
        }
    }

    public void postorder() {
        System.out.println("postorder traversal recursive: ");
        postorder(this.root);
        System.out.println();
        System.out.println("postorder traversal iterative (2 stack): ");
        postorderIterative(this.root);
        System.out.println("postorder traversal iterative (1 stack): ");
        postOrderIterative1Stack(this.root);
    }

    private void postOrderIterative1Stack(Node node) {
        Stack<Node> stack = new Stack<>();

        Node curr = node;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                Node temp = stack.peek().right;
                if (temp == null) {
                    temp = stack.pop();
                    System.out.print(temp.data + " ");
                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        System.out.print(temp.data + " ");
                    }
                } else {
                    curr = temp;
                }
            }
        }
    }


    private void postorderIterative(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack1 = new Stack<Node>();
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
            node = stack2.pop();
            System.out.print(node.data + " ");
        }
        System.out.println();

    }

    private void postorder(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            postorder(node.left);
        }

        if (node.right != null) {
            postorder(node.right);
        }

        System.out.print(node.data + " ");
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
