/**
 647. Palindromic Substrings
 https://leetcode.com/problems/palindromic-substrings/description/

 Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

 Example 1:
 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".
 Example 2:
 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 Note:
 The input string length won't exceed 1000.

 */
public class PalindromicString {

    public static void main(String[] args) {
        String [] inputs = {
          "abc", "aaa"
        };

        for (String s: inputs) {
            System.out.println("input: " + s);
            System.out.println("ans: " + new PalindromicString().countSubstrings(s));
        }
    }

    public int countSubstrings(String s) {
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                if(dp[i][j]) ++res;
            }
        }
        return res;
    }

    public int countSubstringsIter(String s) {
        if (s == null) {
            return 0;
        }

        int slen = s.length();
        int [] count = new int[1];
        for (int i=0; i<slen; i++) {
            checkPalindrome(s, i, i, count);
            checkPalindrome(s, i, i+1, count);
        }

        return count[0];
    }

    private void checkPalindrome(String s, int i, int j, int [] count) {
        while (i >= 0 && j <s.length()  && (s.charAt(i) == s.charAt(j))) {
            ++count[0];
            --i;
            ++j;
        }
    }
}
