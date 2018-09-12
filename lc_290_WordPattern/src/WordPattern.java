import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 Given a pattern and a string str, find if str follows the same pattern.

 Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

 Example 1:

 Input: pattern = "abba", str = "dog cat cat dog"
 Output: true
 Example 2:

 Input:pattern = "abba", str = "dog cat cat fish"
 Output: false
 Example 3:

 Input: pattern = "aaaa", str = "dog cat cat dog"
 Output: false
 Example 4:

 Input: pattern = "abba", str = "dog dog dog dog"
 Output: false
 Notes:
 You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

 */

public class WordPattern {

    public static void main(String[] args) {
        WordPattern wp = new WordPattern();
        String [] P = {
                "abba",
                "abba",
                "aaaa",
                "abba"
        };

        String [] S = {
                "dog cat cat dog",
                "dog cat cat fish",
                "dog cat cat dog",
                "dog dog dog dog"
        };

        for (int i=0; i<P.length; i++) {
            boolean ans = wp.wordPattern(P[i], S[i]);
            System.out.println("ans: " + ans);
        }

//        boolean ans = wp.wordPattern(P[3], S[3]);
//        System.out.println("ans: " + ans);

    }

    public boolean wordPattern(String pattern, String str) {

        if (pattern == null || str == null) {
            return false;
        }

        String [] words = str.split(" ");

        if (pattern.length() != words.length) {
            return false;
        }

        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        int index = 0;
        boolean flag = true;
        for (char ch: pattern.toCharArray()) {
            String si = map.getOrDefault(ch, null);

            if (si != null && !si.equals(words[index])) {
                flag = false;
                break;
            } else if (si == null){
                map.put(ch, words[index]);
                set.add(words[index]);
            }
            ++index;
        }

        if (map.size() != set.size()) {
            flag = false;
        }

        //System.out.println("map: " + map);

        return flag;

    }
}
