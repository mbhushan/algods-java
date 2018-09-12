import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 291. Word Pattern II

 https://leetcode.com/problems/word-pattern-ii/description/

 Given a pattern and a string str, find if str follows the same pattern.

 Here follow means a full match, such that there is a bijection between a letter in pattern
 and a non-empty substring in str.

 Example 1:

 Input: pattern = "abab", str = "redblueredblue"
 Output: true
 Example 2:

 Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
 Output: true
 Example 3:

 Input: pattern = "aabb", str = "xyzabcxzyabc"
 Output: false
 Notes:
 You may assume both pattern and str contains only lowercase letters.

 */

public class WordPattern2 {

    public static void main(String[] args) {
        WordPattern2 wp = new WordPattern2();

        String [] P = {
                "abab",
                "aaaa",
                "aabb",
        };

        String [] S = {
                "redblueredblue",
                "asdasdasdasd",
                "xyzabcxzyabc"
        };

        for (int i=0; i<P.length; i++) {
            boolean flag = wp.wordPatternMatch(P[i], S[i]);
            System.out.println("ans: " + flag);
            System.out.println();
        }

    }

    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return wordPatternMatch(pattern, 0, str, 0, map, set);
    }

    private boolean wordPatternMatch(String P, int i, String S, int j, Map<Character, String> map, Set<String> set) {
        if (i == P.length() && j == S.length()) {
            return true;
        }

        if (i == P.length() || j == S.length()) {
            return false;
        }


        char ch = P.charAt(i);

        if (map.containsKey(ch)) {

            String ps = map.get(ch);

            if (!S.startsWith(ps, j)) {
                return false;
            }

            return wordPatternMatch(P, i+1, S, j+ps.length(), map, set);

        }


        //pattern char doesnt exist in the map.
        for (int index=j; index<S.length(); index++) {
            String ps = S.substring(j,  index+1);

            if (set.contains(ps)) {
                continue;
            }

            map.put(ch, ps);
            set.add(ps);

            if (wordPatternMatch(P, i+1, S, index+1, map, set)) {
                return true;
            }

            //backtrack
            map.remove(ch);
            set.remove(ps);
        }

        return false;

    }
}
