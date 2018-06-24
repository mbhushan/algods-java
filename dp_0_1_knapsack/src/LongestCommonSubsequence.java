import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them.
 A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.

 For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.
 So a string of length n has 2^n different possible subsequences.

 Examples:
 LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
 LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.

 ========================
 INPUT / OUTPUT
 ========================
 S1: abcdaf
 S2: acbcf
 0 0 0 0 0 0
 0 1 1 1 1 1
 0 1 1 2 2 2
 0 1 2 2 3 3
 0 1 2 2 3 3
 0 1 2 2 3 3
 0 1 2 2 3 4
 max subsequence length: 4
 indices: [5, 2, 1, 0]
 longest subsequence: a b c f

 S1: AGGTAB
 S2: GXTXAYB
 0 0 0 0 0 0 0 0
 0 0 0 0 0 1 1 1
 0 1 1 1 1 1 1 1
 0 1 1 1 1 1 1 1
 0 1 1 2 2 2 2 2
 0 1 1 2 2 3 3 3
 0 1 1 2 2 3 3 4
 max subsequence length: 4
 indices: [5, 4, 3, 1]
 longest subsequence: G T A B

 */

public class LongestCommonSubsequence {

    public static void main(String [] args) {
        LongestCommonSubsequence obj = new LongestCommonSubsequence();
        String [] S1 = {"abcdaf", "AGGTAB"};
        String [] S2 = {"acbcf", "GXTXAYB"};

        for (int i=0; i<S1.length; i++) {
            obj.generateLCS(S1[i], S2[i]);
            System.out.println();
        }

    }

    public void generateLCS(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return;
        }

        char [] X = s1.toCharArray();
        char [] Y = s2.toCharArray();

        System.out.println("S1: " + s1);
        System.out.println("S2: " + s2);

        int [][] DP = new int[X.length+1][Y.length+1];

        for (int i=0; i < DP.length; i++) {
            for (int j=0; j < DP[0].length; j++) {
                if (i == 0 || j == 0) {
                    DP[i][j] = 0;
                    continue;
                }

                if (X[i-1] == Y[j-1]) {
                    DP[i][j] = DP[i-1][j-1] + 1;
                } else {
                    DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1]);
                }
            }
        }
        printMatrix(DP);
        System.out.println("max subsequence length: " + DP[X.length][Y.length]);
        findLongestSubsequence(DP, X);
    }

   public void findLongestSubsequence(int [][] M, char [] X) {
        int row = M.length-1;
        int col = M[0].length-1;
        List<Integer> indices = new ArrayList<>();

        while (row >= 1 && col >= 1) {
            if (M[row][col] == M[row][col-1]) {
                col = col-1;
            } else if (M[row][col] == M[row-1][col]) {
                row = row-1;
            } else {
                indices.add(row-1);
                --row;
                --col;
            }
        }
        System.out.println("indices: " + indices.toString());
       System.out.print("longest subsequence: ");
       ListIterator<Integer> li = indices.listIterator(indices.size());
       while (li.hasPrevious()) {
           int index = li.previous();
           //System.out.println("index: " + index);
           System.out.print(X[index] + " ");
       }
       System.out.println();
   }

    private void printMatrix(int [][] M) {
        if (M == null) {
            return;
        }
        int row = M.length;
        int col = M[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }
}
