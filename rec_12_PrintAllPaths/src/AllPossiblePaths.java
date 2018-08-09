import java.util.ArrayList;
import java.util.List;

/**
 Print all possible paths from top left to bottom right of a mXn matrix
 The problem is to print all the possible paths from top left to bottom right
 of a mXn matrix with the constraints that from each cell you can either move only to right or down.

 Examples :

 Input : 1 2 3
 4 5 6
 Output : 1 4 5 6
 1 2 5 6
 1 2 3 6

 Input : 1 2
 3 4
 Output : 1 2 4
 1 3 4

 ===============
 INPUT / OUTPUT
 ===============
 input matrix:
 1 2 3
 4 5 6

 all possible paths:
 [1, 4, 5, 6]
 [1, 2, 5, 6]
 [1, 2, 3, 6]

 */

public class AllPossiblePaths {

    public static void main(String[] args) {
        AllPossiblePaths ap = new AllPossiblePaths();

        int [][] M = {
                {1, 2, 3},
                {4, 5, 6}
        };

        ap.printAllPossiblePaths(M);
    }

    public void printAllPossiblePaths(int [][] M) {

        System.out.println("input matrix: ");
        printMatrix(M);
        System.out.println();

        List<Integer> path = new ArrayList<>();

        System.out.println("all possible paths: ");
        printPaths(M, 0, 0, path);


    }

    private void printPaths(int [][] M, int row, int col, List<Integer> path) {
        if (row == M.length-1 && col == M[0].length-1) {
            path.add(M[row][col]);
            System.out.println(path);
            path.remove(path.size()-1);
            return;
        }

        if (row >= M.length || col >= M[0].length) {
            return;
        }

        path.add(M[row][col]);

        printPaths(M,row+1, col, path);
        printPaths(M, row, col+1, path);

        path.remove(path.size()-1);

    }


    public void printMatrix(int [][] M) {
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
