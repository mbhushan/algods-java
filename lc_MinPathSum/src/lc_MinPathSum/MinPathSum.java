package lc_MinPathSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a m x n grid filled with non-negative numbers, 
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * @author manib
 *
 *Input/Output:
3
4
1 3 5 7
10 11 16 20
23 30 34 50
1 3 5 7 
10 11 16 20 
23 30 34 50 
min path sum: 86
 */
public class MinPathSum {
	
	public int ROWS = 0;
	public int COLS = 0;
	public static int [][] matrix;
	
	public static void main(String[] args) {
		MinPathSum mps = new MinPathSum();
		mps.readMatrix();
		mps.printMatrix();
		
		System.out.println("min path sum: " + mps.minPathSum(matrix));
		
	}
	
	public void printMatrix() {
		for (int i=0; i<ROWS; i++) {
			for (int j=0; j<COLS; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void readMatrix() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String data = br.readLine().trim();
			ROWS = Integer.valueOf(data);
			data = br.readLine().trim();
			COLS = Integer.valueOf(data);
			matrix = new int[ROWS][COLS];
			for (int i=0; i<ROWS; i++) {
				String [] values = br.readLine().trim().split(" ");
				for (int j=0; j<COLS; j++) {
					matrix[i][j] = Integer.parseInt(values[j]);
				}
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int minPathSumRec(int[][] grid) {
	    return dfs(0,0,grid);
	}
	 
	public int dfs(int i, int j, int[][] grid){
	    if(i==grid.length-1 && j==grid[0].length-1){
	        return grid[i][j];
	    }
	 
	    if(i<grid.length-1 && j<grid[0].length-1){
	        int r1 = grid[i][j] + dfs(i+1, j, grid);
	        int r2 = grid[i][j] + dfs(i, j+1, grid);
	        return Math.min(r1,r2);
	    }
	 
	    if(i<grid.length-1){
	        return grid[i][j] + dfs(i+1, j, grid);
	    }
	 
	    if(j<grid[0].length-1){
	        return grid[i][j] + dfs(i, j+1, grid);
	    }
	 
	    return 0;
	}
	
	
	public int minPathSum(int[][] grid) {
	    if(grid == null || grid.length==0)
	        return 0;
	 
	    int m = grid.length;
	    int n = grid[0].length;
	 
	    int[][] dp = new int[m][n];
	    dp[0][0] = grid[0][0];    
	 
	    // initialize top row
	    for(int i=1; i<n; i++){
	        dp[0][i] = dp[0][i-1] + grid[0][i];
	    }
	 
	    // initialize left column
	    for(int j=1; j<m; j++){
	        dp[j][0] = dp[j-1][0] + grid[j][0];
	    }
	 
	    // fill up the dp table
	    for(int i=1; i<m; i++){
	        for(int j=1; j<n; j++){
	            if(dp[i-1][j] > dp[i][j-1]){
	                dp[i][j] = dp[i][j-1] + grid[i][j];
	            }else{
	                dp[i][j] = dp[i-1][j] + grid[i][j];
	            }
	        }
	    }
	 
	    return dp[m-1][n-1];
	}

}
