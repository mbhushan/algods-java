import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 272. Closest Binary Search Tree Value II
 https://leetcode.com/problems/closest-binary-search-tree-value-ii/description/

 Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

 Note:

 Given target value is a floating point.
 You may assume k is always valid, that is: k ≤ total nodes.
 You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 Example:

 Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

 4
 / \
 2   5
 / \
 1   3

 Output: [4,3]
 Follow up:
 Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 */
public class ClosestBST {

    public static void main(String[] args) {

    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new LinkedList<Integer>();
        // populate the predecessor and successor stacks
        Stack<TreeNode> pred = new Stack<TreeNode>();
        Stack<TreeNode> succ = new Stack<TreeNode>();
        TreeNode curr = root;
        while (curr != null) {
            if (target <= curr.val) {
                succ.push(curr);
                curr = curr.left;
            } else {
                pred.push(curr);
                curr = curr.right;
            }
        }
        while (k > 0) {
            if (pred.empty() && succ.empty()) {
                break;
            } else if (pred.empty()) {
                result.add( getSuccessor(succ) );
            } else if (succ.empty()) {
                result.add( getPredecessor(pred) );
            } else if (Math.abs(target - pred.peek().val) < Math.abs(target - succ.peek().val)) {
                result.add( getPredecessor(pred) );
            } else {
                result.add( getSuccessor(succ) );
            }
            k--;
        }
        return result;
    }

    private int getPredecessor(Stack<TreeNode> st) {
        TreeNode popped = st.pop();
        TreeNode p = popped.left;
        while (p != null) {
            st.push(p);
            p = p.right;
        }
        return popped.val;
    }

    private int getSuccessor(Stack<TreeNode> st) {
        TreeNode popped = st.pop();
        TreeNode p = popped.right;
        while (p != null) {
            st.push(p);
            p = p.left;
        }
        return popped.val;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
