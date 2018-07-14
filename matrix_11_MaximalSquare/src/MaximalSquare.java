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
    }
}
