import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KEditDistance {
    public static void main(String[] args) {
        KEditDistance kEditDistance = new KEditDistance();
        TrieNode root = kEditDistance.new TrieNode();
        String[] lists = new String[] {"abcd", "abc", "abd","ad"};
        String word = "ac";
        int k = 1;
        List<String> result = kEditDistance.getKEditDistance(lists, word, k, root);
        for (String s : result) {
            System.out.println(s);
        }
    }

    public List<String> getKEditDistance(String[] lists, String target, int k, TrieNode root) {
        List<String> result = new LinkedList<>();
        if (lists == null || lists.length == 0 || target == null || target.length() == 0 || k < 0) {
            return result;
        }
        for (String s : lists) {
            root.add(s);
        }
        int[] prevDist = new int[target.length() + 1];
        for (int i = 0; i < prevDist.length; i++) {
            prevDist[i] = i;
        }

        System.out.println("prev distance: " + Arrays.toString(prevDist));
        print(root );
        computeDistance("", prevDist, target, k, root, result);
        return result;
    }

    public void computeDistance(String cur, int[] prevDist, String target, int k, TrieNode node, List<String> result) {
        if (node.isWord) {
            if (prevDist[target.length()] <= k) {
                result.add(cur);
            } else {
                return;
            }
        }

        System.out.println("prev distance: " + Arrays.toString(prevDist));

        for (int i = 0; i < 26; i++) {
            if (node.children[i] == null) {
                continue;
            }
//			print(node);

            int[] curDist = new int[target.length() + 1];
            curDist[0] = cur.length() + 1;
            for (int j = 1; j < prevDist.length; j++) {
                if (target.charAt(j - 1) == (char) (i + 'a')) {
                    curDist[j] = prevDist[j - 1];
                } else {
                    curDist[j] = Math.min(Math.min(prevDist[j - 1], prevDist[j]), curDist[j - 1]) + 1;
                }
            }
            System.out.println("curr distance: " + Arrays.toString(curDist));
            computeDistance(cur + (char)(i + 'a'), curDist, target, k, node.children[i], result);
        }
    }

    public  void print(TrieNode root) {
        TrieNode cur = root;
        int level = 0;
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(cur);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                cur = queue.poll();
                System.out.println("level " + level + cur.character);
                for (int i = 0; i < cur.children.length; i++) {
                    if (cur.children[i] != null) {
                        queue.add(cur.children[i]);
                    }
                }
                size--;
            }
            level++;
        }

    }

    class TrieNode {
        boolean isWord;
        TrieNode[] children;
        char character;

        public TrieNode() {
            children = new TrieNode[26];
        }

        public void add(String s) {
            if (s == null || s.length() == 0) {
                return;
            }
            TrieNode cur = this;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
                cur.character = c;
            }
            cur.isWord = true;
        }
    }

}
