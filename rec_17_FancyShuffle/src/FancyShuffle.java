import java.util.HashSet;
import java.util.Set;

/**
 Shuffle a character array so that no two repeated characters are together

 ===============
 INPUT / OUTPUT
 ===============
 input: ABA
 output: [ABA]

 input: AABB
 output: [BABA, ABAB]

 input: ABCA
 output: [ABCA, CABA, BACA, ABAC, ACAB, ACBA]
 */

public class FancyShuffle {

    public static void main(String [] args) {
        FancyShuffle fs = new FancyShuffle();

        String [] inputs = {
          "ABA", "AABB", "ABCA"
        };

        for (String s: inputs) {
            System.out.println("input: " + s);
            fs.shuffle(s);
            System.out.println();
        }


    }

    public void shuffle(String str) {
        System.out.print("output: ");
        Set<String> set = new HashSet<>();
        shuffle(str.toCharArray(), 0, set);
        System.out.print(set);
        System.out.println();
    }

    private void shuffle(char [] A, int index, Set<String> set) {

        if (index == A.length) {
            set.add(new String(A));
           // System.out.print(new String(A) + " ");
            return;
        }

        if (index > A.length) {
            return;
        }

        for (int i=index; i<A.length; i++) {

            if (i != index && A[i] == A[index]) {
                continue;
            }

            if (index > 0 && A[i] == A[index-1]) {
                continue;
            }

            //regular permutation
            char ch = A[i];
            A[i] = A[index];
            A[index] = ch;
            shuffle(A, index+1, set);

            //reverse the decision
            ch = A[i];
            A[i] = A[index];
            A[index] = ch;
        }

    }
}
