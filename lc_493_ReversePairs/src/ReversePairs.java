import java.util.Arrays;

/**
 493. Reverse Pairs
 https://leetcode.com/problems/reverse-pairs/description/
 Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

 You need to return the number of important reverse pairs in the given array.

 Example1:

 Input: [1,3,2,3,1]
 Output: 2
 Example2:

 Input: [2,4,3,5,1]
 Output: 3
 Note:
 The length of the given array will not exceed 50,000.
 All the numbers in the input array are in the range of 32-bit integer.

 */
public class ReversePairs {

    public static void main(String[] args) {

    }

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length-1);
    }
    private int mergeSort(int[] nums, int s, int e){
        if(s>=e) return 0;
        int mid = s + (e-s)/2;
        int cnt = mergeSort(nums, s, mid) + mergeSort(nums, mid+1, e);
        for(int i = s, j = mid+1; i<=mid; i++){
            while(j<=e && nums[i]/2.0 > nums[j]) j++;
            cnt += j-(mid+1);
        }
        Arrays.sort(nums, s, e+1);
        return cnt;
    }

    public int reversePairsSlow(int[] nums) {
        int res = 0;
        TreeNode root = null;

        for (int ele : nums) {
            res += search(root, 2*ele + 1);
            root = insert(root, ele);
        }

        return res;
    }

    public int search(TreeNode node, int value) {
        if (node == null) {
            return 0;
        }

        if (value == node.val){
            return node.count;
        } else if (value < node.val) {
            return node.count + search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }

    public TreeNode insert(TreeNode node, int value) {
        if (node == null) {
            node = new TreeNode(value);
        } else if (value == node.val) {
            ++node.count;
        } else if (value < node.val) {
            node.left = insert(node.left, value);
        } else {
            ++node.count;
            node.right = insert(node.right, value);
        }

        return node;

    }
}

class TreeNode {
    int val;
    int count;
    TreeNode left, right;

    TreeNode(int v) {
        val = v;
        count = 1;
        left = right = null;
    }
}