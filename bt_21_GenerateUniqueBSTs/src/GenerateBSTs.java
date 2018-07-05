import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 LeetCode – Unique Binary Search Trees II (Java)

 Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

 For example,
 Given n = 3, your program should return all 5 unique BST's shown below.
   1         3     3      2      1
   \       /     /      / \      \
   3     2     1      1   3      2
  /     /       \                 \
 2     1         2                 3
 */

public class GenerateBSTs {

    public static void main(String[] args) {

        GenerateBSTs bt = new GenerateBSTs();

        int n = 3;
        bt.generateBSTs(n);
    }

    public void generateBSTs(int n) {
        List<Node> rootNodes = new ArrayList<>();
        rootNodes = generateBSTs(1, n);

        System.out.println("preorder of generated BSTs: ");
        for (Node node: rootNodes) {
            preorder(node);
            System.out.println();
        }
    }

    private void preorder(Node node) {
        Stack<Node> stack = new Stack<>();

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

    private List<Node> generateBSTs(int low, int high) {
        List<Node> result = new ArrayList<Node>();
        if (low > high) {
            result.add(null);
            return result;
        }

        for (int i = low; i<= high; i++) {
            List<Node> left = generateBSTs(low, i-1);
            List<Node> right = generateBSTs(i+1, high);

            for (Node l: left) {
                for (Node r: right) {
                    Node node = new Node(i);
                    node.left = l;
                    node.right = r;
                    result.add(node);
                }
            }
        }

        return result;
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
