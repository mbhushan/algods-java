import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 692. Top K Frequent Words
 https://leetcode.com/problems/top-k-frequent-words/description/

 Given a non-empty list of words, return the k most frequent elements.

 Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency,
 then the word with the lower alphabetical order comes first.

 Example 1:
 Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 Output: ["i", "love"]
 Explanation: "i" and "love" are the two most frequent words.
 Note that "i" comes before "love" due to a lower alphabetical order.
 Example 2:
 Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 Output: ["the", "is", "sunny", "day"]
 Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 with the number of occurrence being 4, 3, 2 and 1 respectively.
 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Input words contain only lowercase letters.
 Follow up:
 Try to solve it in O(n log k) time and O(n) extra space.

 */
public class TopKFrequent {

    public static void main(String[] args) {

    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();

        if (k < 1) {
            return result;
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String w: words) {
            map.put(w, map.getOrDefault(w, 0)+1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o2.freq == o1.freq)  {
                    return o1.key.compareTo(o2.key);
                } else {
                    return o2.freq - o1.freq;
                }
            }
        });

        for (String s: map.keySet()) {
            pq.add(new Node(s, map.get(s)));
        }

        while (k > 0) {
            result.add(pq.remove().key);
            --k;
        }

        return result;
    }

    //Min Heap Solution:
    public List<String> topKFrequentMinHeap(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
                (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                        w2.compareTo(w1) : count.get(w1) - count.get(w2) );

        for (String word: count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }

    //Sorting based nlogn
    public List<String> topKFrequentSorting(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList(count.keySet());
        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w1.compareTo(w2) : count.get(w2) - count.get(w1));

        return candidates.subList(0, k);
    }
}

class Node {
    String key;
    int freq;
    Node(String k, int f) {
        key = k;
        freq = f;
    }
}
