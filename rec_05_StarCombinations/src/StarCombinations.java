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

 */
public class StarCombinations {

    public static void main(String [] args) {
        StarCombinations sc = new StarCombinations();

        int [] A = {1, 2, 3};

        sc.genCombinations(A);
    }

    public void genCombinations(int [] A) {

    }

    private void genCombinations(int [] A, int r, List<Integer> buff) {

    }
}
