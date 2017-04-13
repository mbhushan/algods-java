package ic_Search2DMatrix1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author manib
 *
 *
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has properties:
1) Integers in each row are sorted from left to right. 2) The first integer of each row is greater than the last integer of the previous row.
For example, consider the following matrix:
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
Given target = 3, return true.
======= INPUT =======
3
4
1 3 5 7
10 11 16 20
23 30 34 50
 */
public class Search2DMatrix1 {

	public int ROWS = 0;
	public int COLS = 0;
	public static int [][] matrix;
	
	public static void main(String [] args) {
		Search2DMatrix1 sm = new Search2DMatrix1();
		sm.readMatrix();
		sm.printMatrix();
		
		int [] keys = {3, 90, 34};
		for (int i=0; i<keys.length; i++) {
            System.out.println("searching: " + keys[i] +" = " + sm.searchMatrix(matrix, keys[i]));
		}
	}
	
	 public boolean searchMatrix(int[][] M, int target) {
	        if(M==null || M.length==0 || M[0].length==0) 
	            return false;
	 
	        int m = M.length;
	        int n = M[0].length;
	 
	        int start = 0;
	        int end = m*n-1;
	 
	        while(start<=end){
	            int mid= start + (end - start) / 2;
	            int midX=mid/n;
	            int midY=mid%n;
	 
	            if(M[midX][midY]==target) 
	                return true;
	 
	            if(M[midX][midY]<target){
	                start=mid+1;
	            }else{
	                end=mid-1;
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
