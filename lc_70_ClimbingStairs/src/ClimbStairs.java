/**
 70. Climbing Stairs
 https://leetcode.com/problems/climbing-stairs/description/

 You are climbing a stair case. It takes n steps to reach to the top.

 Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 Note: Given n will be a positive integer.

 Example 1:

 Input: 2
 Output: 2
 Explanation: There are two ways to climb to the top.
 1. 1 step + 1 step
 2. 2 steps
 Example 2:

 Input: 3
 Output: 3
 Explanation: There are three ways to climb to the top.
 1. 1 step + 1 step + 1 step
 2. 1 step + 2 steps
 3. 2 steps + 1 step

 */
public class ClimbStairs {

    public static void main(String[] args) {

        ClimbStairs cs = new ClimbStairs();

        System.out.println("ans: " + cs.climbStairs(10));
    }

    public int climbStairs(int n) {
        int a = 0;
        int b = 1;

        for (int i=1; i<=n; i++) {
            int tmp = b;
            b = b + a;
            a = tmp;
        }

        return b;
    }
}
