import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 923. 3Sum With Multiplicity
 Given an integer array A, and an integer target, return the number of tuples i, j, k
 such that i < j < k and A[i] + A[j] + A[k] == target.

 As the answer can be very large, return it modulo 10^9 + 7.



 Example 1:

 Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
 Output: 20
 Explanation:
 Enumerating by the values (A[i], A[j], A[k]):
 (1, 2, 5) occurs 8 times;
 (1, 3, 4) occurs 8 times;
 (2, 2, 4) occurs 2 times;
 (2, 3, 3) occurs 2 times.
 */
public class ThreeSumMultiplicity {

    public static void main(String[] args) {
        ThreeSumMultiplicity ts = new ThreeSumMultiplicity();
        int [] A = {1,1,2,2,3,3,4,4,5,5};
        int target = 8;
        ts.threeSumMultiRec(A, 8);


    }
    
    public int threeSumMulti(int[] A, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        int res = 0;
        int mod = 1000000007;
        for (int i = 0; i < A.length; i++) {
            res = (res + map.getOrDefault(target - A[i], 0)) % mod;

            for (int j = 0; j < i; j++) {
                int temp = A[i] + A[j];
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }
        return res;
    }

    int count = 0;
    public int threeSumMultiRec(int[] A, int target) {
        threeSum(A, target, 0, 0, new ArrayList<Integer>());

        return count % 1000000007;
    }

    private void threeSum(int [] A, int target, int index, int sum, List<Integer> res) {
        if (res.size() == 3 && sum == target) {
            System.out.println(res);
            ++count;

        }
        if (index >= A.length) {
            return;
        }

        for (int i=index; i<A.length; i++) {
            res.add(A[i]);
            threeSum(A, target, i+1, sum+A[i], res);
            res.remove(res.size()-1);
        }
    }
}
