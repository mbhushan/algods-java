/**
 * Created by manib on 6/29/18.

 =================================
 Given two strings A & B and operations edit, delete and add, how many minimum
 operations would it take to convert string B to string A.

 */
public class EditDistance {

    public static void main(String [] args) {
        EditDistance ed = new EditDistance();

        String B = "COMMIT";
        String A = "COMPUTE";
        ed.findMinEditDistance(A, B);
    }

    public void findMinEditDistance(String S1, String S2) {
        if (S1 == null || S2 == null) {
            return;
        }

        int s1Len = S1.length();
        int s2Len = S2.length();

        int [][] T = new int[s1Len+1][s2Len+1];

        for (int i=0; i<=s2Len; i++) {
            T[0][i] = i;
        }

        for (int i=0; i<=s1Len; i++) {
            T[i][0] = i;
        }

        for (int i=1; i<=s1Len; i++) {
            for (int j=1; j<=s2Len; j++) {
                if (S1.charAt(i-1) == S2.charAt(j-1)) {
                    T[i][j] = T[i-1][j-1];
                } else {
                    T[i][j] = Math.min(T[i-1][j-1], Math.min(T[i-1][j], T[i][j-1])) + 1;
                }
            }
        }

        //print DP matrix
        System.out.println("DP Matrix: ");
        printDPMatrix(T);
    }

    public void printDPMatrix(int [][] M) {
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
