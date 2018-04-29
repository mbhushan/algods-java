import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Created by manib on 4/28/18.
 *
 * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start
 * to end, such that only one letter can be changed at a time and each intermediate word must exist in the dictionary.
 * For example, given:
 start = "hit"
 end = "cog"
 dict = ["hot","dot","dog","lot","log"]
 */
public class WordLadder {
    private String [] dict = {"hot","dot","dog","lot","log", "bag", "lag", "tag", "pop", "pos"};

    public static void main(String [] args) {
        WordLadder wordLadder = new WordLadder();
        String start = "hit";
        String end = "cog";
        wordLadder.calcPath(start, end);

    }

    public void calcPath(String start, String end) {
        if (start == null || end == null || start.isEmpty() || end.isEmpty() || (start.length() != end.length())) {
            System.out.println("wrong input");
            return;
        }

        Set<String> wordSet = new HashSet<>();
        this.processDictionary(wordSet);
        wordSet.add(end);

        Node node = new Node(start, null);
        Queue<Node> queue = new LinkedList<Node>();
        List<String> toRemove = new LinkedList<>();
        int steps = 0;
        boolean pathFound = false;

        queue.add(node);

        while (!queue.isEmpty()) {
            node = queue.remove();
            ++steps;
            for (String str: wordSet) {
                if (isDistanceOne(node.word, str)) {
                    Node newNode = new Node(str, node);
                    if (str.equals(end)) {
                        System.out.println("Path Found: " + steps + " steps");
                        //print the path.
                        printWordPath(newNode, end);
                        pathFound = true;
                        break;
                    }
                    queue.add(newNode);
                    toRemove.add(str);
                }
            }
            if (pathFound) {
                break;
            }
            for (String str: toRemove) {
                wordSet.remove(str);
            }
        }
    }

    private void printWordPath(Node node, String end) {
        if (node.parent != null) {
            printWordPath(node.parent, end);
        }
        if (node.word.equals(end)) {
            System.out.print(node.word);
        } else {
            System.out.print(node.word + " -> ");
        }
    }

    private boolean isDistanceOne(String src, String target) {
        int len = src.length();
        int distance = 0;
        for (int i=0; i<len; i++) {
            if (src.charAt(i) == target.charAt(i)) {
                continue;
            } else {
                ++distance;
            }
        }

        return 1 == distance;
    }

    private void processDictionary(Set<String> wordSet) {
        for (String word: this.dict) {
            wordSet.add(word);
        }
    }
}

class Node {
    String word;
    Node parent;

    Node(String word, Node parent) {
        this.word = word;
        this.parent = parent;
    }

    public String toString() {
        String str = "[ word: " + this.word + ", parent: ";
        if (this.parent != null) {
            str += this.parent.word ;
        }
        str += " ]";
        return str;
    }
}
