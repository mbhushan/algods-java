import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 239. Sliding Window Maximum
 https://leetcode.com/problems/sliding-window-maximum/description/

 Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

 Example:

 Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 Output: [3,3,5,5,6,7]
 Explanation:

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 Note:
 You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

 Follow up:
 Could you solve it in linear time?

 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {

        //int [] A = {1,3,-1,-3,5,3,6,7};
        int [] A = {1,3,1,2,0,5};
        int k = 3;

        SlidingWindowMaximum sw = new SlidingWindowMaximum();

        sw.maxSlidingWindow(A, k);

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();

        int index = 0;
        //{1,3,-1,-3,5,3,6,7};
        List<Integer> list = new ArrayList<>();
        while (index < nums.length) {
            if (dq.isEmpty()) {
                dq.add(index);
            } else {
                while (!dq.isEmpty() && nums[dq.peekLast()] < nums[index]) {
                    dq.removeLast();
                }
                while (!dq.isEmpty() && index - dq.peekFirst() >= k) {
                    dq.removeFirst();
                }
                dq.add(index);

            }
            if (index+1 >= k) {
                System.out.println("max at index [" + index + "]: " + nums[dq.peekFirst()]);
                list.add(nums[dq.peekFirst()]);
            }
            ++index;
        }

        return list.stream().mapToInt(i->i).toArray();
    }
}
