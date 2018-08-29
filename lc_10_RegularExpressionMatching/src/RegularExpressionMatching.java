/**
 10. Regular Expression Matching
 https://leetcode.com/problems/regular-expression-matching/description/


 Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.
 The matching should cover the entire input string (not partial).

 Note:

 s could be empty and contains only lowercase letters a-z.
 p could be empty and contains only lowercase letters a-z, and characters like . or *.
 Example 1:

 Input:
 s = "aa"
 p = "a"
 Output: false
 Explanation: "a" does not match the entire string "aa".
 Example 2:

 Input:
 s = "aa"
 p = "a*"
 Output: true
 Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 Example 3:

 Input:
 s = "ab"
 p = ".*"
 Output: true
 Explanation: ".*" means "zero or more (*) of any character (.)".
 Example 4:

 Input:
 s = "aab"
 p = "c*a*b"
 Output: true
 Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 Example 5:

 Input:
 s = "mississippi"
 p = "mis*is*p*."
 Output: false

 ======================================
            INPUT / OUTPUT
 ======================================
 string: aa
 pattern: a
 DP Matrix:
 1 0
 0 1
 0 0
 match: false

 string: aab
 pattern: c*a*b
 DP Matrix:
 1 0 1 0 1 0
 0 0 0 1 1 0
 0 0 0 0 1 0
 0 0 0 0 0 1
 match: true

 string: aa
 pattern: a*
 DP Matrix:
 1 0 1
 0 1 1
 0 0 1
 match: true

 string: ab
 pattern: .*
 DP Matrix:
 1 0 1
 0 1 1
 0 0 1
 match: true

 string: mississippi
 pattern: mis*is*p*.
 DP Matrix:
 1 0 0 0 0 0 0 0 0 0 0
 0 1 0 0 0 0 0 0 0 0 0
 0 0 1 0 1 0 0 0 0 0 0
 0 0 0 1 1 0 0 0 0 0 0
 0 0 0 0 1 0 0 0 0 0 0
 0 0 0 0 0 1 0 1 0 1 0
 0 0 0 0 0 0 1 1 0 1 1
 0 0 0 0 0 0 0 1 0 1 1
 0 0 0 0 0 0 0 0 0 0 1
 0 0 0 0 0 0 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0 0
 0 0 0 0 0 0 0 0 0 0 0
 match: false

 string: a
 pattern: .*..a*
 DP Matrix:
 1 0 1 0 0 0 0
 0 1 1 1 0 0 0
 match: false

 =======================================
 input / output with recursive solution
 ========================================
 string: aa
 pattern: a
 DP Match: false
 Rec Match: false

 string: aab
 pattern: c*a*b
 DP Match: true
 Rec Match: true

 string: aa
 pattern: a*
 DP Match: true
 Rec Match: true

 string: ab
 pattern: .*
 DP Match: true
 Rec Match: true

 string: mississippi
 pattern: mis*is*p*.
 DP Match: false
 Rec Match: false

 string: a
 pattern: .*..a*
 DP Match: false
 Rec Match: false

 */
public class RegularExpressionMatching {

    public static void main(String [] args) {
        RegularExpressionMatching rem = new RegularExpressionMatching();

        String [] S = {
                "aa",
                "aab",
                "aa",
                "ab",
                "mississippi",
                "a"
        };

        String [] P = {
                "a",
                "c*a*b",
                "a*",
                ".*",
                "mis*is*p*.",
                ".*..a*"
        };

        for (int i=0; i<S.length; i++) {
            System.out.println("string: " + S[i]);
            System.out.println("pattern: " + P[i]);
            boolean ans = rem.isMatch(S[i], P[i]);
            System.out.println("DP Match: " + ans);
            System.out.println("Rec Match: " + rem.isMatchRec(S[i], P[i]));
            System.out.println();
        }

    }

    public boolean isMatchRec(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        boolean first_flag = (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (isMatch(s, p.substring(2))) || (first_flag && isMatchRec(s.substring(1), p));
        } else {
            return (first_flag && isMatchRec(s.substring(1), p.substring(1)));
        }
    }


    public boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return false;
        }

        int sLen = s.length();
        int pLen = p.length();

        boolean [][] T = new boolean[sLen+1][pLen+1];

        T[0][0] = true;

        for (int i=1; i<T[0].length; i++) {
            if (i > 1 && p.charAt(i-1) == '*') {
                T[0][i] = T[0][i-2];
            }
//            else if (p.charAt(i-1) == '.') {
//                T[0][i] = T[0][i-1];
//            }
        }

        for (int i=1; i<=sLen; i++) {
            for (int j=1; j<=pLen; j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1)  == '.') {
                    T[i][j] = T[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    T[i][j] = T[i][j-2];
                    if (p.charAt(j-2) == '.' || s.charAt(i-1) == p.charAt(j-2)) {
                        T[i][j] = T[i][j] || T[i-1][j];
                    }
                }
            }
        }

        //print the DP matrix
        //System.out.println("DP Matrix: ");
        //printMatrix(T);

        return T[sLen][pLen];
    }

    public void printMatrix(boolean [][] M) {
        int row = M.length;
        int col = M[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
               if (M[i][j]) {
                   System.out.print(1 + " ");
               } else {
                   System.out.print(0 + " ");
               }
            }
            System.out.println();
        }
    }
}
