import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Generate all combination which prints star in place of absent characters.
 * e.g a, b c
 * * * *
 * a * *
 * a b *
 * a b c
 * * b *
 * * b c
 * * * c
 *
 * Idea is to store the index of values in used[] array. So just
 * like regular combination if used is set print it else print *
 ====================
 INPUT / OUTPUT
 ====================
 input array: [1, 2, 3]
 generating combinations with star chars:
 * * *
 1 * *
 * 2 *
 * * 3
 1 2 *
 1 * 3
 * 2 3
 1 2 3

 */
public class StarCombinations {

    public static void main(String [] args) {
        StarCombinations sc = new StarCombinations();

        int [] A = {1, 2, 3};

        sc.genCombinations(A);
    }

    public void genCombinations(int [] A) {
        List<Integer> buff = new ArrayList<>();

        System.out.println("input array: " + Arrays.toString(A));

        System.out.println("generating combinations with star chars: ");
        for (int r=0; r<=A.length; r++) {
            genCombinations(A, r, 0, buff);
        }

    }

    private void genCombinations(int [] A, int r, int index, List<Integer> buff) {

        if (buff.size() == r) {
           for (int i=0; i<A.length; i++) {
               if (buff.contains(i)) {
                   System.out.print(A[i] + " ");
               } else {
                   System.out.print("* ");
               }
           }
           System.out.println();
           return;
        }

        if (index >= A.length) {
            return;
        }

        for (int i=index; i<A.length; i++) {
            buff.add(i);
            genCombinations(A, r, i+1, buff);
            buff.remove(buff.size()-1);
        }
    }
}
