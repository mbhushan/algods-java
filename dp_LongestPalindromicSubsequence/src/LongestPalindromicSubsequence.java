/**
 Given a sequence, find the length of the longest palindromic subsequence in it.


 For example, if the given sequence is "BBABCBCAB",
 then the output should be 7 as
 "BABCBAB" is the longest palindromic subsequence in it.

 "BBBBB" and "BBCBB" are
 also palindromic subsequences of the given sequence, but not the longest ones.

 input string: AGBDBA
 1 1 1 1 3 5
 0 1 1 1 3 3
 0 0 1 1 3 3
 0 0 0 1 1 1
 0 0 0 0 1 1
 0 0 0 0 0 1
 longest palindromic subsequence: 5
 A A
 B B
 D
 input string: BBABCBCAB
 1 2 2 3 3 5 5 5 7
 0 1 1 3 3 3 3 5 7
 0 0 1 1 1 3 3 5 5
 0 0 0 1 1 3 3 3 5
 0 0 0 0 1 1 3 3 3
 0 0 0 0 0 1 1 1 3
 0 0 0 0 0 0 1 1 1
 0 0 0 0 0 0 0 1 1
 0 0 0 0 0 0 0 0 1
 longest palindromic subsequence: 7
 B B
 A A
 C C
 B
 */

public class LongestPalindromicSubsequence {

    public static void main(String [] args) {
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        String [] str = {"AGBDBA", "BBABCBCAB"};

        for (int i=0; i<str.length; i++) {
            System.out.println("input string: " + str[i]);
            lps.findSequenceLength(str[i]);
        }

    }

    public void findSequenceLength(String str) {
        if (str == null) {
            return;
        }

        char [] S = str.toCharArray();
        int size = S.length;

        int [][] T = new int[size][size];
        for (int i=0; i<size; i++) {
            T[i][i] = 1;
        }

        for (int len = 2; len <= size; len++) {
            for (int i=0; i<=size-len; i++) {
                int j = i + len - 1;

                if (S[i] == S[j]) {
                    T[i][j] = Math.max(T[i+1][j-1]+2, T[i][j]);
                } else {
                    T[i][j] = Math.max(Math.max(T[i][j-1], T[i+1][j]), T[i][j]);
                }
            }
        }

        //print DP matrix
        printDPMatrix(T);
        System.out.println("longest palindromic subsequence: " + T[0][size-1]);
        printPalindromeSubsequence(T, S);
    }

    public void printPalindromeSubsequence(int [][] T, char [] S) {
        int row = 0;
        int col = S.length-1;
        int len = S.length;

        while (row < len && col >= 0 && (row != col)) {
            if (T[row][col] == T[row+1][col]) {
                ++row;
            } else if (T[row][col] == T[row+1][col-1]+2) {
                System.out.println(S[row] + " " + S[col]);
                ++row;
                --col;
            }
            if (row == col) {
                System.out.println(S[row]);
            }
        }
    }

    public void printDPMatrix(int [][] T) {
        int row = T.length;
        int col = T[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                System.out.print(T[i][j] + " ");
            }
            System.out.println();
        }
    }
}
