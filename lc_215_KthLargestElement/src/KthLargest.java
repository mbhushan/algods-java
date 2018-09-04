import java.util.Arrays;
import java.util.PriorityQueue;

/**
 215. Kth Largest Element in an Array
 https://leetcode.com/problems/kth-largest-element-in-an-array/description/

 Find the kth largest element in an unsorted array. Note that it is the kth largest
 element in the sorted order, not the kth distinct element.

 Example 1:
 Input: [3,2,1,5,6,4] and k = 2

 Output: 5


 Example 2:
 Input: [3,2,3,1,2,4,5,5,6] and k = 4
 Output: 4
 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.

 */

public class KthLargest {

    public static void main(String[] args) {
        KthLargest kl = new KthLargest();

        int [][] A = {
                {3,2,1,5,6,4},
                {3,2,3,1,2,4,5,5,6}
        };

        int [] k = {2, 4};

        for (int i=0; i<A.length; i++) {
            System.out.println("input: " + Arrays.toString(A[i]));
            int ans = kl.findKthLargest(A[i], k[i]);
            System.out.println("ans: " + ans);
        }

    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return Integer.MIN_VALUE;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(k);

        for (int i=0; i<nums.length; i++) {
            if (pq.size() < k) {
                pq.add(nums[i]);
            } else {
                if (nums[i] > pq.peek()) {
                    pq.remove();
                    pq.add(nums[i]);

                }
            }
        }

        System.out.println("PQ: " + pq);

        return pq.peek();

    }
}
