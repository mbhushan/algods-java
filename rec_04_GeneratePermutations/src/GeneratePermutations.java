import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Write a program to print all permutations of a given string
 A permutation, also called an “arrangement number” or “order,” is a rearrangement of the
 elements of an ordered list S into a one-to-one correspondence with S itself.
 A string of length n has n! permutation.

 Source: Mathword(http://mathworld.wolfram.com/Permutation.html)

 Below are the permutations of string ABC.

 ABC ACB BAC BCA CBA CAB

 ===============
 INPUT / OUTPUT
 ===============

 generating permutations for input:
 [1, 2, 3]
 generated permutations as:
 [1, 2, 3]
 [1, 3, 2]
 [2, 1, 3]
 [2, 3, 1]
 [3, 2, 1]
 [3, 1, 2]

 */

public class GeneratePermutations {

    public static void main(String[] args) {
        GeneratePermutations gp = new GeneratePermutations();

        int [] A = {1, 2, 3};

        System.out.println("generating permutations for input: ");
        System.out.println(Arrays.toString(A));
        System.out.println("generated permutations as: ");
        gp.permutation(A);
        System.out.println();
    }

    public void permutation(int [] A) {
        List<Integer> buff = new ArrayList<>();

        permutations(A, 0, buff);
    }

    private void permutations(int [] A, int index, List<Integer> buff) {
        if (buff.size() == A.length) {
            System.out.println(buff);
            return;
        }

        if (index >= A.length) {
            return;
        }

        for (int i=index; i<A.length; i++) {
            buff.add(A[i]);

            int tmp = A[i];
            A[i] = A[index];
            A[index] = tmp;
            //System.out.println("before: " + Arrays.toString(A));
            permutations(A, index+1, buff);
            tmp = A[i];
            A[i] = A[index];
            A[index] = tmp;

            //System.out.println("after: " + Arrays.toString(A));
            buff.remove(buff.size()-1);
        }
    }
}
