/**
 680. Valid Palindrome II
 https://leetcode.com/problems/valid-palindrome-ii/description/

 Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

 Example 1:
 Input: "aba"
 Output: True
 Example 2:
 Input: "abca"
 Output: True
 Explanation: You could delete the character 'c'.
 Note:
 The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

 ===============
 INPUT / OUTPUT
 ===============
 input: aba
 valid palindrome O(N^2): true
 valid palindrome O(N): true

 input: abca
 valid palindrome O(N^2): true
 valid palindrome O(N): true

 input: abccda
 valid palindrome O(N^2): false
 valid palindrome O(N): false
 */

public class ValidPalindrome2 {

    public static void main(String[] args) {
        ValidPalindrome2 vp = new ValidPalindrome2();

        String [] S = {
          "aba",
          "abca",
          "abccda"
        };

        for (String s: S) {
            System.out.println("input: " + s);
            System.out.println("valid palindrome O(N^2): " + vp.validPalindromeN2(s));
            System.out.println("valid palindrome O(N): " + vp.validPalindrome(s));
            //System.out.println("abca: " + vp.validPalindrome("abca"));
            System.out.println();
        }

        //System.out.println("abca: " + vp.validPalindrome("abca"));

    }

    public boolean validPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }

        int len = s.length();

        int start = 0;
        int end = len-1;

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return isPalindrome(s, start+1, end) || isPalindrome(s, start, end-1);
            }
            ++start;
            --end;
        }

        return true;
    }

    public boolean validPalindromeN2(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }

        int len = s.length();

        int start = 0;
        int end = len-1;

        if (isPalindrome(s, 0, end)) {
            return true;
        }

        for (int i=0; i<=end; i++) {
            String in =s.substring(0, i) + s.substring(i+1);
            if (isPalindrome(in, 0, in.length()-1)) {
                return true;
            }
        }

        return false;
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            ++start;
            --end;
        }

        return true;
    }
}
