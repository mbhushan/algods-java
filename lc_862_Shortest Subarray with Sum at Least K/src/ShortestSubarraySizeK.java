import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 862. Shortest Subarray with Sum at Least K
 https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/

 Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

 If there is no non-empty subarray with sum at least K, return -1.



 Example 1:

 Input: A = [1], K = 1
 Output: 1
 Example 2:

 Input: A = [1,2], K = 4
 Output: -1
 Example 3:

 Input: A = [2,-1,2], K = 3
 Output: 3
 */
public class ShortestSubarraySizeK {

    public static void main(String[] args) {
        ShortestSubarraySizeK ss = new ShortestSubarraySizeK();

       // int [] A = {17,85,93,-45,-21};
       // int k = 150;

        int [] A = {84,-37,32,40,95};
        int k = 167;

        System.out.println("ans: " + ss.shortestSubarray(A, k));
    }

    public int shortestSubarray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] prefix = new int[nums.length + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prefix.length; i++) {
            while (!deque.isEmpty() && prefix[i] - prefix[deque.peekFirst()] >= k) {
                min = Math.min(min, i - deque.pollFirst());
            }
            while (!deque.isEmpty() && prefix[i] < prefix[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offer(i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public int shortestSubarrayOld(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();

        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            dq.add(nums[i]);
            if (sum >= k) {
                min = Math.min(min, dq.size());
            }
            while (!dq.isEmpty() && (dq.peekFirst() < 0 || sum >= k)) {
                int x = dq.removeFirst();
                sum = sum - x;
                if (sum >= k) {
                    min = Math.min(min, dq.size());
                }

            }
        }

        while (sum >= k && !dq.isEmpty()) {
            int x = dq.removeFirst();
            sum = sum - x;
            if (sum >= k) {
                min = Math.min(min, dq.size());
            }

        }

        return min == Integer.MAX_VALUE ? -1: min;
    }
}
