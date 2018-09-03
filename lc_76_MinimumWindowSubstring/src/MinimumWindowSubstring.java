/**
 76. Minimum Window Substring
 https://leetcode.com/problems/minimum-window-substring/description/

 Given a string S and a string T, find the minimum window in S which will
 contain all the characters in T in complexity O(n).

 Example:
 Input: S = "ADOBECODEBANC", T = "ABC"
 Output: "BANC"

 Note:
 If there is no such window in S that covers all characters in T, return the empty string "".
 If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

 */
public class MinimumWindowSubstring {

    public static void main(String [] args) {
        MinimumWindowSubstring ms = new MinimumWindowSubstring();

        String [] S = {
                "ADOBECODEBANC"
        };

        String [] T = {
                "ABC"
        };

        for (int i=0; i<S.length; i++) {
            ms.minWindow(S[i], T[i]);
        }


    }

    public String minWindow(String s, String t) {

        return null;

    }
}
