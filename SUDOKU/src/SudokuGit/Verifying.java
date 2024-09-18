package SudokuGit;
import java.util.ArrayList;
public class Verifying {
	private int boardCnt = Display.listRow * Display.listCol;
	public boolean Verifying(ArrayList<String> str1, ArrayList<String> str2) {
		showBoard(str2, 1);
		showBoard(str1, 2);
		for(int i = 0;i < boardCnt;i++) {
		if(!(str1.get(i).equals(str2.get(i)))) {
				System.out.println("Žc”OI");
				return false;
			}
		}
		System.out.println("‚¨‚ß‚Å‚Æ‚¤I");
		return true;
	}
	public void showBoard(ArrayList<String> board, int c) {
		if(c == 1) {
		System.out.println("‚ ‚È‚½");
		}else {
			System.out.println("“š‚¦");
		}
		int n = 1;
		for(int i = 0;i < boardCnt;i++) {
			System.out.print(board.get(i));
			if(board.get(i).equals(""))	System.out.print("   ");
			else	System.out.print("  ");
			
			if(n % Display.listCol == 0) {
				System.out.println();
			}
			n++;
		}
	}
	
	public void showBoard(String[][] board) {
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]);
				if(board[i][j].equals("")) System.out.print("   ");
				else	System.out.print("  ");
			}
			System.out.println();
		}
		
	}
	
}