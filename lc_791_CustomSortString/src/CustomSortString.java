import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 791. Custom Sort String
 https://leetcode.com/problems/custom-sort-string/description/

 S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

 S was sorted in some custom order previously. We want to permute the characters of T so that
 they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur
 before y in the returned string.

 Return any permutation of T (as a string) that satisfies this property.

 Example :
 Input:
 S = "cba"
 T = "abcd"
 Output: "cbad"
 Explanation:
 "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
 Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.

 */
public class CustomSortString {

    public static void main(String[] args) {

    }

    public String customSortString(String S, String T) {
        List<Node> list = new ArrayList<>();

        for (char ch: T.toCharArray()) {
            int index = S.indexOf(ch);
            if (index != -1) {
                list.add(new Node(ch, index));
            } else {
                list.add(new Node(ch, Integer.MAX_VALUE));
            }
        }

        Collections.sort(list, (a, b) -> a.index - b.index);

        StringBuffer sb = new StringBuffer();
        for (Node n: list) {
            sb.append(n.ch);
        }

        return sb.toString();
    }
}

class Node {
    char ch;
    int index;

    Node (char c, int i) {
        ch = c;
        index = i;
    }
}