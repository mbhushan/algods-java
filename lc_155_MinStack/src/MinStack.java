import java.util.Stack;

/**
 155. Min Stack
 https://leetcode.com/problems/min-stack/description/

 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.
 Example:
 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> Returns -3.
 minStack.pop();
 minStack.top();      --> Returns 0.
 minStack.getMin();   --> Returns -2.

 */
public  class MinStack {

    /** initialize your data structure here. */
    Stack<Integer> stack, min;

    public MinStack() {
        stack = new Stack<>();
        min = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (min.empty()) min.push(x);
        else if (min.peek() >= x) min.push(x);
    }

    public void pop() {
        int popped = stack.pop();
        if (!min.empty() && min.peek() == popped) {
            min.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
