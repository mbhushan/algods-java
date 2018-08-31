import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 438. Find All Anagrams in a String
 https://leetcode.com/problems/find-all-anagrams-in-a-string/description/

 Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

 Strings consists of lowercase English letters only and the length of
 both strings s and p will not be larger than 20,100.

 The order of output does not matter.

 Example 1:

 Input:
 s: "cbaebabacd" p: "abc"

 Output:
 [0, 6]

 Explanation:
 The substring with start index = 0 is "cba", which is an anagram of "abc".
 The substring with start index = 6 is "bac", which is an anagram of "abc".
 Example 2:

 Input:
 s: "abab" p: "ab"

 Output:
 [0, 1, 2]

 Explanation:
 The substring with start index = 0 is "ab", which is an anagram of "ab".
 The substring with start index = 1 is "ba", which is an anagram of "ab".
 The substring with start index = 2 is "ab", which is an anagram of "ab".

 ======================================
 INPUT / OUTPUT
 ======================================
 indices: [0, 6]
 lc result: [0, 6]

 indices: [0, 1, 2]
 lc result: [0, 1, 2]
 */

public class AllAnagrams {

    public  static void main(String [] args) {

        AllAnagrams aa = new AllAnagrams();

        String [] s = {"cbaebabacd", "abab"};
        String [] p = {"abc", "ab"};

        for (int i=0; i<s.length; i++) {
            aa.findAnagramsSlow(s[i], p[i]);
            aa.findAnagrams(s[i], p[i]);
            System.out.println();
        }

    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> indices = new ArrayList<>();

        if (p == null || s == null || p.length() > s.length()) {
            return indices;
        }

        Map<Character, Integer> hmap = new HashMap<>();

        for (char ch: p.toCharArray()) {
            hmap.put(ch, hmap.getOrDefault(ch, 0) + 1);
        }


        int counter = hmap.size();

        int begin = 0, end = 0;

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
                char tmpB = s.charAt(begin);
                if (hmap.containsKey(tmpB)) {

                    hmap.put(tmpB, hmap.getOrDefault(tmpB, 0) + 1);
                    if (hmap.get(tmpB) > 0) {
                        ++counter;
                    }
                }
                if (end - begin  == p.length()) {
                    indices.add(begin);
                }
                ++begin;
            }
        }
        System.out.println("lc result: " + indices);
        return indices;
    }


    public List<Integer> findAnagramsSlow(String s, String p) {
        List<Integer> indices = new ArrayList<>();

        if (p == null || s == null) {
            return indices;
        }

        char [] P = p.toCharArray();
        char [] S = s.toCharArray();

        Arrays.sort(P);
        p = String.valueOf(P);
        int plen = P.length;

        int i=0;

        while (i<S.length-plen+1) {
            int j = i + plen;
            char [] T = String.valueOf(s.substring(i, j)).toCharArray();
            Arrays.sort(T);
            String tmp = String.valueOf(T);
            if (tmp.equals(p)) {
                indices.add(i);
            }
            ++i;
        }

        System.out.println("indices: " + indices);

        return indices;

    }
}
