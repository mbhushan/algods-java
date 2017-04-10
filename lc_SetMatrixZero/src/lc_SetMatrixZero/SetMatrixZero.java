package lc_SetMatrixZero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
Set Matrix Zeroes (Java)
Given a m * n matrix, if an element is 0, set its entire row and column to 0.
Do it in place.
* @author manib
 *
 */
public class SetMatrixZero {
	
	public int ROWS = 0;
	public int COLS = 0;
	public int [][] matrix;
			
	
	public static void main(String [] args) {
		SetMatrixZero smz = new SetMatrixZero();
		
		smz.readMatrix();
		//smz.printMatrix();
		smz.setZeros();
	}
	
	
	public void setZeros( ) {
		boolean firstRowZero = false;
		boolean firstColZero = false;
		
		
		//check if first col is to be set zero or not
		for (int i=0; i<matrix.length; i++) {
			if (matrix[i][0] == 0) {
				firstColZero = true;
				break;
			}
		}
		
		//check if the first row is to be set zero or not
		for (int i=0; i<matrix[0].length; i++) {
			if (matrix[0][i] == 0) {
				firstRowZero = false;
				break;
			}
		}
		
		//do markings on first row and col if the cell is zero
		for (int i=1; i<ROWS; i++) {
			for (int j=1; j<COLS; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		//use the previous markings to set the cell values to zero
		for (int i=1; i<ROWS; i++) {
			for (int j=1; j<COLS; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		
		if (firstRowZero) {
			for (int i=0; i<COLS; i++) {
				matrix[0][i] = 0; 
			}
		}
		
		if (firstColZero) {
			for (int i=0; i<ROWS; i++) {
				matrix[i][0] = 0;
			}
		}
	
		System.out.println("after matrix operation:");
		printMatrix();
		
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
