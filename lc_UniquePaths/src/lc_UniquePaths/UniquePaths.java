package lc_UniquePaths;

/**

A robot is located at the top-left corner of a m x n grid. It can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid.
How many possible unique paths are there?

 * @author manib
 *
 */
public class UniquePaths {
	
	
	public static void main(String [] args) {
		
	}
	
	public int uniquePaths(int row, int col) {
		if (row == 0 || col == 0) {
			return 0;
		}
		int [][] dp = new int[row][col];
		
		
		return dp[row-1][col-1];
	}

}
