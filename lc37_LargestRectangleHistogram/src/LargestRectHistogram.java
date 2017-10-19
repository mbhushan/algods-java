import java.util.Stack;

/**
 * Created by manib on 10/19/17.
 *
 * 37 Largest Rectangle in Histogram
 Given n non-negative integers representing the histogram’s bar height where the width
 of each bar is 1, find the area of largest rectangle in the histogram.
 Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 For example, given height = [2,1,5,6,2,3], return 10.
 */
public class LargestRectHistogram {

    public static void main(String [] main) {
        int [] A = {2,1,5,6,2,3};

        LargestRectHistogram lrh = new LargestRectHistogram();
        System.out.println("Max Area: " + lrh.calcArea(A));
    }

    public int calcArea(int [] heights) {
        if (heights == null || heights.length < 1) {
            return 0;
        }

        int index = 0;
        int totalHeights = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();

        while (index < totalHeights) {
            if (stack.isEmpty() || (heights[stack.peek()] <= heights[index])) {
                stack.push(index++);
            } else {
                int top = stack.pop();
                int width = stack.isEmpty() ? index: index - stack.peek() - 1;
                int area = heights[top] * width;
                maxArea = Math.max(maxArea, area);
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            int width = stack.isEmpty() ? index: index - stack.peek() - 1;
            int area = heights[top] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
