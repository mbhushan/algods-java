import java.util.HashMap;
import java.util.Map;

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

 ===============
 INPUT / OUTPUT
 ===============

 input: [ADOBECODEBANC, ABC]
 min window: BANC

 input: [cabwefgewcwaefgcf, cae]
 min window: cwae

 */
public class MinimumWindowSubstring {

    public static void main(String [] args) {
        MinimumWindowSubstring ms = new MinimumWindowSubstring();

        String [] S = {
                "ADOBECODEBANC",
                "cabwefgewcwaefgcf"
        };

        String [] T = {
                "ABC",
                "cae"
        };

        for (int i=0; i<S.length; i++) {
            System.out.println("input: [" + S[i] + ", " + T[i] + "]");
            ms.minWindow(S[i], T[i]);
            System.out.println();
        }


    }

    public String minWindow(String s, String t) {

        String result = "";

        if (s == null || t == null || t.length() > s.length()) {
            return result;
        }

        Map<Character, Integer> hmap = new HashMap<>();
        for (char ch: t.toCharArray()) {
            hmap.put(ch, hmap.getOrDefault(ch, 0) + 1);
        }

        int begin = 0, end = 0;
        int counter = hmap.size();
        int minLen = Integer.MAX_VALUE;

        while (end < s.length()) {
            char ch = s.charAt(end);

            if (hmap.containsKey(ch)) {
                hmap.put(ch, hmap.getOrDefault(ch, 0)-1);
                if (hmap.get(ch) == 0) {
                    --counter;
                }
            }
            ++end;

            while (counter == 0) {
                char tmpCh = s.charAt(begin);

                if (hmap.containsKey(tmpCh)) {
                    if ((end - begin) < minLen) {
                        result =s.substring(begin, end);
                        minLen = result.length();
                    }
                    hmap.put(tmpCh, hmap.getOrDefault(tmpCh, 0)+1);
                    if (hmap.get(tmpCh) > 0) {
                        ++counter;
                    }
                }

                ++begin;
            }
        }

        System.out.println("min window: " + result);

        return result;

    }
}
