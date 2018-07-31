import java.util.ArrayList;
import java.util.List;

/**
 Find if an array of strings can be chained to form a circle | Set 1
 Given an array of strings, find if the given strings can be chained to form a circle.
 A string X can be put before another string Y in circle if the last character of X is same as first character of Y.

 Examples:

 Input: arr[] = {"geek", "king"}
 Output: Yes, the given strings can be chained.
 Note that the last character of first string is same
 as first character of second string and vice versa is
 also true.

 Input: arr[] = {"for", "geek", "rig", "kaf"}
 Output: Yes, the given strings can be chained.
 The strings can be chained as "for", "rig", "geek"
 and "kaf"

 Input: arr[] = {"aab", "bac", "aaa", "cda"}
 Output: Yes, the given strings can be chained.
 The strings can be chained as "aaa", "aab", "bac"
 and "cda"

 Input: arr[] = {"aaa", "bbb", "baa", "aab"};
 Output: Yes, the given strings can be chained.
 The strings can be chained as "aaa", "aab", "bbb"
 and "baa"

 Input: arr[] = {"aaa"};
 Output: Yes

 Input: arr[] = {"aaa", "bbb"};
 Output: No

 Input  : arr[] = ["abc", "efg", "cde", "ghi", "ija"]
 Output : Yes
 These strings can be reordered as, “abc”, “cde”, “efg”,
 “ghi”, “ija”

 Input : arr[] = [“ijk”, “kji”, “abc”, “cba”]
 Output : No
 */

public class ChainedStrings {

    public static void main(String[] args) {
        ChainedStrings cs = new ChainedStrings();

        String [] A = {"aaa", "bbb", "baa", "aab"};

        cs.chainPossible(A);


    }

    public boolean chainPossible(String [] A) {
        List<String> visited = new ArrayList<>();
        String first = A[0];
        char last = first.charAt(first.length()-1);
        int index = 1;

        chainPossible(A, visited, last, index);

        return true;
    }

    public void chainPossible(String [] A, List<String> visited, char last, int index) {

        if (visited.size() == A.length) {
            System.out.println(visited);
            return ;
        }

        if (index >= A.length) {
            return;
        }

        char first = A[index].charAt(0);

        if (first == last) {
            last = A[index].charAt(A[index].length()-1);
            visited.add(A[index]);
            chainPossible(A, visited, last, index+1);
            visited.remove(visited.size()-1);
        } else if (index+1 < A.length){
            //swap
            String tmp = A[index];
            A[index] = A[index+1];
            A[index+1] = tmp;
            chainPossible(A, visited, last, index);
        }


    }
}
