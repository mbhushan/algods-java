/**
 * Created by manib on 6/27/18.

 ================================
 Given keys and frequency at which these keys are searched, how would you create
 binary search tree from these keys such that cost of searching is minimum.

 ================================
  INPUT / OUTPUT
 ================================
 DP Matrix:
 4 8 20 26
 0 2 10 16
 0 0 6 12
 0 0 0 3
 Minimum cost of the binary search tree: 26
 DP Parent Matrix:
 0 0 2 2
 0 0 2 2
 0 0 0 2
 0 0 0 0
 printing tree roots:
 start: 0; end: 3 ; 2
 start: 0; end: 1 ; 0
 start: 1; end: 1 ; 0
 start: 3; end: 3 ; 0

 */

public class OptimalBST {

    public static void main(String [] args) {
        OptimalBST obst = new OptimalBST();

        int [] keys = {10, 12, 16, 21};
        int [] frequency = {4, 2, 6, 3};

        obst.findOptimalCost(keys, frequency);
    }

    public void findOptimalCost(int [] A, int [] F) {
        int size = A.length;

        int [][] T = new int[size][size];
        int [][] P = new int[size][size];

        for (int i=0; i<size; i++) {
            T[i][i] = F[i];
        }

        for (int len=2; len<=size; len++ ) {
            for (int i=0; i<=size-len; i++) {
                int j = i + len -1;
                int freqSum = getFrequencySum(i, j, F);
                T[i][j] = Integer.MAX_VALUE;

                for (int k=i; k <= j; k++) {
                    int val = freqSum + (k-1 < i ? 0: T[i][k-1]) + (k+1 > j ? 0: T[k+1][j]);
                    if (val < T[i][j]) {
                        T[i][j] = val;
                        P[i][j] = k;
                    }
                }
            }
        }
        System.out.println("DP Matrix: ");
        printDPMatrix(T);
        System.out.println("Minimum cost of the binary search tree: " + T[0][size-1]);
        System.out.println("DP Parent Matrix: ");
        printDPMatrix(P);
        System.out.println("printing tree roots: ");
        printSolution(P, 0, size-1, P[0][size-1]);
    }

    public void printSolution(int [][] P, int start, int end, int root) {
        if (start == end) {
            System.out.println("start: " + start + "; end: " + end + " ; " + root + " ");
            return;
        }
        System.out.println("start: " + start + "; end: " + end + " ; " + root + " ");
        if (root-1 >= 0) {
            printSolution(P, start, root - 1, P[start][root - 1]);
        }
        if (root+1 < P.length) {
            printSolution(P, root + 1, end, P[root + 1][end]);
        }
    }

    public void printDPMatrix(int [][] M) {
        int row = M.length;
        int col = M[0].length;


        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (M[i][j] == Integer.MAX_VALUE) {
                    System.out.print("X" + " ");
                } else {
                    System.out.print(M[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private int getFrequencySum(int start, int end, int [] F) {
        int total = 0;
        for (int i=start; i<=end; i++) {
            total += F[i];
        }
        return total;
    }
}
