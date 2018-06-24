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

 */

public class LongestCommonSubsequence {

    public static void main(String [] args) {
        LongestCommonSubsequence obj = new LongestCommonSubsequence();
        String [] S1 = {"abcdaf"};
        String [] S2 = {"acbcf"};

        for (int i=0; i<S1.length; i++) {
            obj.generateLCS(S1[i], S2[i]);
        }

    }

    public void generateLCS(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return;
        }

        char [] X = s1.toCharArray();
        char [] Y = s2.toCharArray();

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
