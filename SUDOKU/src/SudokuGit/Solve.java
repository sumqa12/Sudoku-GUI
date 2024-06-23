package SudokuGit;

import java.util.HashSet;
import java.util.Set;

public class Solve {
	Set<String> hashSet = new HashSet<>();
	
	public boolean solve(String[][] board) {
		for(int row = 0;row < 9;row++) {
			for(int col = 0;col < 9;col++) {
				if(board[row][col].equals("0")) {
					int numToTry = 1;
					for(;numToTry <= 9; numToTry++) {
						if(safeIndex(board, row, col, Integer.toString(numToTry))) {
							hashSet.add(Integer.toString(numToTry));
						}
						if(hashSet.size() == 1) {
							board[row][col] = Integer.toString(numToTry);
							if(solve(board)) {
								hashSet.clear();
								return true;
							}else {
								board[row][col] = "0";
								hashSet.clear();
							}
						}
						hashSet.clear();
					}
					
					return false;
				}
			}
		}
		return true;
	}
	private boolean safeIndex(String[][] board, int row, int col, String num) {
		
		return 	!safeRowIndex(board, row, num) &&
				!safeColIndex(board, col, num) &&
				!safeBoxIndex(board, row - row % 3, col - col % 3, num);
	}
	private boolean safeRowIndex(String[][] board, int row, String num) {
		
		for(int col = 0;col < 9;col++) {
			if(board[row][col].equals(num)) {
				return true;
			}
		}
		return false;
	}
	private boolean safeColIndex(String[][] board, int col, String num) {
		
		for(int row = 0;row < 9;row++) {
			if(board[row][col].equals(num)) {
				return true;
			}
		}
		return false;

	}
	private boolean safeBoxIndex(String[][] board, int startRow, int startCol, String num) {
		
		for(int row = 0;row < 3;row++) {
			for(int col = 0;col < 3;col++) {
				if(board[row + startRow][col + startCol].equals(num)) {
					return true;
				}
			}
		}
		return false;
	}
}
