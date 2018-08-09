import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find all possible interpretations of an array of digits
 * Consider a coding system for alphabets to integers where ‘a’ is represented
 * as 1, ‘b’ as 2, .. ‘z’ as 26. Given an array of digits (1 to 9) as input,
 * write a function that prints all valid interpretations of input array.
 *
 * Examples
 *
 * Input: {1, 1}
 * Output: ("aa", 'k")
 * [2 interpretations: aa(1, 1), k(11)]
 *
 * Input: {1, 2, 1}
 * Output: ("aba", "au", "la")
 * [3 interpretations: aba(1,2,1), au(1,21), la(12,1)]
 *
 * Input: {9, 1, 8}
 * Output: {"iah", "ir"}
 * [2 interpretations: iah(9,1,8), ir(9,18)]
 *
 ================
 INPUT / OUTPUT
 ===============
 input: [1, 1]
 possible interpretations:
 [1, 1][aa]
 [11][k]

 input: [1, 2, 1]
 possible interpretations:
 [1, 2, 1][aba]
 [1, 21][au]
 [12, 1][la]

 input: [9, 1, 8]
 possible interpretations:
 [9, 1, 8][iah]
 [9, 18][ir]

 */

public class PossibleInterpretations {

    private String S = "0abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        PossibleInterpretations pi = new PossibleInterpretations();

        int [][] M = {
                {1, 1},
                {1, 2, 1},
                {9, 1, 8}
        };

        for (int i=0; i<M.length; i++) {
            System.out.println("input: " + Arrays.toString(M[i]));
            System.out.println("possible interpretations: ");
            pi.possibleInterpretations(M[i]);
            System.out.println();

        }
    }

    public void possibleInterpretations(int [] A) {
        interpretations(A, 0, new ArrayList<>());
    }

    private void interpretations(int [] A, int index, List<Integer> result) {
        if (index == A.length) {
            System.out.print(result + "[");
            for (int i: result) {
                System.out.print(S.charAt(i));
            }
            System.out.println("]");
            return;
        }

        if (index > A.length) {
            return;
        }

        result.add(A[index]);
        interpretations(A, index+1, result);
        result.remove(result.size()-1);

        if (index+1 < A.length) {
            int num = A[index]*10 + A[index+1];
            if (num <= 26) {
                result.add(num);
                interpretations(A, index+2, result);
                result.remove(result.size()-1);
            }
        }

    }
}
