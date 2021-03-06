import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 Print all distinct permutations of a given string with duplicates
 Given a string that may contain duplicates, write a function to print all permutations
 of given string such that no permutation is repeated in output.

 Examples:

 Input:  str[] = "AB"
 Output: AB BA

 Input:  str[] = "AA"
 Output: AA

 Input:  str[] = "ABC"
 Output: ABC ACB BAC BCA CBA CAB

 Input:  str[] = "ABA"
 Output: ABA AAB BAA

 Input:  str[] = "ABCA"
 Output: AABC AACB ABAC ABCA ACBA ACAB BAAC BACA BCAA CABA CAAB CBAA

 =========================
 INPUT / OUTPUT
 ========================
 input: AB
 output: AB BA

 input: AA
 output: AA

 input: ABC
 output: ABC ACB BAC BCA CBA CAB

 input: ABA
 output: AAB ABA BAA

 input: ABCA
 output: AABC AACB ABAC ABCA ACBA ACAB BAAC BACA BCAA CABA CAAB CBAA
 */

public class DistinctPermutations {

    public static void main(String[] args) {
        DistinctPermutations dp = new DistinctPermutations();

        String [] inputs = {
                "AB", "AA", "ABC", "ABA", "ABCA"
        };

        for (String A: inputs) {
            System.out.println("input: " + A);
            System.out.print("output: ");
            dp.permute(A.toCharArray());
            dp.fancyShuffle(A);
            System.out.println();

        }

       // dp.permute("ABA".toCharArray());
       // dp.fancyShuffle("ABA");
    }

    public void permute(char [] A) {
        Arrays.sort(A);
        permute(A, 0, new ArrayList<>());
        //permuteDistinct(A, 0, new ArrayList<>());
        System.out.println();
    }

    public void fancyShuffle(String input) {
        char [] A = input.toCharArray();
        int index = 0;
        System.out.print("fancy shuffle output: ");
        fancyShuffle(A, index);
        System.out.println();
    }

    private void fancyShuffle(char [] A, int index) {
        if (index == A.length) {
            System.out.print(new String(A) + " ");
            return ;
        }

        for (int i=index; i<A.length; i++) {
            if (i != index && A[i] == A[index]) {
                continue;
            }
            if (index > 0 && A[i] == A[index-1]) {
                continue;
            }
            char ch = A[i];
            A[i] = A[index];
            A[index] = ch;
            fancyShuffle(A, index+1);
            ch = A[i];
            A[i] = A[index];
            A[index] = ch;
        }
    }

    private void permuteDistinct(char [] A, int index, List<Character> result) {
        if (result.size() == A.length) {
            System.out.print(result.stream().map(String::valueOf).collect(Collectors.joining()) + " ");
        }

        if (index >= A.length) {
            return;
        }

        for (int i = index; i < A.length; i++) {
            if (i != index && A[i] == A[index]) {
                continue;
            }

            if (index > 0 && A[i] == A[index-1]) {
                continue;
            }

            result.add(A[i]);

            char tmp = A[i];
            A[i] = A[index];
            A[index] = tmp;
            permute(A, index + 1, result);

            tmp = A[i];
            A[i] = A[index];
            A[index] = tmp;

            result.remove(result.size() - 1);
        }
    }

    private void permute(char [] A, int index, List<Character> result) {
        if (result.size() == A.length) {
            System.out.print(result.stream().map(String::valueOf).collect(Collectors.joining()) + " ");
        }

        if (index >= A.length) {
            return;
        }

        for (int i=index; i<A.length; i++) {
            if (i != index && A[i] == A[index]) {
                continue;
            }
            result.add(A[i]);

            char tmp = A[i];
            A[i] = A[index];
            A[index] = tmp;
            permute(A, index+1, result);

            tmp = A[i];
            A[i] = A[index];
            A[index] = tmp;

            result.remove(result.size()-1);
        }
    }

}
