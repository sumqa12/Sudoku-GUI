package SudokuGit;

public class DefineVoid {
	  public void defineVoid(String[][] board) {
		   	int x = new java.util.Random().nextInt(9);
		   	int y = new java.util.Random().nextInt(9);
		   	board[x][y] = "0";
		   	System.out.println("------------------------------");
			String[][] test = new String[9][9];
			for(int i = 0;i < 9;i++) {
				for(int j = 0;j < 9;j++) {
					test[i][j] = board[i][j];
				}
			}
			Solve s = new Solve();
			if(s.solve(test)) {
				for(int i = 0;i < 9;i++) {
					for(int j = 0;j < 9;j++) {
						System.out.print (board[i][j] + "  ");
					}
					System.out.println("");
				}
				System.out.println("------------------------------");
				for(int i = 0;i < 9;i++) {
					for(int j = 0;j < 9;j++) {
						System.out.print (board[i][j] + "  ");
					}
					System.out.println("");
				}
				int c = 0;
				for(int i = 0;i < 9;i++) {
					for(int j = 0;j < 9;j++) {
						if(board[i][j].equals("0")) {
							c++;
						}
					}
				}
				if(c < 40) {
					defineVoid(board);
				}else {
					for(int i = 0;i < 9;i++) {
						for(int j = 0;j < 9;j++) {
							board[i][j].equals(test[i][j]) ;
						}
					}
				}
			}else {
				System.out.println("false");
				for(int i = 0;i < 9;i++) {
					for(int j = 0;j < 9;j++) {
						board[i][j] = Sudoku.answer[i][j];
					}
				}
				defineVoid(board);
			}
		}
	 
}
