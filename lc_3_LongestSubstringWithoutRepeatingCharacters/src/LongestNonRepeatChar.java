import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 3. Longest Substring Without Repeating Characters
 https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

 Given a string, find the length of the longest substring without repeating characters.

 Example 1:

 Input: "abcabcbb"
 Output: 3
 Explanation: The answer is "abc", with the length of 3.
 Example 2:

 Input: "bbbbb"
 Output: 1
 Explanation: The answer is "b", with the length of 1.
 Example 3:

 Input: "pwwkew"
 Output: 3
 Explanation: The answer is "wke", with the length of 3.
 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 */
public class LongestNonRepeatChar {

    public static void main(String[] args) {
        LongestNonRepeatChar ln = new LongestNonRepeatChar();

        String [] inputs = {
                "abcabcbb",
                "bbbbb",
                "pwwkew"

        };

//        for (String s: inputs) {
//            System.out.println("input: " + s);
//            System.out.println(ln.lengthOfLongestSubstring(s));
//            System.out.println();
//        }

        String s = "ab";
        System.out.println("input: " + s);
        System.out.println(ln.lengthOfLongestSubstring(s));
        System.out.println();
    }

    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();
        int n = s.length(), ans = 0;

        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1 );
            map.put(s.charAt(j), j+1);
        }
        return ans;
    }

    public int lengthOfLongestSubstringWrong(String s) {

        if (s == null) {
            return 0;
        }
        if ( s.length() <= 1) {
            return s.length();
        }

        Set<Character> set = new HashSet<>();

        StringBuffer sb = new StringBuffer();
        String result = "";

        for (int i=0; i<s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                if (sb.length() > result.length()) {
                    result = sb.toString();
                    // System.out.println("res: " + result);
                }
                sb.setLength(0);
                set.clear();
            }
            set.add(s.charAt(i));
            sb.append(s.charAt(i));
        }

        if (sb.length() > result.length()) {
            result = sb.toString();
        }

        return result.length();
    }
}
