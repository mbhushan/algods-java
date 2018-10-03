import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLeaves {

    public static void main(String[] args) {

    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        collectLeaves(res, root);

        return res;

    }

    public void collectLeaves(List<List<Integer>> res, TreeNode root) {

        List<TreeNode> leaves = new ArrayList<>();

        while(true) {
            collectLeaves(root, root, leaves);

            List<Integer> tmp = new ArrayList<>();
            for (TreeNode tnode: leaves) {
                tmp.add(tnode.val);
            }
            if (tmp.size() > 0) {
                res.add(tmp);
            }


            if (leaves.size() == 0) {
                break;
            }

            if (leaves.get(0).equals(root)) {
                break;
            }
            leaves = new ArrayList<>();
        }


    }

    private void collectLeaves(TreeNode node, TreeNode prev, List<TreeNode> leaves) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            leaves.add(node);
            if (prev.left != null && prev.left.equals(node)) {
                prev.left = null;
            } else if (prev.right != null && prev.right.equals(node)) {
                prev.right = null;
            }
            return;
        }

        prev = node;

        if (node.left != null) {
            collectLeaves(node.left, prev, leaves);
        }

        if (node.right != null) {
            collectLeaves(node.right, prev, leaves);
        }
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }