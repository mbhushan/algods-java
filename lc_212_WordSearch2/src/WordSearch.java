import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 https://leetcode.com/problems/word-search-ii/description/

 212. Word Search II

 Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

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
public class WordSearch {

    private Set<String> dict;

    WordSearch() {
        dict = new HashSet<>();
    }

    public static void main(String[] args) {
        WordSearch ws = new WordSearch();

        String [] words = {"oath","pea","eat","rain"};

        char [][] B = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };

       ws.findWords(B, words);

       // ws.testing();

    }

    public List<String> findWords(char[][] board, String[] words) {

        List<String> res = new ArrayList<>();
        if (words == null || words.length < 1) {
            return res;
        }
        for (String w: words) {
            findWords(board, w, res);
        }
        System.out.println("result: " + res);
        return res;
    }

    private void findWords(char [][] board, String w, List<String> res) {
        boolean [][] visited = new boolean[board.length][board[0].length];
        boolean flag = false;
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j] == w.charAt(0)) {
                    visited[i][j] = true;
                    flag = findWordsImproved(board, i, j, w.toCharArray(), 1, visited);
                    if (flag) {
                        System.out.println("found: " + w);
                        res.add(w);
                        return;
                    }
                    visited[i][j] = false;
                }
            }
        }
    }


    public void testing() {
        char [][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };

        String w = "oath";

        boolean [][] visited = new boolean[board.length][board[0].length];
        boolean flag = false;

        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j] == w.charAt(0)) {
                  //  visited[i][j] = true;
                    flag = findWords(board, i, j, w.toCharArray(), 0, visited);
                    if (flag) {
                        System.out.println("found: " + w);
                    }
                }
            }
        }
    }



    private boolean findWords(char [][] board, int r, int c, char [] A, int index, boolean [][] visited) {
        if (index == A.length) {
            return true;
        }

        char ch = A[index];

        if (board[r][c] != ch) {
            return false;
        }

       // System.out.println("matched: [" + r + ", " + c + "] " + ch);

        int [][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        boolean flag = false;

        visited[r][c] = true;

        for (int i=0; i<directions.length; i++) {

            int row = r + directions[i][0];
            int col = c + directions[i][1];
            if (row < 0 || col < 0 || row>=board.length || col >= board[0].length || visited[row][col]) {
                continue;
            }
            flag = findWords(board, row, col, A, index+1, visited);
            if (flag) {
                return true;
            }
        }
        visited[r][c] = false;
        return flag;
    }


    private boolean findWordsImproved(char [][] board, int r, int c, char [] A, int index, boolean [][] visited) {
        if (index == A.length) {
            return true;
        }

        char ch = A[index];
        // System.out.println("matched: [" + r + ", " + c + "] " + ch);

        int [][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        boolean flag = false;
        //visited[r][c] = true;

        for (int i=0; i<directions.length; i++) {

            int row = r + directions[i][0];
            int col = c + directions[i][1];
            if (row < 0 || col < 0 || row>=board.length || col >= board[0].length || visited[row][col]) {
                continue;
            }
            if (ch == board[row][col]) {
               // visited[row][col] = true;
                flag = findWords(board, row, col, A, index + 1, visited);
                if (flag) {
                    return true;
                }
               visited[row][col] = false;
            }
        }

        //visited[r][c] = true;

        return flag;
    }
}
