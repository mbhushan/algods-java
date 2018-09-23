import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 652. Find Duplicate Subtrees
 https://leetcode.com/problems/find-duplicate-subtrees/solution/

 Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees,
 you only need to return the root node of any one of them.

 Two trees are duplicate if they have the same structure with same node values.

 Example 1:

 1
 / \
 2   3
 /   / \
 4   2   4
 /
 4
 The following are two duplicate subtrees:

 2
 /
 4
 and

 4
 Therefore, you need to return above trees' root in the form of a list.

 */
public class DuplicateSubtrees {

    public static void main(String[] args) {

    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<String, Integer>();

        if (root == null) {
            return result;
        }

        countDups(root, result, map);

        return result;

    }

    private String countDups(TreeNode node, List<TreeNode> result,  Map<String, Integer> map) {
        if (node == null) {
            return "#";
        }

        String serial = node.val + countDups(node.left, result, map) + countDups(node.right, result, map);

        map.put(serial, map.getOrDefault(serial, 0)+1);

        if (map.get(serial) == 2) {
            result.add(node);
        }

        return serial;
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
