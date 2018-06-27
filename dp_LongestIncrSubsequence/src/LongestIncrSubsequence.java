import java.util.Arrays;

/**
 * Created by manib on 6/27/18.

 Find the longest increasing subsequence in an array.

 Input:
 int [] A = {3, 4, -1, 0, 6, 2, 3}

 Output:
 LIS: 4 (-1, 0, 2, 3)

 ========================
 INPUT / OUTPUT
 ========================
 Array: [1, 2, 1, 2, 3, 3, 4]
 Parent: [-1, 0, -1, 2, 1, 3, 5]
 longest increasing subsequence: 4
 sequence:
 -1 0 2 3

 */

public class LongestIncrSubsequence {

    public static void main(String [] args) {
        LongestIncrSubsequence lis = new LongestIncrSubsequence();
        int [] A = {3, 4, -1, 0, 6, 2, 3};
        lis.findLIS(A);

    }

    public void findLIS(int [] A) {
        if (A == null || A.length < 2) {
            return;
        }
        int len = A.length;
        int [] T = new int[len];
        int [] P = new int[len];

        for (int i=0; i<len; i++) {
            T[i] = 1;
            P[i] = -1;
        }
        int maxSoFar = 1;
        int index = 0;
        for (int i=0; i<len-1; i++) {
            for (int j=i+1; j<len; j++) {
                if (A[i] < A[j]) {
                    if (T[i]+1 > T[j]) {
                        T[j] = T[i]+1;
                        if (T[j] > maxSoFar) {
                            maxSoFar = T[j];
                            index = j;
                        }
                        P[j] = i;
                    }
                }
            }
        }

        System.out.println("Array: " + Arrays.toString(T));
        System.out.println("Parent: " + Arrays.toString(P));

        for (int i=0; i<len; i++) {

        }
        System.out.println("longest increasing subsequence: " + T[index]);
        System.out.println("sequence: ");
        printSequence(P, A, index);

    }

    public void printSequence(int [] P, int []A, int index ) {
        if (P[index] == -1) {
            System.out.print(A[index] + " ");
            return;
        }
        printSequence(P, A, P[index]);
        System.out.print(A[index] + " ");

    }
}
