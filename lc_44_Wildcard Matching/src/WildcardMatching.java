/**

 44. Wildcard Matching
 https://leetcode.com/problems/wildcard-matching/description/


 Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).
 The matching should cover the entire input string (not partial).

 Note:

 s could be empty and contains only lowercase letters a-z.
 p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 Example 1:

 Input:
 s = "aa"
 p = "a"
 Output: false
 Explanation: "a" does not match the entire string "aa".
 Example 2:

 Input:
 s = "aa"
 p = "*"
 Output: true
 Explanation: '*' matches any sequence.
 Example 3:

 Input:
 s = "cb"
 p = "?a"
 Output: false
 Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 Example 4:

 Input:
 s = "adceb"
 p = "*a*b"
 Output: true
 Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 Example 5:

 Input:
 s = "acdcb"
 p = "a*c?b"
 Output: false

 ========================
 INPUT / OUTPUT
 ========================
 string: aa
 pattern: a
 DP match: false
 Rec match: false

 string: aa
 pattern: *
 DP match: true
 Rec match: true

 string: cb
 pattern: ?a
 DP match: false
 Rec match: false

 string: adceb
 pattern: *a*b
 DP match: true
 Rec match: true

 string: acdcb
 pattern: a*c?b
 DP match: false
 Rec match: false

 */


public class WildcardMatching {

    public static void main(String [] args) {
        WildcardMatching wm = new WildcardMatching();

        String [] S = {
                "aa",
                "aa",
                "cb",
                "adceb",
                "acdcb",
                "aab"
        };

        String [] P = {
                "a",
                "*",
                "?a",
                "*a*b",
                "a*c?b",
                "c*a*b"
        };

        for (int i = 0; i < S.length; i++) {
            System.out.println("string: " + S[i]);
            System.out.println("pattern: " + P[i]);
            System.out.println("DP match: " + wm.isMatch(S[i], P[i]));
            System.out.println("Rec match: " + wm.isMatchRec(S[i], P[i]));
            System.out.println();
        }

    }

    public boolean isMatchRec(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        if (p.length()==1 && s.length() == 1 && p.charAt(0) == '*') {
            return true;
        }

        boolean flag = (!s.isEmpty()) && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?');

        if (p.length()>=1 && s.length() >= 1 && p.charAt(0) == '*') {
            return isMatchRec(s.substring(1), p) || isMatchRec(s, p.substring(1));
        }
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (flag && isMatchRec(s.substring(1), p)) || isMatchRec(s, p.substring(1));
        } else {
            return flag && isMatchRec(s.substring(1), p.substring(1));
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
            if (p.charAt(i-1) == '*') {
                T[0][i] = T[0][i-1];
            }
        }

        for (int i=1; i<=sLen; i++) {
            for (int j=1; j<=pLen; j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                    T[i][j] = T[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    T[i][j] = T[i-1][j] || T[i][j-1];
                }
            }
        }

        return T[sLen][pLen];
    }
}
