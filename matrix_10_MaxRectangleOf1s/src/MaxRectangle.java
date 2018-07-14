import java.util.Stack;

/**
 Maximum size rectangle binary sub-matrix with all 1s
 Given a binary matrix, find the maximum size rectangle binary-sub-matrix with all 1’s.

 Input :
 0 1 1 0
 1 1 1 1
 1 1 1 1
 1 1 0 0

 Output :
 1 1 1 1
 1 1 1 1
 ===================
 INPUT / OUTPUT
 ===================
 */

public class MaxRectangle {

    public static void main(String [] args) {
        MaxRectangle mr = new MaxRectangle();

        int [][] M = {
                {0, 1, 1, 0},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
        };

        int [][] A = {
                {2, 1, 2},
                {2, 1, 5, 6, 2, 3},
                { 6, 2, 5, 4, 5, 1, 6 }
        };
        for (int i=0; i<A.length; i++) {
            Result result = new Result();
            mr.maxArea(A[i], result);
            System.out.println("result: " + result);
        }

    }

    /**
     Largest Rectangle in Histogram
     Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
     find the area of largest rectangle in the histogram.

     * @param A
     * @param result
     */

    public void maxArea(int [] A, Result result) {
        if (A == null) {
            return;
        }

        Stack<Integer> stack = new Stack<>();
        int i=0;
        while (i < A.length) {
            if (stack.isEmpty() || (A[i] >= A[stack.peek()]) )  {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && (A[i] < A[stack.peek()])) {
                    int top = stack.pop();
                    int area = 0;
                    if (stack.isEmpty()) {
                        area = A[top] * (i);
                        if (area > result.maxArea) {
                            result.maxArea = area;
                            result.col1 = 0;
                            result.col2 = i-1;
                        }
                    } else {
                        area = A[top] * (i - stack.peek() - 1);
                        if (area > result.maxArea) {
                            result.maxArea = area;
                            result.col1 = stack.peek()+1;
                            result.col2 = i-1;
                        }
                    }

                }
                stack.push(i);
            }
            ++i;
        }
        while (!stack.isEmpty() ) {
            int top = stack.pop();
            int area = 0;
            if (stack.isEmpty()) {
                area = A[top] * (i);
                if (area > result.maxArea) {
                    result.maxArea = area;
                    result.col1 = 0;
                    result.col2 = i-1;
                }
            } else {
                area = A[top] * (i - stack.peek() - 1);
                if (area > result.maxArea) {
                    result.maxArea = area;
                    result.col1 = stack.peek()+1;
                    result.col2 = i-1;
                }
            }
        }
    }
}

class Result {
    int maxArea;
    int row1;
    int row2;
    int col1;
    int col2;

    Result() {
        maxArea = 0;
        row1 = 0;
        row2 = 0;
        col1 = 0;
        col2 = 0;
    }

    @Override
    public String toString() {
        return "[ max area: " + this.maxArea + ", row1: " + row1 + ", row2: " + row2 + ", col1 "
                + col1 + ", col2: " + col2 + "]";
    }
}
