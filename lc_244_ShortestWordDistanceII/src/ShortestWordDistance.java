import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 244. Shortest Word Distance II
 Design a class which receives a list of words in the constructor, and implements a
 method that takes two words word1 and word2 and return the shortest distance between these two
 words in the list. Your method will be called repeatedly many times with different parameters.

 Example:
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Input: word1 = “coding”, word2 = “practice”
 Output: 3
 Input: word1 = "makes", word2 = "coding"
 Output: 1
 Note:
 You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.



 */
public class ShortestWordDistance {

    public static void main(String[] args) {
        String [] words = {"practice", "makes", "perfect", "coding", "makes"};

        ShortestWordDistance sd = new ShortestWordDistance(words);

        System.out.println("shortest: " + sd.shortest("practice", "coding"));


    }

    Map<String, List<Integer>> map = new HashMap<>();

    public ShortestWordDistance(String[] words) {

        int index = 0;
        for (String w: words) {
            map.computeIfAbsent(w, v -> new ArrayList<>()).add(index);
            ++index;
        }

    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int i = 0, j = 0, min = Integer.MAX_VALUE;

        while(i < list1.size() && j < list2.size()) { // pairwise comparison

            int index1 = list1.get(i), index2 = list2.get(j);
            if (index1 > index2) {
                min = Math.min(min, index1 - index2);
                j++;
            } else {
                min = Math.min(min, index2 - index1);
                i++;
            }
            if (min == 1) { // doesn't get better than this!
                return min;
            }
        }
        return min;
    }
}
