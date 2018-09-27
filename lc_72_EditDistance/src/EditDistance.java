/**
 72. Edit Distance
 https://leetcode.com/problems/edit-distance/description/

 Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

 You have the following 3 operations permitted on a word:

 Insert a character
 Delete a character
 Replace a character
 Example 1:

 Input: word1 = "horse", word2 = "ros"
 Output: 3
 Explanation:
 horse -> rorse (replace 'h' with 'r')
 rorse -> rose (remove 'r')
 rose -> ros (remove 'e')
 Example 2:

 Input: word1 = "intention", word2 = "execution"
 Output: 5
 Explanation:
 intention -> inention (remove 't')
 inention -> enention (replace 'i' with 'e')
 enention -> exention (replace 'n' with 'x')
 exention -> exection (replace 'n' with 'c')
 exection -> execution (insert 'u')
 */
public class EditDistance {

    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
    }

    /*
    if (S1[i] == S[j]) {
      T[i][j] = T[i-1][j-1];
    } else {
        T[i][j] = min(T[i-1][j], T[i][j-1], T[i-1][j-1])+1;
    }

    */
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) {
            return 0;
        }
        if (word1 == null) {
            return word2.length();
        }
        if (word2 == null) {
            return word1.length();
        }

        int w1Len = word1.length();
        int w2Len = word2.length();

        int [][] DP = new int[w1Len+1][w2Len+1];

        for (int i=1; i<=w2Len; i++) {
            DP[0][i] = i;
        }

        for (int i=1; i<=w1Len; i++) {
            DP[i][0] = i;
        }


        for (int i=1; i<=w1Len; i++) {
            for (int j=1; j<=w2Len; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    DP[i][j] = DP[i-1][j-1];
                } else {
                    DP[i][j] = Math.min(DP[i-1][j-1], Math.min(DP[i-1][j], DP[i][j-1])) + 1;
                }
            }
        }

        return DP[w1Len][w2Len];

    }
}
