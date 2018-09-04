import java.util.ArrayList;
import java.util.List;

/**
 257. Binary Tree Paths

 https://leetcode.com/problems/binary-tree-paths/description/

 Given a binary tree, return all root-to-leaf paths.

 Note: A leaf is a node with no children.

 Example:

 Input:

 1
 /   \
 2     3
 \
 5

 Output: ["1->2->5", "1->3"]

 Explanation: All root-to-leaf paths are: 1->2->5, 1->3

 ===============
 INPUT / OUTPUT
 ===============
 Binary Tree Paths:
 [1->2->5, 1->3]

 */
public class BinaryTreePaths {

    private TreeNode root;

    public static void main(String[] args) {
        BinaryTreePaths bt = new BinaryTreePaths();

        bt.buildTree();

        bt.binaryTreePaths();

    }



    public void binaryTreePaths() {

        binaryTreePaths(this.root);

    }

    public List<String> binaryTreePaths(TreeNode root) {

        List<String> path = new ArrayList<>();
        List<String> result = new ArrayList<>();

        binaryTreePaths(root, path, result);

        System.out.println("Binary Tree Paths: ");
        System.out.println(result);

        return result;

    }

    private void binaryTreePaths(TreeNode node, List<String> paths, List<String> result) {
        if (node == null) {
            return;
        }

        paths.add(String.valueOf(node.val));

        binaryTreePaths(node.left, paths, result);
        binaryTreePaths(node.right, paths, result);

        if (node.left == null && node.right == null) {
            //System.out.println(paths);
            String ans = String.join("->", paths);
            result.add(ans);

        }
        paths.remove(paths.size()-1);


    }

    public void buildTree() {
        this.root = new TreeNode(1);

        this.root.left = new TreeNode(2);
        this.root.right = new TreeNode(3);

        this.root.left.right = new TreeNode(5);
    }

    public void buildTree1() {
        this.root = new TreeNode(10);

        this.root.left = new TreeNode(5);
        this.root.right = new TreeNode(20);

        this.root.left.left = new TreeNode(2);
        this.root.left.right = new TreeNode(8);

        this.root.right.left = new TreeNode(15);
        this.root.right.right = new TreeNode(25);

        this.root.right.right.right = new TreeNode(30);
        this.root.right.right.left = new TreeNode(22);

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
        this.left = null;
        this.right = null;
    }
}
