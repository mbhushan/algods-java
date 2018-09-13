/**
 79. Word Search

 https://leetcode.com/problems/word-search/description/

 Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell,
 where "adjacent" cells are those horizontally or vertically neighboring.
 The same letter cell may not be used more than once.

 Example:

 board =
 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]

 Given word = "ABCCED", return true.
 Given word = "SEE", return true.
 Given word = "ABCB", return false.
 */

public class WordSearch {

    public static void main(String[] args) {
        WordSearch ws = new WordSearch();

        char [][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        char [][] board1 = {
                {'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}
        };

        String [] words = {"ABCCED", "SEE", "ABCB"};

        for (int i=0; i<words.length; i++) {
            System.out.println("word: " + words[i]);
            boolean ans = ws.exist(board, words[i]);
            System.out.println("ans: " + ans) ;
            System.out.println();
        }

        System.out.println("AAB ans: " + ws.exist(board1, "AAB"));

    }

    public boolean exist(char[][] board, String word) {

        boolean flag = false;
        boolean [][] visited = new boolean[board.length][board[0].length];

       for (int i=0; i<board.length; i++) {
           for (int j=0; j<board[0].length; j++) {
               if (board[i][j] == word.charAt(0)) {
                   visited[i][j] = true;
                   flag = findWord(board, i, j, word.toCharArray(), 0, visited);
                   if (flag) {
                       return flag;
                   }
               }
           }
       }
        return flag;
    }

    private boolean findWord(char [][] board, int row, int col, char [] A, int index, boolean [][] visited) {
        if (index == A.length) {
            return true;
        }


        char ch = A[index];

        if (board[row][col] != ch) {
            return false;
        }

        visited[row][col] = true;

        int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        boolean flag = false;

        for (int i = 0; i < directions.length; i++) {
            int r = directions[i][0] + row;
            int c = directions[i][1] + col;
            if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || visited[r][c]) {
                continue;
            }

                flag = findWord(board, r, c, A, index + 1, visited);
                if (flag) {
                    return true;
                }


        }
        visited[row][col] = false;

        return flag;
    }


}
