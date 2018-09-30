import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 127. Word Ladder
 https://leetcode.com/problems/word-ladder/description/

 Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

 Only one letter can be changed at a time.
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 Note:

 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.
 Example 1:

 Input:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]

 Output: 5

 Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.
 Example 2:

 Input:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 Output: 0

 Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 */
public class WordLadder {

    public static void main(String[] args) {

        String start = "qa";
        String end = "sq";
        List<String> wordList = new ArrayList<>(Arrays.asList("qa",
                "sq", "si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci",
                "ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au",
                "ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li",
                "ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne",
                "mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"));

        System.out.println("ans: " + new WordLadder().ladderLengthBFS(start, end, wordList));
    }

    public int ladderLengthBFS(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();

        if (!set.contains(endWord)){
            return 0;
        }

        queue.add(beginWord);
        String marker = "-1";
        queue.add(marker);
        int level = 1;
        while (!queue.isEmpty()) {
            String node = queue.remove();
            if (node.equals(marker)) {
                ++level;
                if (!queue.isEmpty()) {
                    queue.add(marker);
                }
            } else {
                if (node.equals(endWord)) {
                    return level;
                }
                if (!set.isEmpty()) {
                    nextLevelWords(node, set, queue);
                }
            }

        }

        return 0;
    }

    public void nextLevelWords(String s, Set<String> wordList, Queue<String> queue) {

        List<String> toRem = new ArrayList<String>();
        for (String w: wordList) {
            int diff = 0;
            for (int i=0; i<w.length(); i++) {
                if (s.charAt(i) != w.charAt(i)) {
                    ++diff;
                    if (diff > 1) {
                        break;
                    }
                }
            }
            if (diff == 1) {
                toRem.add(w);
                queue.add(w);
            }
        }

        for (String r: toRem) {
            wordList.remove(r);
        }
    }

    private int count = Integer.MAX_VALUE;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        ladderLenHelper(beginWord, endWord, wordList, new HashSet<String>(), 1);
        return count == Integer.MAX_VALUE ? 0 : count;
    }

    private void ladderLenHelper(String start, String end, List<String> dict, Set<String> visited, int level) {
        if (start.equals(end)) {
            count = Math.min(count, level);
        }

        for (String s: dict) {
            int diff = 0;
            for (int i=0; i<s.length(); i++) {
                if (start.charAt(i) != s.charAt(i)) {
                    ++diff;
                    if (diff > 1) {
                        break;
                    }
                }
            }
            if (diff == 1 && !visited.contains(s)) {
                visited.add(s);
                ladderLenHelper(s, end, dict, visited, level+1);
                visited.remove(s);
            }
        }
    }
}
