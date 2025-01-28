package sudoku.game.src;

public class Solve {
	int c = 0;
    
    public boolean solve(String[][] board) {
        
        int[] emptySpot = findEmptySpot(board);
        
        if (emptySpot == null) {
            return true; 
        }
        
        int row = emptySpot[0];
        int col = emptySpot[1];
        int cnt = 0, num = 0;
        for (int trynum = 1; trynum <= board.length; trynum++) {

            if (isSafe(board, row, col, Integer.toString(trynum))) {
            	c++;
            	cnt++;
                num = trynum;
            }
        }
        if(cnt == 1) {
        	board[row][col] = Integer.toString(num);
        	if(solve(board)) {
        		return true;
        	}
        }
        return false;
    }
    
    
    private boolean isSafe(String[][] board, int row, int col, String num) {
    	
        return !usedInRow(board, row, num) && !usedInCol(board, col, num) && !usedInBox(board, row - row % 3, col - col % 3, num);
    }
    
    private boolean usedInRow(String[][] board, int row, String num) {
        for (int col = 0; col < board.length; col++) {
            if (board[row][col].equals(num)) {
                return true;
            }
        }
        return false;
    }

    public static boolean usedInCol(String[][] board, int col, String num) {
            for (String[] board1 : board) {
                if (board1[col].equals(num)) {
                    return true;
                }
            }
        return false;
    }

    private boolean usedInBox(String[][] board, int boxStartRow, int boxStartCol, String num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + boxStartRow][col + boxStartCol].equals(num)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private int[] findEmptySpot(String[][] board) {
        int N = board.length;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col].equals("0")) {
                    return new int[] {row, col};
                }
            }
        }
        return null;
    }

}