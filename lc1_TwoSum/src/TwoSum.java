/**
 * Created by manib on 10/4/17.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1 Two Sum
 Given an array of integers, find two numbers such that they add up to a specific target
 number.
 */

public class TwoSum {

    public static void main(String [] args) {
        TwoSum ts = new TwoSum();

        int [][] A = {
                {2, 7, 15, 11},
                {5, 8, 1, 4, 2, 13}
        };
        int [] target = {9, 21};
        List<Result> results = new ArrayList<Result>();

        for (int i=0; i<A.length; i++) {
            Result res = ts.twoSum(A[i], target[i]);
            results.add(res);
        }

        for (Result res: results) {
            System.out.println(res.toString());
        }
    }

    public Result twoSum(int [] A, int target) {
        if (A == null || A.length <= 1) {
            return null;
        }

        Arrays.sort(A);
        int i = 0;
        int j = A.length-1;

        while (i < j) {
            if (A[i] + A[j] == target) {
                return new Result(A[i], A[j]);
            } else if ((A[i] + A[j]) < target) {
                ++i;
            } else {
                --j;
            }
        }

        return null;
    }

}

class Result {
    int first;
    int second;

    public Result(int first, int second) {
        this.first= first;
        this.second= second;
    }

    public String toString() {
        return "[" + this.first + ", " + this.second + "]";
    }

}
