import java.util.List;

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
 Output: AABC AACB ABAC ABCA ACBA ACAB BAAC BACA
 BCAA CABA CAAB CBAA
 */

public class DistinctPermutations {

    public static void main(String[] args) {
        DistinctPermutations dp = new DistinctPermutations();

        String [] inputs = {
                "AB", "AA", "ABC", "ABA", "ABCA"
        };

        for (String A: inputs) {
            dp.permute(A.toCharArray());
        }
    }

    public void permute(char [] A) {

    }

    private void permute(char [] A, List<Character> result) {

    }

}
