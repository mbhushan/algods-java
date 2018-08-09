/**
 Check if edit distance between two strings is one
 An edit between two strings is one of the following changes.

 Add a character
 Delete a character
 Change a character
 Given two string s1 and s2, find if s1 can be converted to s2 with exactly one edit.
 Expected time complexity is O(m+n) where m and n are lengths of two strings.

 Examples:

 Input:  s1 = "geeks", s2 = "geek"
 Output: yes
 Number of edits is 1

 Input:  s1 = "geeks", s2 = "geeks"
 Output: no
 Number of edits is 0

 Input:  s1 = "geaks", s2 = "geeks"
 Output: yes
 Number of edits is 1

 Input:  s1 = "peaks", s2 = "geeks"
 Output: no
 Number of edits is 2

 ======================
 INPUT / OUTPUT
 ======================
 first string: geeks
 second string: geek
 DP Matrix:
 0 1 2 3 4 5
 1 0 1 2 3 4
 2 1 0 1 2 3
 3 2 1 0 1 2
 4 3 2 1 0 1
 is one edit distance: true

 first string: geeks
 second string: geeks
 DP Matrix:
 0 1 2 3 4 5
 1 0 1 2 3 4
 2 1 0 1 2 3
 3 2 1 0 1 2
 4 3 2 1 0 1
 5 4 3 2 1 0
 is one edit distance: false

 first string: geaks
 second string: geeks
 DP Matrix:
 0 1 2 3 4 5
 1 0 1 2 3 4
 2 1 0 1 2 3
 3 2 1 1 2 3
 4 3 2 2 1 2
 5 4 3 3 2 1
 is one edit distance: true

 first string: peaks
 second string: geeks
 DP Matrix:
 0 1 2 3 4 5
 1 1 2 3 4 5
 2 2 1 2 3 4
 3 3 2 2 3 4
 4 4 3 3 2 3
 5 5 4 4 3 2
 is one edit distance: false

 */

public class OneEditDistance {

    public static void main(String[] args) {
        OneEditDistance od =  new OneEditDistance();

        String [] S1 = {
          "geeks", "geeks", "geaks", "peaks"
        };

        String [] S2 = {
                "geek", "geeks", "geeks", "geeks"
        };

        for (int i=0; i<S1.length; i++) {
            System.out.println("first string: " + S1[i]);
            System.out.println("second string: " + S2[i]);

            boolean ans = od.isOneEditDistance(S1[i], S2[i], 1);
            System.out.println("is one edit distance: " + ans);
            System.out.println();
        }
    }

    public boolean isOneEditDistance(String x, String y, int k) {
        char [] A = x.toCharArray();
        char [] B = y.toCharArray();

        int [][] T = new int[B.length+1][A.length+1];

        T[0][0] = 0;
        for (int i=1; i<=A.length; i++) {
            T[0][i] = 1 + T[0][i-1];
        }

        for (int j=1; j<=B.length; j++) {
            T[j][0] = 1 + T[0][j-1];
        }

        for (int i=1; i<=B.length; i++) {
            for (int j=1; j<=A.length; j++) {
                if (B[i-1] == A[j-1]) {
                    T[i][j] = T[i-1][j-1];
                } else {
                    T[i][j] = Math.min(T[i-1][j-1], Math.min(T[i-1][j], T[i][j-1])) + 1;
                }
            }
        }

        System.out.println("DP Matrix: ");
        printMatrix(T);

        return T[B.length][A.length] == k;
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
