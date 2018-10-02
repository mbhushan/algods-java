import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();

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
            List<String> ans = wb.wordBreak(S[i], new HashSet<String>(dicts[i]));
            System.out.println(ans);
            System.out.println();
        }
    }

    public List<String> wordBreak(String s, Set<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String>res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }
}
