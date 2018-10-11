import java.util.HashSet;
import java.util.Set;

/**
 694. Number of Distinct Islands
 https://leetcode.com/problems/number-of-distinct-islands/description/

 Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

 Count the number of distinct islands. An island is considered to be the same as another if
 and only if one island can be translated (and not rotated or reflected) to equal the other.

 Example 1:
 11000
 11000
 00011
 00011
 Given the above grid map, return 1.
 Example 2:
 11011
 10000
 00001
 11011
 Given the above grid map, return 3.

 Notice that:
 11
 1
 and
 1
 11
 are considered different island shapes, because we do not consider reflection / rotation.
 Note: The length of each dimension in the given grid does not exceed 50.

 */
public class DistinctIslands {

    public static void main(String[] args) {

    }

    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();

        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    StringBuffer sb = new StringBuffer();
                    helper(grid, i, j, 'O', sb);
                    grid[i][j] = 0;
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    private void helper(int [][] M, int r, int c, char ch, StringBuffer sb) {
        if (r < 0 || c < 0 || r >= M.length || c >= M[0].length || M[r][c] == 0) {
            return;
        }


        int [][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        char [] arr = {'L', 'R', 'U', 'D'};

        sb.append(ch);
        M[r][c] = 0;
        for (int i=0; i<directions.length; i++) {
            int row = r + directions[i][0];
            int col = c + directions[i][1];
            helper(M, row, col, arr[i], sb);
        }

        sb.append('B');
    }


}
