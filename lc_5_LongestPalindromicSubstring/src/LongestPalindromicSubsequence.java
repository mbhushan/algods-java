/**
 5. Longest Palindromic Substring
 https://leetcode.com/problems/longest-palindromic-substring/description/

 Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example 1:

 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.
 Example 2:

 Input: "cbbd"
 Output: "bb"


 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {

        LongestPalindromicSubsequence obj = new LongestPalindromicSubsequence();
        String s = "babad";
        String ans = obj.longestPalindrome(s);
        System.out.println("longest palindrome: " + ans);
    }

    private int low = 0;
    private int maxLen = 0;

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int slen = s.length();
        for (int i=0; i<slen-1; i++) {
            expand(s, slen, i, i); //odd length;
            expand(s, slen, i, i+1); //even length;
        }

        return s.substring(low, low + maxLen);
    }

    private void expand(String s, int slen, int j, int k) {
        while (j >= 0 && k < slen && s.charAt(j) == s.charAt(k)) {
            --j;
            ++k;
        }
        if (maxLen < (k - (j+1))) {
            maxLen = k - (j+1);
            low = j+1;
        }
    }
}
