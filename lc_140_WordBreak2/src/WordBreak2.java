import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 140. Word Break II
 https://leetcode.com/problems/word-break-ii/description/
 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

 Note:

 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.
 Example 1:

 Input:
 s = "catsanddog"
 wordDict = ["cat", "cats", "and", "sand", "dog"]
 Output:
 [
 "cats and dog",
 "cat sand dog"
 ]
 Example 2:

 Input:
 s = "pineapplepenapple"
 wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 Output:
 [
 "pine apple pen apple",
 "pineapple pen apple",
 "pine applepen apple"
 ]
 Explanation: Note that you are allowed to reuse a dictionary word.
 Example 3:

 Input:
 s = "catsandog"
 wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output:
 []

 */

public class WordBreak2 {

    public static void main(String[] args) {
        WordBreak2 wb = new WordBreak2();

        String [] S = {
          "catsanddog",
          "pineapplepenapple",
                "catsandog"
        };

        List<String> [] dicts = new ArrayList[]{
                new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat")),
                new ArrayList<>(Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")),
                new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"))
        };

        System.out.println("DFS approach: ");
        for (int i=0; i<S.length; i++) {
            wb.wordBreak(S[i], dicts[i]);
          //  System.out.println();
        }
        System.out.println();

        //wb.wordBreak("catsanddog", dicts[0]);

        System.out.println("DFS with memoization WIP: ");
        for (int i=0; i<S.length; i++) {
            wb.wordBreak2(S[i], dicts[i]);
           // System.out.println();
        }
        System.out.println();

        System.out.println("DFS with memoization: ");
        for (int i=0; i<S.length; i++) {
            wb.wordBreak3(S[i], new HashSet<>(dicts[i]));
            // System.out.println();
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {

        List<List<String>> result = new ArrayList<>();
        List<String> buff = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();

        Set<Integer> set = new HashSet<>();

        wordBreak(s, 0, wordDict, buff, result);

        System.out.println(result);

        List<String> ans = new ArrayList<>();
        for (List<String> list: result) {
            String str = String.join(" ", list);
            ans.add(str);
        }

        return ans;

    }

    private boolean wordBreak(String s, int index, List<String> wordDict, List<String> buff,
                           List<List<String>> result) {
        if (index == s.length()) {
            List<String> temp = new ArrayList<>();
            temp.addAll(buff);
            result.add(temp);
            return true;
        }

        boolean flag = false;

        for (int i=index; i<s.length(); i++) {

            String word = s.substring(index, i+1);
            if (wordDict.contains(word)) {
                buff.add(word);
                flag = wordBreak(s, index+word.length(), wordDict, buff, result);
                buff.remove(buff.size()-1);

            }

        }
        return flag;
    }


    public List<String> wordBreak2(String s, List<String> wordDict) {

        List<List<String>> result = new ArrayList<>();
        List<String> buff = new ArrayList<>();

        Map<Integer, List<String>> map = new HashMap<>();

        wordBreak2(s, 0, wordDict, buff, result, map);

        System.out.println(result);

        List<String> ans = new ArrayList<>();
        for (List<String> list: result) {
            String str = String.join(" ", list);
            ans.add(str);
        }

        return ans;

    }

    private void wordBreak2(String s, int index, List<String> wordDict, List<String> buff,
                              List<List<String>> result, Map<Integer, List<String>> map) {



        if (index == s.length()) {
            List<String> temp = new ArrayList<>();
            temp.addAll(buff);
            result.add(temp);

            return;
        }

        if (map.containsKey(index)) {
            buff = new ArrayList<>();
            buff.addAll(map.get(index));
            int len = length(buff);
            wordBreak2(s, index+len, wordDict, buff, result, map);
        }

        for (int i=index; i<s.length(); i++) {
            String word = s.substring(index, i+1);
            if (wordDict.contains(word)) {
                buff.add(word);
                wordBreak2(s, index+word.length(), wordDict, buff, result, map);
                buff.remove(buff.size()-1);
            }
        }
        List<String> temp = new ArrayList<>();
        temp.addAll(buff);
        map.put(index, temp);
//        System.out.println("map: [" + index + "] -> " + map);
//        System.out.println("buff: [" + index + "] = " + buff);
//        System.out.println();
    }

    public static int length(List<String> list) {
        if (list.size() == 0) {
            return 0;
        }
        return list.get(0).length() + length(list.subList(1, list.size()));
    }

    public List<String> wordBreak3(String s, Set<String> wordDict) {
        HashMap<Integer, List<String>> map = new HashMap<>();

        List<String> res = word_Break3(s, wordDict, 0, map);
        System.out.println("res: " + res);
        return res;
    }


    public List<String> word_Break3(String s, Set<String> wordDict, int start, HashMap<Integer, List<String>> map) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("\t");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            String w = s.substring(start, end);
            if (wordDict.contains(w)) {
                List<String> list = word_Break3(s, wordDict, end, map);
                for (String l : list) {
                    res.add(w +  " " + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }
}
