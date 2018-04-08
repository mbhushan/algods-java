import java.util.Arrays;

/**
 * Created by manib on 2/22/18.
 */
public class AnagramChecker {


    public static void main(String [] args) {
        String [] A = {"listen", "mani", "triangle", "world"};
        String [] B = {"silent", "naim", "integral", "lords"};

        AnagramChecker ac = new AnagramChecker();

        for (int i=0; i<A.length; i++) {
            boolean ans = ac.isAnagram(A[i], B[i]);
            System.out.println("A: " + A[i] + "\nB: " + B[i] + "\nIs Anagram: " + ans);
            System.out.println();
        }
    }

    public boolean isAnagram(String A, String B) {
        if (A == null || B == null) {
            return false;
        }

        if (A.length() != B.length()) {
            return false;
        }

        char [] arrA = A.toLowerCase().toCharArray();
        Arrays.sort(arrA);
        String sortedA = Arrays.toString(arrA);
        char [] arrB = B.toLowerCase().toCharArray();
        Arrays.sort(arrB);
        String sortedB = Arrays.toString(arrB);

        if (sortedA.equals(sortedB)) {
            return true;
        }

        return false;
    }
}

/*
********** OUTPUT ***********
A: listen
B: silent
Is Anagram: true

A: mani
B: naim
Is Anagram: true

A: triangle
B: integral
Is Anagram: true

A: world
B: lords
Is Anagram: false

 */
