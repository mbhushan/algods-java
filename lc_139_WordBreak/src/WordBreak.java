import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 139. Word Break
 https://leetcode.com/problems/word-break/description/

 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 Note:

 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.
 Example 1:

 Input: s = "leetcode", wordDict = ["leet", "code"]
 Output: true
 Explanation: Return true because "leetcode" can be segmented as "leet code".
 Example 2:

 Input: s = "applepenapple", wordDict = ["apple", "pen"]
 Output: true
 Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 Note that you are allowed to reuse a dictionary word.
 Example 3:

 Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output: false

 */

public class WordBreak {

   // private Set<String> dict;

    WordBreak() {
     //   dict = new HashSet<String>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
    }

    public static void main(String[] args) {

        WordBreak wb = new WordBreak();

        List<String> dict1 = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
        List<String> dict2 = new ArrayList<>(Arrays.asList("apple", "pen"));
        List<String> dict3 = new ArrayList<>(Arrays.asList("leet", "code"));

      //  boolean ans = wb.wordBreak("catsanddog", dict1);

        System.out.println("ans: " + wb.wordBreak("catsanddog", dict1));
        System.out.println("ans: " + wb.wordBreak("catsandog", dict1));
        System.out.println("ans: " + wb.wordBreak("applepenapple", dict2));
        System.out.println("ans: " + wb.wordBreak("leetcode", dict3));

    }

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<Integer> set = new HashSet<Integer>();
        return wordBreak(s, 0, wordDict, set);

    }

    private boolean wordBreak(String s, int index, List<String> dict, Set<Integer> set) {
        if (index >= s.length()) {
            return true;
        }

        if (set.contains(index)) {
            return false;
        }

        for (int i=index; i<s.length(); i++) {
            String w = s.substring(index, i+1);
            if (dict.contains(w)) {

                boolean flag = wordBreak(s, index + w.length(), dict, set);
                if (flag) {
                    return true;
                }
                set.add(i);
            }
        }

        return false;
    }
}
