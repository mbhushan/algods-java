import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Boggle {

    private Set<String> dict;

    Boggle() {
        dict = new HashSet<>(Arrays.asList(new String[]{"GEEK", "FOR", "QUIZ", "GO", "USEI", "SEIKZ"}));
    }

    public static void main(String[] args) {
        char [][] M = {
                {'G','I','Z'},
                {'U','E','K'},
                {'Q','S','E'}
        };

        Boggle obj = new Boggle();
        obj.findWords(M);
    }

    public void findWords(char [][] M) {
        int row = M.length;
        int col = M[0].length;

        boolean [][] visited = new boolean[row][col];

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                search(M, i, j, visited, new StringBuffer());
            }
        }
    }

    public void search(char [][]M, int r, int c, boolean [][] visited, StringBuffer sb) {
        if (r < 0 || c < 0 || r >= M.length || c >= M[0].length) {
            return;
        }


        if (visited[r][c]) {
            return;
        }

        visited[r][c] = true;
        sb.append(M[r][c]);

        if (dict.contains(sb.toString())) {
            System.out.println(sb.toString());
        }

        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                    search(M, i + r, j + c, visited, sb);
            }
        }
        sb.deleteCharAt(sb.length()-1);
        visited[r][c] = false;
    }
}
