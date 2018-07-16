import java.util.Arrays;

/**
 LeetCode – Word Search II (Java)

 Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell,
 where "adjacent" cells are those horizontally or vertically neighboring.
 The same letter cell may not be used more than once in a word.


 For example, given words = ["oath","pea","eat","rain"] and board =
 [
 ['o','a','a','n'],
 ['e','t','a','e'],
 ['i','h','k','r'],
 ['i','f','l','v']
 ]
 Return ["eat","oath"].
 */

public class WordSearch2 {

    public static void main(String [] args) {
        WordSearch2 ws = new WordSearch2();

        char [][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };

        String [] dict = {"oath","pea","eat","rain"};

        for (int i=0; i<dict.length; i++) {
            ws.wordSearch(board, dict[i].toCharArray());
        }
    }

    public void wordSearch(char [][] board, char [] word) {

        int row = board.length;
        int col = board[0].length;

        boolean [][] visited = new boolean[row][col];
        boolean found = false;

        System.out.println("word: " + Arrays.toString(word));

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (board[i][j] == word[0]) {
                    visited[i][j] = true;
                    found = wordSearch(board, word, visited, i, j, 1);
                    visited[i][j] = false;
                }

                if (found) {
                    System.out.println("found in matrix: " + found);
                    return;
                }
            }
        }
        System.out.println("found in matrix: " + found);
    }

    private boolean wordSearch(char [][] board, char [] word, boolean [][] visited, int r, int c, int index) {
        if (index == word.length) {
            return true;
        }

        char ch = word[index];

        int [][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        boolean found = false;

        for (int i=0; i<directions.length; i++) {
            int r1 = directions[i][0] + r;
            int c1 = directions[i][1] + c;

            if (r1 < 0 || r1 >= board.length || c1 < 0 || c1 >= board[0].length) {
                continue;
            }

            if (visited[r1][c1]) {
                continue;
            }

            if (board[r1][c1] == ch) {
                visited[r1][c1] = true;
                found = wordSearch(board, word, visited, r1, c1, index+1);
            }
        }

        return found;

    }
}
