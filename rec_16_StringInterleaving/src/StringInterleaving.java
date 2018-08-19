import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 Print all interleavings of given two strings
 Given two strings str1 and str2, write a function that prints all interleavings of the given two strings.
 You may assume that all characters in both strings are different

 Example:

 Input: str1 = "AB",  str2 = "CD"
 Output:
 ABCD
 ACBD
 ACDB
 CABD
 CADB
 CDAB

 Input: str1 = "AB",  str2 = "C"
 Output:
 ABC
 ACB
 CAB
 An interleaved string of given two strings preserves the order of characters in individual strings.
 For example, in all the interleavings of above first example, ‘A’ comes before ‘B’ and ‘C’ comes before ‘D’.

 ===============
 INPUT / OUTPUT
 ===============
 input strings: [AB, C]
 ABC ACB CAB

 input strings: [AB, CD]
 ABCD ACBD ACDB CABD CADB CDAB

 */

public class StringInterleaving {

    public static void main(String[] args) {
        StringInterleaving si = new StringInterleaving();

        String [] S1 = {"AB", "AB"};
        String [] S2 = {"C", "CD"};

        for (int i=0; i<S1.length; i++) {
            si.findInterleavings(S1[i], S2[i]);
            System.out.println();
        }

    }

    public void findInterleavings(String s1, String s2) {
        System.out.println("input strings: [" + s1 +", " + s2 + "]");

        findInterleavings(s1.toCharArray(), 0, s2.toCharArray(), 0, 0, new ArrayList<Character>());

        System.out.println();
    }

    private void findInterleavings(char [] A, int i, char [] B, int j, int index, List<Character> buff) {
        if (index == (A.length + B.length)) {
            System.out.print(buff.stream().map(String::valueOf).collect(Collectors.joining()) + " ");
            return;
        }

        if (i < A.length) {
            buff.add(A[i]);
            findInterleavings(A, i+1, B, j, index+1, buff);
            buff.remove(buff.size()-1);

        }
        if (j < B.length) {
            buff.add(B[j]);
            findInterleavings(A, i, B, j+1, index+1, buff);
            buff.remove(buff.size()-1);
        }

    }
}
