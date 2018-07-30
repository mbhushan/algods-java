/**
 Print all combinations of balanced parentheses
 Write a function to generate all possible n pairs of balanced parentheses.

 Examples:

 Input : n=1
 Output: {}

 Input : n=2
 Output:
 {}{}
 {{}}

 =====================
 INPUT / OUTPUT
 =====================
 Printing all combinations of balanced parentheses:
 ((()))
 (()())
 (())()
 ()(())
 ()()()

 */
public class BracketCombinations {

    public static void main(String [] args) {
        BracketCombinations bc = new BracketCombinations();

        int n = 3;
        bc.generateCombinations(n);

    }

    public void generateCombinations(int n) {
       char [] A = new char[n*2];
        System.out.println("Printing all combinations of balanced parentheses:");
       generateCombinations(A, 0, 0, 0, n);

    }

    private void generateCombinations(char [] A, int left, int right, int index, int n) {
        if (index >= A.length) {
            System.out.println(String.valueOf(A));
            return;
        }

        if (left < n) {
            A[index] = '(';
            generateCombinations(A, left+1, right, index+1, n);
        }
        if (right < left) {
            A[index] = ')';
            generateCombinations(A, left, right+1, index+1, n);
        }
    }
}
