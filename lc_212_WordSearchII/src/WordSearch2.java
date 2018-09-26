import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 212. Word Search II
 https://leetcode.com/problems/word-search-ii/description/

 Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells
 are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 Example:

 Input:
 words = ["oath","pea","eat","rain"] and board =
 [
 ['o','a','a','n'],
 ['e','t','a','e'],
 ['i','h','k','r'],
 ['i','f','l','v']
 ]

 Output: ["eat","oath"]
 Note:
 You may assume that all inputs are consist of lowercase letters a-z.



 */


class WordSearch2 {

    public static void main(String[] args) {
        WordSearch2 ws = new WordSearch2();

        String [] words = {"oath","pea","eat","rain"};

        char [][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };

        System.out.println("words are: " + ws.findWords(board, words));

    }

    public List<String> findWords(char[][] board, String[] words) {
        //List<String> result = new ArrayList<>();
        Set<String> result = new HashSet<>();

        for (String w: words) {
            if (findWords(board, w)) {
                result.add(w);
            }
        }

        return new ArrayList<>(result);

    }

    private boolean findWords(char [][] board, String word) {

        int row = board.length;
        int col = board[0].length;
        boolean [][] visited = new boolean[row][col];

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean flag = search(board, i, j, word, 0, visited);
                    if (flag) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean search(char [][] board, int r, int c, String word, int index, boolean [][] visited) {

        if (index == word.length()) {
            return true;
        }

        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return false;
        }

        if (visited[r][c]) {
            return false;
        }



        boolean flag = false;

        if (board[r][c] == word.charAt(index)) {
            visited[r][c] = true;
            int [][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

            for (int i=0; i<directions.length; i++) {
                int row = r + directions[i][0];
                int col = c + directions[i][1];

                flag = search(board, row, col, word, index+1, visited);
                if (flag) {
                    return true;
                }
            }

            visited[r][c] = false;
        }

        return flag;

    }
}