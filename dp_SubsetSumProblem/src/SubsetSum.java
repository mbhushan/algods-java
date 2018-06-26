import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**

 Given an array of non negative numbers and a total, is there subset of numbers in this
 array which adds up to given total. Another variation is given an array is it possible
 to split it up into 2 equal sum partitions. Partition need not be equal sized. Just
 equal sum.

 =================
 INPUT / OUTPUT
 =================
 DP Array:
 T F F F F F F F F F F F
 T F T F F F F F F F F F
 T F T T F T F F F F F F
 T F T T F T F T F T T F
 T F T T F T F T T T T T
 T F T T F T F T T T T T
 result indices: [3, 1]
 Elements from array are:
 3 8

 */

public class SubsetSum {

    public static void main(String [] args) {
        SubsetSum ss = new SubsetSum();
        int [] A = {2, 3, 7, 8, 10};
        int total = 11;
        ss.subsetSum(A, total);
        System.out.println();
//        System.out.println("alternate implementation:");
//        ss.subsetSum2(A, total);
    }

    public boolean subsetSum(int [] A, int total) {
        int len = A.length;

        boolean [][] DP = new boolean[len+1][total+1];

        for (int col=1; col <= total; col++) {
            DP[0][col] = false;
        }
        for (int row=0; row <= len; row++) {
            DP[row][0] = true;
        }

        for (int i=1; i <=len; i++) {
            for (int j=1; j<=total; j++) {
                if (A[i-1] <= j) {
                    DP[i][j] = DP[i-1][j] || DP[i-1][j - A[i-1]];
                } else {
                    DP[i][j] = DP[i-1][j];
                }
            }
        }
        System.out.println("DP Array: ");
        printDPMatrix(DP);
        findElements(DP, A);
        return DP[len][total] ;

    }

    public void findElements(boolean [][] M, int [] A) {
        int row = M.length-1;
        int col = M[0].length-1;

        List<Integer> result = new ArrayList<>();

        while (row > 0 && col > 0) {
            if (M[row][col] == M[row-1][col]) {
                --row;
            } else {
                result.add(row-1);
                col = col - A[row-1];
                --row;
            }
        }
        System.out.println("result indices: " + result.toString());
        ListIterator<Integer> iter = result.listIterator(result.size());
        System.out.println("Elements from array are: ");
        while (iter.hasPrevious()) {
            int index = iter.previous();
            System.out.print(A[index] + " ");
        }
        System.out.println();
    }

    public boolean subsetSum2(int [] A, int total) {
        int len = A.length;
        boolean [][] T = new boolean[len+1][total+1];
        for (int i=0; i<=len; i++) {
            T[i][0] = true;
        }

        for (int i=1; i<=len; i++) {
            for (int j=1; j<=total; j++) {
                if (j-A[i-1] >= 0) {
                    T[i][j] = T[i-1][j] || T[i-1][j - A[i-1]];
                } else {
                    T[i][j] = T[i-1][j];
                }
            }
        }
        printDPMatrix(T);
        return T[len][total];
    }

    private void printDPMatrix(boolean [][] M) {
        int row = M.length;
        int col = M[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (M[i][j]) {
                    System.out.print("T" + " ");
                } else {
                    System.out.print("F" + " ");
                }
            }
            System.out.println();
        }
    }
}
