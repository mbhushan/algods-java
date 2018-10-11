import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightView {

    public static void main(String[] args) {

    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList();
        TreeNode marker = new TreeNode(Integer.MIN_VALUE);
        TreeNode node = root;
        queue.add(node);
        queue.add(marker);

        while (!queue.isEmpty()) {
            node = queue.remove();
            if (!queue.isEmpty() && queue.peek().equals(marker)) {
                result.add(node.val);
            }
            if (node.equals(marker)) {
                if (!queue.isEmpty()) {
                    queue.add(marker);
                }
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return result;

    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
