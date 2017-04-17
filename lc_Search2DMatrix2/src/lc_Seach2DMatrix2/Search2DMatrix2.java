package lc_Seach2DMatrix2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

For example, consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.
 * @author manib
 *
 */
public class Search2DMatrix2 {
	
	public int ROWS = 0;
	public int COLS = 0;
	public static int [][] matrix;
	
	public static void main(String [] args) {
		Search2DMatrix2 sm = new Search2DMatrix2();
		sm.readMatrix();
		sm.printMatrix();
		
		int [] keys = {3, 90, 34};
		for (int i=0; i<keys.length; i++) {
            System.out.println("searching: " + keys[i] +" = " + sm.searchMatrix(matrix, keys[i]));
		}
	}
	
	public boolean searchMatrix(int [][] M, int target) {
		int m=matrix.length-1;
	    int n=matrix[0].length-1;
	 
	    int i=m; 
	    int j=0;
	 
	    while(i>=0 && j<=n){
	        if(target < matrix[i][j]){
	            i--;
	        }else if(target > matrix[i][j]){
	            j++;
	        }else{
	            return true;
	        }
	    }
	 
	    return false;
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
}
