/**
 Submatrix Sum Queries
 Given a matrix of size M x N, there are large number of queries to find submatrix sums.
 Inputs to queries are left top and right bottom indexes of submatrix whose sum is to find out.

 How to preprocess the matrix so that submatrix sum queries can be performed in O(1) time.

 Example :

 tli :  Row number of top left of query submatrix
 tlj :  Column number of top left of query submatrix
 rbi :  Row number of bottom right of query submatrix
 rbj :  Column number of bottom right of query submatrix

 Input: mat[M][N] = {{1, 2, 3, 4, 6},
 {5, 3, 8, 1, 2},
 {4, 6, 7, 5, 5},
 {2, 4, 8, 9, 4} };
 Query1: tli = 0, tlj = 0, rbi = 1, rbj = 1
 Query2: tli = 2, tlj = 2, rbi = 3, rbj = 4
 Query3: tli = 1, tlj = 2, rbi = 3, rbj = 3;

 Output:
 Query1: 11  // Sum between (0, 0) and (1, 1)
 Query2: 38  // Sum between (2, 2) and (3, 4)
 Query3: 38  // Sum between (1, 2) and (3, 3)
 */

public class MatrixSumQueries {

    public static void main(String[] args) {
        MatrixSumQueries ms = new MatrixSumQueries();

        int [][] M = {
                {1, 2, 3, 4, 6},
                {5, 3, 8, 1, 2},
                {4, 6, 7, 5, 5},
                {2, 4, 8, 9, 4}
        };


        Query [] queries = {
                new Query(0, 0, 1, 1),
                new Query(2, 2, 3, 4),
                new Query(1, 2, 3, 3),
        };

        System.out.println("original matrix: ");
        ms.printMatrix(M);
        System.out.println();

        int [][] T = ms.buildHelperMatrix(M);
        System.out.println("print helper matrix: ");
        ms.printMatrix(T);
        System.out.println();

        for (int i=0; i<queries.length; i++) {
            int result = ms.queryResult(queries[i], T);
            System.out.println("Query: " + queries[i]);
            System.out.println("result: " + result);
        }


    }

    public int queryResult(Query query, int [][] T) {
        int r1 = query.topLeft+1;
        int c1 = query.topRight+1;

        int r2 = query.bottomLeft+1;
        int c2 = query.bottomRight+1;

        return (T[r2][c2] - T[r1-1][c2] - T[r2][c1-1] + T[r1-1][c1-1]);
    }




    public int [][] buildHelperMatrix(int [][]M) {
        if (M == null) {
            return null;
        }

        int row = M.length;
        int col = M[0].length;

        int [][] T = new int[row+1][col+1];

        for (int i=1; i<=row; i++) {
            for (int j=1; j<=col; j++) {
                T[i][j] = M[i-1][j-1] + T[i-1][j] + T[i][j-1] - T[i-1][j-1];
            }
        }

        return T;
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

class Query {
    int topLeft;
    int topRight;
    int bottomLeft;
    int bottomRight;

    Query(int tl, int tr, int bl, int br) {
        this.topLeft = tl;
        this.topRight = tr;
        this.bottomLeft = bl;
        this.bottomRight = br;
    }

    Query() {
        this.topLeft = 0;
        this.topRight = 0;
        this.bottomLeft = 0;
        this.bottomRight = 0;
    }

    @Override
    public String toString() {
        return "[ tl: " + this.topLeft + ", tr: " + this.topRight + "; bl: " + this.bottomLeft
                + ", br: " + this.bottomRight + "]";
    }
}
