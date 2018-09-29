import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 679. 24 Game
 https://leetcode.com/problems/24-game/description/

 You have 4 cards each containing a number from 1 to 9. You need to judge whether they could
 operated through *, /, +, -, (, ) to get the value of 24.

 Example 1:
 Input: [4, 1, 8, 7]
 Output: True
 Explanation: (8-4) * (7-1) = 24
 Example 2:
 Input: [1, 2, 1, 2]
 Output: False
 Note:
 The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example,
 with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 */

public class Game24 {

    public static void main(String[] args) {
        Game24 game = new Game24();
        Double d = new Double(2);
//        if (d.compareTo(2.0)  == 0) {
//            System.out.println("true");
//        }

        int [][] A = {
                {4, 1, 8, 7},
                {1, 2, 1, 2}
        };

        for (int i=0; i<A.length; i++) {
            System.out.println(game.judgePoint24(A[i]));
        }
    }

    private static double eps = 0.0001;

    public boolean judgePoint24(int[] nums) {

        List<Double> list = new ArrayList<Double>();
        for (int n: nums) {
            list.add((double)n);
        }

        // return judgePoint24(list);
        return judgePoint24Helper(list);
    }

    private boolean judgePoint24Helper(List<Double> list) {
        if (list.size() == 1) {
            //return list.get(0).compareTo(24.0) == 0;
            return Math.abs(list.get(0) - 24.0) < 0.001;
        }

        for (int i=0; i<list.size(); i++) {
            for (int j=0; j<i; j++) {
                List<Double> ops = new ArrayList<Double>();
                double x = list.get(i);
                double y = list.get(j);
                ops.addAll(Arrays.asList(x+y, x-y, y-x, x*y));
                if (Math.abs(x) > eps) { ops.add(y/x); }
                if (Math.abs(y) > eps) { ops.add(x/y); }


                list.remove(i);
                list.remove(j);
                for (double d: ops) {
                    list.add(d);
                    if (judgePoint24Helper(list)) {
                        return true;
                    }
                    list.remove(list.size()-1);
                }
                list.add(j, y);
                list.add(i, x);


            }
        }

        return false;
    }
}
