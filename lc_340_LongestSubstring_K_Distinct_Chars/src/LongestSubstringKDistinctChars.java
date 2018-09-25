import java.util.HashMap;
import java.util.Map;

/*
340. Longest Substring with At Most K Distinct Characters
https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/

Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.

==============input / output==========
input: [eceba, 2]
lis: 3
*/
public class LongestSubstringKDistinctChars {

    public static void main(String[] args) {

        LongestSubstringKDistinctChars ls = new LongestSubstringKDistinctChars();

        String s = "eceba";
        int k = 2;

        System.out.println("input: " + "[" + s + ", " + k + "]");
        System.out.println("lis: " + ls.lengthOfLongestSubstringKDistinct(s, k));

    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() < 1 || k == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        int slen = s.length();
        int i = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int begin = 0, end = 0;
        int maxLen = 0;
        while (end < slen) {
            Character ch = s.charAt(end);
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0)+1);

            while (begin < end && map.size() > k) {
                if (map.get(s.charAt(begin)) > 1) {
                    map.put(s.charAt(begin), map.get(s.charAt(begin))-1);
                } else {
                    map.remove(s.charAt(begin));
                }
                ++begin;
            }
            maxLen = Math.max(maxLen, end-begin+1);
            ++end;

        }

        return maxLen;
    }
}
