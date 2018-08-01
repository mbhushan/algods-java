import java.util.ArrayList;
import java.util.Arrays;
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

 ==============
 INPUT / OUTPUT
 ==============
 input: [geek, king]
 chain possible: [geek, king]

 input: [aaa]
 chain possible: [aaa]

 input: [aaa, bbb]
 Not possible chain!

 input: [for, geek, rig, kaf]
 chain possible: [for, rig, geek, kaf]

 input: [aab, bac, aaa, cda]
 chain possible: [aab, bac, cda, aaa]

 input: [aaa, bbb, baa, aab]
 chain possible: [aaa, aab, bbb, baa]

 input: [abc, efg, cde, ghi, ija]
 chain possible: [abc, cde, efg, ghi, ija]

 input: [ijk, kji, abc, cba]
 Not possible chain!

 */

public class ChainedStrings {

    public static void main(String[] args) {
        ChainedStrings cs = new ChainedStrings();

        String [][] A = {
                {"geek", "king"},
                {"aaa"},
                {"aaa", "bbb"},
                {"for", "geek", "rig", "kaf"},
                {"aab", "bac", "aaa", "cda"},
                {"aaa", "bbb", "baa", "aab"},
                {"abc", "efg", "cde", "ghi", "ija"},
                {"ijk", "kji", "abc", "cba"},


        };

        for (int i=0; i<A.length; i++) {
            System.out.println("input: " + Arrays.toString(A[i]));
            boolean ans = cs.chainPossible(A[i]);
            if (!ans) {
                System.out.println("Not possible chain!");
            }
            System.out.println();
        }


    }

    public boolean chainPossible(String [] A) {
        List<String> visited = new ArrayList<>();
        String first = A[0];
        char last = first.charAt(first.length()-1);
        int index = 1;

        return chainPossible(A, visited, last, index);

        //return true;
    }

    public boolean chainPossible(String [] A, List<String> visited, char last, int index) {

        if (index == A.length) {
            System.out.println("chain possible: " + Arrays.toString(A));
            return true;
        }

        if (index >= A.length) {
            return false;
        }

        for (int i=index; i<A.length; i++) {
            char first = A[i].charAt(0);
            if (first == last) {
                char ch = A[i].charAt(A[i].length()-1);

                String tmp = A[index];
                A[index] = A[i];
                A[i] = tmp;

                boolean flag = chainPossible(A, visited, ch, index+1);

                if (flag) {
                    return true;
                }
            }
        }

        return false;
    }
}
