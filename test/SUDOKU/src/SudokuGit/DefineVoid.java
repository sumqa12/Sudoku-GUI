package SudokuGit;

public class DefineVoid {
   	int a, b; 
   	String n;
   	
   	public DefineVoid() {
   		defineVoid(Sudoku.board);
   		new Verifying().showBoard(Sudoku.board);
   	}
	  private void defineVoid(String[][] board) {
		  
			String[][] test = new String[9][9];
			
			for(int i = 0;i < 9;i++) {
				for(int j = 0;j < 9;j++) {
					test[i][j] = board[i][j];
				}
			}
			
			Solve s = new Solve();
			
			if(s.solve(test)) {
				
				int x = new java.util.Random().nextInt(9);
			   	int y = new java.util.Random().nextInt(9);
			   	a = x;
				b = y;
				n = board[a][b];
			   	board[x][y] = "0";
				int c = 0;
				
				for(int i = 0;i < 9;i++) {
					for(int j = 0;j < 9;j++) {
						if(board[i][j].equals("0")) {
							c++;
						}
					}
				}
				
				if(c < 42) {
					defineVoid(board);
					
				}else {
					for(int i = 0;i < 9;i++) {
						for(int j = 0;j < 9;j++) {
							board[i][j].equals(test[i][j]);
						}
					}
				}
			}else {
				
				board[a][b] = n;
				defineVoid(board);
			}
		}
	 
}
