/**
 Given an array p[] which represents the chain of matrices such that the ith matrix Ai is of
 dimension p[i-1] x p[i]. We need to write a function MatrixChainOrder() that should return the minimum
 number of multiplications needed to multiply the chain.

 Input: p[] = {40, 20, 30, 10, 30}
 Output: 26000
 There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30.
 Let the input 4 matrices be A, B, C and D.  The minimum number of
 multiplications are obtained by putting parenthesis in following way
 (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30

 Input: p[] = {10, 20, 30, 40, 30}
 Output: 30000
 There are 4 matrices of dimensions 10x20, 20x30, 30x40 and 40x30.
 Let the input 4 matrices be A, B, C and D.  The minimum number of
 multiplications are obtained by putting parenthesis in following way
 ((AB)C)D --> 10*20*30 + 10*30*40 + 10*40*30

 Input: p[] = {10, 20, 30}
 Output: 6000
 There are only two matrices of dimensions 10x20 and 20x30. So there
 is only one way to multiply the matrices, cost of which is 10*20*30

 */

public class MatrixChainMultiplication {

    public static void main(String[] args) {
        MatrixChainMultiplication obj = new MatrixChainMultiplication();
        int [] M = {2, 3, 6, 4, 5};
        obj.findMatrixMultiplyCost(M);

    }

    public void findMatrixMultiplyCost(int [] M) {

        int size = M.length;
        /* m[i,j] = Minimum number of scalar multiplications needed
	       to compute the matrix A[i]A[i+1]...A[j] = A[i..j] where
	       dimension of A[i] is p[i-1] x p[i] */
        int [][] T = new int[size][size];
        int value = 0;
        for (int len=2; len<size; len++) {
            for (int i=0; i<size-len; i++) {
                int j = i + len;
                T[i][j] = Integer.MAX_VALUE;
                for (int k=i+1; k<j; k++) {
                    T[i][j] = Math.min(T[i][j], T[i][k] + T[k][j] + M[i] * M[k] * M[j]);
                }
            }
        }

        printDPMatrix(T);
        System.out.println("min matrix chain multiplication cost: " + T[0][size-1]);
    }

    private void printDPMatrix(int [][] T) {
        if (T == null) {
            return;
        }
        System.out.println("DP Matrix: ");
        for (int i=0; i<T.length; i++) {
            for (int j=0; j < T[0].length; j++) {
                System.out.print(T[i][j] + " ");
            }
            System.out.println();
        }
    }
}
