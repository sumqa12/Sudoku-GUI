package SudokuGit;
import java.util.*;
public class Sudoku {

	/** 1から9までを格納するboardList */
	  List<String> list = new ArrayList<String>();

	  /** 重複チェック用のhashSet1 */
	  Set<String> hashSet1 = new HashSet<>();

	  /** 重複チェック用のhashSet2 */
	  Set<String> hashSet2 = new HashSet<>();
	  
	  public static String[][] board = new String[9][9];
	  public static String[][] answer = new String[9][9];

	  /** 数独の作成 */
	  public void MakeSudoku() {
	    for (int i = 1; i <= 9; i++) {
	      list.add(Integer.toString(i));
	    }
	    Collections.shuffle(list);

	    for (int i = 0, j; i <= 8;) {
	      int u = 2, v = 2;
	      if (i == 5) {
	        u = 5;
	      }

	      for (j = 0; j <= 8;) {
	        board[i][j] = list.get(j);
	        for (int x = 0; x < i; x++) {
	          hashSet1.add(board[x][j]);
	        }
	        if (hashSet1.contains(board[i][j])) {
	          hashSet1.clear();
	          break;
	        }
	        if (i == u && j == v) {
	          hashSet2.add(board[u - 2][v - 2]);
	          hashSet2.add(board[u - 2][v - 1]);
	          hashSet2.add(board[u - 2][v]);
	          hashSet2.add(board[u - 1][v - 2]);
	          hashSet2.add(board[u - 1][v - 1]);
	          hashSet2.add(board[u - 1][v]);
	          hashSet2.add(board[u][v - 2]);
	          hashSet2.add(board[u][v - 1]);
	          hashSet2.add(board[u][v]);
	          if (hashSet2.size() != 9) {
	            hashSet2.clear();
	            i -= 2;
	            j = 0;
	            break;
	          }
	          v = 5;
	         
	        }
	        j++;

	        hashSet1.clear();
	        hashSet2.clear();
	      }
	      if (j == 9) {
	        i++;
	      }

	      Collections.shuffle(list);
	    }
	    
	    for(int i = 0;i < 9;i++) {
	    	for(int j = 0;j < 9;j++) {
	    		answer[i][j] = board[i][j];
	    		
	    	}
	    }
	  }

}
