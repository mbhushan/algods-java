import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Find all possible interpretations of an array of digits
 Consider a coding system for alphabets to integers where ‘a’ is
 represented as 1, ‘b’ as 2, .. ‘z’ as 26. Given an array of digits (1 to 9) as input,
 write a function that prints all valid interpretations of input array.

 Examples

 Input: {1, 1}
 Output: ("aa", 'k")
 [2 interpretations: aa(1, 1), k(11)]

 Input: {1, 2, 1}
 Output: ("aba", "au", "la")
 [3 interpretations: aba(1,2,1), au(1,21), la(12,1)]

 Input: {9, 1, 8}
 Output: {"iah", "ir"}
 [2 interpretations: iah(9,1,8), ir(9,18)]

 */

public class PossibleInterpretations {

    private String [] alphabet = {"", "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "v", "z"};

    public PossibleInterpretations() {
    }

    public static void main(String [] args) {
        PossibleInterpretations pi = new PossibleInterpretations();

        int [][] A = {
                {1, 1},
                {1, 2, 1},
                {9, 1, 8},
                {1, 1, 3, 4},
                {1, 1, 1},
                {2, 6},
                {1, 2},
                {1, 0},
                {1, 2, 2, 1}
        };

        for (int i=0; i<A.length; i++) {
            pi.findInterpretations(A[i]);
            System.out.println();
        }

        //pi.findInterpretations(A[2]);
    }

    public  void findInterpretations(int [] A) {
        List<Integer> buff = new ArrayList<>();

        System.out.println("input array: " + Arrays.toString(A));
        System.out.print("possible interpretations: [ ");
        findInterpretations(A, 0, buff);
        System.out.println("]");
    }

    private void findInterpretations(int[] A, int index, List<Integer> buff) {

        if (index >= A.length) {
            //System.out.println(buff);
            StringBuffer sb = new StringBuffer();
            for (int i: buff) {
                if (i > 26) {
                    continue;
                }
                //System.out.print(this.alphabet[i] + " ");
                sb.append(alphabet[i]);
            }
            System.out.print(sb + "; ");
            return;
        }


        buff.add(A[index]);
        findInterpretations(A, index + 1, buff);
        buff.remove(buff.size() - 1);

        if (index + 1 < A.length) {
            int num = A[index] * 10 + A[index+1];
            if (num > 26) {
                return;
            }
            buff.add(num);
            findInterpretations(A, index + 2, buff);
            buff.remove(buff.size() - 1);
        }
    }
}
