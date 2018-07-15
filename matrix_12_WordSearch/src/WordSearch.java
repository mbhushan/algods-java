import java.util.Arrays;

/**
 LeetCode – Word Search (Java)

 Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell,
 where "adjacent" cells are those horizontally or vertically neighboring.
 The same letter cell may not be used more than once.


 For example, given board =

 [
 ["ABCE"],
 ["SFCS"],
 ["ADEE"]
 ]
 word = "ABCCED", -> returns true,
 word = "SEE", -> returns true,
 word = "ABCB", -> returns false.
 */

public class WordSearch {

    public static void main(String [] args) {
        WordSearch ws = new WordSearch();
        char [][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String [] words = {"ABCCED", "SEE", "ABCB"};

//        char [][] boggle = {
//                {'G','I','Z'},
//                {'U','E','K'},
//                {'Q','S','E'}
//        };
//
//        String [] target = {"GEEKS", "QUIZ"};



        for (int i=0; i<words.length; i++) {
            ws.checkWord(board, words[i].toCharArray());
        }

        System.out.println();

//        for (int i=0; i<target.length; i++) {
//            ws.checkWord(boggle, target[i].toCharArray());
//        }
    }

    public void checkWord(char [][] M, char [] word) {

        int row = M.length;
        int col = M[0].length;

        boolean found = false;
        boolean [][] visited = new boolean[row][col];

        System.out.println("Target word: " + Arrays.toString(word));

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (M[i][j] == word[0]) {
                    visited[i][j] = true;
                    found = checkWord(M, word, visited,1, i, j);
                    if (found) {
                        System.out.println("Found in matrix: " + found);
                        return;
                    }
                }
            }
        }
        System.out.println("Found in matrix? " + found);


    }

    private boolean checkWord(char [][] M, char [] word, boolean [][] visited, int index, int r, int c) {
        if (index == word.length) {
            return true;
        }


        char ch = word[index];

        int [][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        boolean found = false;

        for (int i=0; i<directions.length; i++) {
            int r1 = directions[i][0] + r;
            int c1 = directions[i][1] + c;
            if (r1 < 0 || r1 >= M.length || c1 < 0 || c1 >= M[0].length) {
                continue;
            }
            if (visited[r1][c1]) {
                continue;
            }
            if (M[r1][c1] == ch) {
                visited[r1][c1] = true;
                found =  checkWord(M, word, visited, index+1, r1, c1);
            }
        }
        return found;
    }
}
