import java.util.Stack;

/**
 84. Largest Rectangle in Histogram
 https://leetcode.com/problems/largest-rectangle-in-histogram/description/

 Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 find the area of largest rectangle in the histogram.

 Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 The largest rectangle is shown in the shaded area, which has area = 10 unit.
 Example:
 Input: [2,1,5,6,2,3]
 Output: 10
 */
public class LargestRectangleHistogram {

    public static void main(String[] args) {
        int [] A = {2,1,5,6,2,3};

        LargestRectangleHistogram lr = new LargestRectangleHistogram();
        System.out.println(lr.largestRectangleArea(A));
    }

    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int index = 0;

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        //2,1,5,6,2,3
        while (index < len) {
            while (!stack.isEmpty() && (heights[index] <= heights[stack.peek()])) {
                int top = stack.pop();
                int width = stack.isEmpty() ? index: index - stack.peek() - 1;
                int area = width * heights[top];
                maxArea = Math.max(area, maxArea);
            }
            stack.push(index);
            ++index;
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            int width = stack.isEmpty() ? index: index - stack.peek()-1;
            int area = width * heights[top];
            maxArea = Math.max(area, maxArea);

        }

        return maxArea;
    }
}
