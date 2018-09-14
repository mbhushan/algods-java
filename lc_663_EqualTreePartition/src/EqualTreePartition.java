import java.util.Stack;

/**
 Given a binary tree with n nodes, your task is to check if it's possible to partition the tree
 to two trees which have the equal sum of values after removing exactly one edge on the original tree.

 Example 1:
 Input:
 5
 / \
 10 10
 /  \
 2   3

 Output: True
 Explanation:
 5
 /
 10

 Sum: 15

 10
 /  \
 2    3

 Sum: 15
 Example 2:
 Input:
 1
 / \
 2  10
 /  \
 2   20

 Output: False
 Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
 Note:
 The range of tree node value is in the range of [-100000, 100000].
 1 <= n <= 10000
 */

public class EqualTreePartition {

    private TreeNode root;
    Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        EqualTreePartition bt = new EqualTreePartition();

        bt.init();
        bt.equalTree();

    }

    public void equalTree() {
        boolean flag = checkEqualTree(this.root);
        System.out.println("equal partition possible: " + flag);
    }

    public boolean checkEqualTree(TreeNode root) {

        boolean flag = false;



        int total = treeSum(this.root);

        System.out.println("stack: " + stack);
        System.out.println("total: " + total);

        if (total%2 == 1) {
            return false;
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == total/2) {
                return true;
            }
            stack.pop();
        }
        return false;




    }

    private int treeSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sum  = treeSum(node.left) + treeSum(node.right) + node.val;
        stack.push(sum);
        return stack.peek();
    }



    public void init() {

        this.root = new TreeNode(5);
        this.root.left = new TreeNode(10);

        this.root.right = new TreeNode(10);
        this.root.right.left = new TreeNode(2);
        this.root.right.right = new TreeNode(3);

    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
