import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by manib on 10/17/17.
 *
 * 5 4Sum
 Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c
 + d = target? Find all unique quadruplets in the array which gives the sum of target.
 Note: Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤
 b ≤ c ≤ d) The solution set must not contain duplicate quadruplets.
 For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 A solution set is:
 (-1, 0, 0, 1)
 (-2, -1, 1, 2)
 (-2, 0, 0, 2)
 */
public class FourSum {

    public static void main(String [] args) {
        int [] A = {-1, 0, 1, 2, -2, 0};

        FourSum fs = new FourSum();
        fs.evaluate(A);

    }

    public void evaluate(int [] A) {
        List<Result> ans = fourSumCalc(A);
        if (ans == null) {
            System.out.println("Result is Null!");
        }

        for (Result r: ans) {
            System.out.println(r);
        }
    }

    public List<Result> fourSumCalc(int [] A) {
        List<Result> ans = new ArrayList<Result>();

        if (A == null || A.length < 4) {
            return null;
        }

        Arrays.sort(A);

        for (int i=0; i<A.length-3; i++) {
            if (i != 0 && A[i] == A[i-1]) {
                continue;
            }

            for (int j=i+1; j<A.length-2; j++) {
                if (j != i+1 && A[j] == A[j-1]) {
                    continue;
                }
                int k = j+1;
                int l = A.length-1;
                while (k < l) {
                    if (A[i] + A[j] + A[k] + A[l] == 0) {
                        ans.add(new Result(A[i], A[j], A[k], A[l]));
                        ++k;
                        --l;

                        while (k < l && A[k] == A[k-1]) {
                            ++k;
                        }

                        while (k < l && A[l] == A[l+1]) {
                            --l;
                        }

                    } else if  (A[i] + A[j] + A[k] + A[l] < 0) {
                        ++k;
                    } else if  (A[i] + A[j] + A[k] + A[l] > 0) {
                        --l;
                    }
                }
            }
        }
        return ans;
    }
}

class Result {
    int x;
    int y;
    int z;
    int u;

    Result(int x, int y, int z, int u) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.u = u;
    }

    public String toString() {
        return "[" + this.x + ", " + this.y + ", " + this.z + ", " + this.u + "]";
    }
}
