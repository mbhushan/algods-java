/**
 Maximum size square sub-matrix with all 1s
 Given a binary matrix, find out the maximum size square sub-matrix with all 1s.

 For example, consider the below binary matrix.
 maximum-size-square-sub-matrix-with-all-1s

 Recommended: Please solve it on “PRACTICE” first, before moving on to the solution.




 Algorithm:
 Let the given binary matrix be M[R][C]. The idea of the algorithm is to construct an auxiliary size matrix S[][]
 in which each entry S[i][j] represents size of the square sub-matrix with all 1s including M[i][j] where M[i][j] is
 the rightmost and bottommost entry in sub-matrix.

 1) Construct a sum matrix S[R][C] for the given M[R][C].
 a)    Copy first row and first columns as it is from M[][] to S[][]
 b)    For other entries, use following expressions to construct S[][]
 If M[i][j] is 1 then
 S[i][j] = min(S[i][j-1], S[i-1][j], S[i-1][j-1]) + 1
 Else If M[i][j] is 0
            S[i][j] = 0
                    2) Find the maximum entry in S[R][C]
                    3) Using the value and coordinates of maximum entry in S[i], print
                    sub-matrix of M[][]
                    For the given M[R][C] in above example, constructed S[R][C] would be:

                    0  1  1  0  1
                    1  1  0  1  0
                    0  1  1  1  0
                    1  1  2  2  0
                    1  2  2  3  1
                    0  0  0  0  0
                    The value of maximum entry in above matrix is 3 and coordinates of the entry are (4, 3).
                    Using the maximum value and its coordinates, we can find out the required sub-matrix.

 =================
 INPUT / OUTPUT
 ==================
 input matrix:
 0 1 1 0 1
 1 1 0 1 0
 0 1 1 1 0
 1 1 1 1 0
 1 1 1 1 1
 0 0 0 0 0
 DP matrix:
 0 1 1 0 1
 1 1 0 1 0
 0 1 1 1 0
 1 1 2 2 0
 1 2 2 3 1
 0 0 0 0 0

 max square: 3
 */


public class MaximalSquare {

    public static void main(String[] args) {
        MaximalSquare ms = new MaximalSquare();

        int M[][] =  {
                {0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };

        System.out.println("input matrix: ");
        ms.printMatrix(M);

        ms.maxSquare(M);
        System.out.println();
    }

    public void maxSquare(int [][] M) {

        int row = M.length;
        int col = M[0].length;

        int maxSquare = 0;

        int [][] T = new int[row][col];

        T[0][0] = M[0][0];

        for (int i=1; i<col; i++) {
            T[0][i] = M[0][i];
        }

        for (int i=1; i<row; i++) {
            T[i][0] = M[i][0];
        }

        for (int i=1; i<row; i++) {
            for (int j=1; j<col; j++) {
                if (M[i][j] == 0) {
                    T[i][j] = 0;
                } else {
                    T[i][j] = Math.min(T[i - 1][j - 1], Math.min(T[i - 1][j], T[i][j - 1])) + 1;
                    if (T[i][j] > maxSquare) {
                        maxSquare = T[i][j];
                    }
                }
            }
        }

        System.out.println("DP matrix: ");
        printMatrix(T);

        System.out.println();
        System.out.println("max square: " + maxSquare);
    }

    public void printMatrix(int [][] M) {
        if (M == null) {
            return;
        }
        int row = M.length;
        int col = M[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }

}
