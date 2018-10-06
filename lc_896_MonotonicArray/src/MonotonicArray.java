/**
 896. Monotonic Array
 https://leetcode.com/problems/monotonic-array/description/
 An array is monotonic if it is either monotone increasing or monotone decreasing.

 An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].

 Return true if and only if the given array A is monotonic.



 Example 1:

 Input: [1,2,2,3]
 Output: true
 Example 2:

 Input: [6,5,4,4]
 Output: true
 Example 3:

 Input: [1,3,2]
 Output: false
 Example 4:

 Input: [1,2,4,5]
 Output: true
 Example 5:

 Input: [1,1,1]
 Output: true


 Note:

 1 <= A.length <= 50000
 -100000 <= A[i] <= 100000
 */
public class MonotonicArray {

    public static void main(String[] args) {

    }

    public boolean isMonotonic(int[] A) {
        if (A == null || A.length <= 1) {
            return true;
        }

        int i=1;
        while((i < A.length) && (A[i] == A[i-1])) {
            ++i;
        }
        if (i == A.length) {
            return true;
        }

        boolean flag = A[i] > A[i-1] ? true: false;

        //if true check for increasing
        if (flag) {
            while (i < A.length) {
                if (A[i] < A[i-1]){
                    return false;
                }
                ++i;
            }
        } else {
            while (i < A.length) {
                if (A[i] > A[i-1]){
                    return false;
                }
                ++i;
            }
        }

        return true;
    }
}

