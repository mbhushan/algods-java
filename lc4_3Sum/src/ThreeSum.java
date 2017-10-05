import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by manib on 10/5/17.
 *
 * Problem:
 Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 Find all unique triplets in the array which gives the sum of zero.
 Note: Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 The solution set must not contain duplicate triplets.
 For example, given array S = {-1 0 1 2 -1 -4},
 A solution set is:
 (-1, 0, 1)
 (-1, -1, 2)
 -4 -1 -1 0 1 2
 */
public class ThreeSum {

    public static void main(String [] args) {
        ThreeSum ts = new ThreeSum();

        int [] A = {-1, 0, 1, 2, -1, -4};

        List<Triplet> result = ts.findTriplets(A, 0);

        for (Triplet t : result) {
            System.out.println(t.toString());
        }
    }

    public List<Triplet> findTriplets(int [] A, int target) {
        List<Triplet> result = new ArrayList<Triplet>();

        if (A == null || A.length < 3) {
            return result;
        }
        Arrays.sort(A);

        for (int i=0; i<A.length-1; i++) {
            if(i!=0 && (A[i] == A[i-1])) {
                continue;
            }
            int j = i+1;
            int k = A.length - 1;
            while (j < k) {
                if (A[i] + A[j] + A[k] == 0) {
                    result.add(new Triplet(A[i], A[j], A[k]));
                    ++j;
                    --k;
                } else if (A[i] + A[j] + A[k] < 0) {
                    ++j;
                } else {
                    --k;
                }
            }
        }

        return result;
    }
}

class Triplet {
    int x;
    int y;
    int z;

    public Triplet(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString() {
        return "[" + this.x + ", " + this.y + ", " + this.z + "]";
    }
}
