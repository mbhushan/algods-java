package lc_ValidSudoku;

/**
 * Determine if a Sudoku is valid. The Sudoku board could be partially filled, 
 * where empty cells are filled with the character '.'.
 * @author manib
 *
 */
public class ValidSudoku {

	public boolean isValidSudoku (String [] input) {
		
		if (input.length != 9) {
			return false;
		}
		
		int n = 9;
		
		char [][] board = new char[n][n];
		
		for (int i=0; i<input.length; i++) {
			char [] row = input[i].toCharArray();
			for (int j=0; j<row.length; j++) {
				board[i][j] = row[j];
			}
		}
		
		//check each row
		for (int i=0; i<n; i++) {
			boolean [] flags = new boolean[n];
			for (int j=0; j<n; j++) {
				if (board[i][j] != '.') {
					if (flags[(int) (board[i][j] - '1')]) {
						return false;
					}
					flags[(int) (board[i][j] - '1')] = true;
				}
			}
		}
		
		//check each column
		for (int i=0; i<n; i++) {
			boolean [] flags = new boolean[n];
			for (int j=0; j<n; j++) {
				if (board[j][i] != '.') {
					if (flags[(int) (board[j][i] - '1')]) {
						return false;
					}
					flags[(int) (board[j][i] - '1')] = true;
				}
			}
		}
		
		//check each 3*3 matrix
		for (int i=0; i<n; i+=3) {
			for (int j=0; j<n; j+=3) {
			boolean [] flags = new boolean[n];
				for (int k=0; k<3; k++) {
					for (int l=0; l<3; l++) {
						if (board[i+k][l+j] != '.') {
							if (flags[(int) (board[i+k][j+l] - '1')]) {
								return false;
							}
							flags[(int) (board[i+k][j+l] - '1')] = true;
						}
					}
				}
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		ValidSudoku SV = new ValidSudoku();
		
		String [] board = {"53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.", "...419..5", "....8..79"};
		System.out.println("is valid sudoku: " + SV.isValidSudoku(board));
		
	}
}
