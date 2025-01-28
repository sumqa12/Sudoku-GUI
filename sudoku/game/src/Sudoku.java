package sudoku.game.src;
import java.util.*;
public class Sudoku {

	/** 1����9�܂ł��i�[����boardList */
	  List<String> list = new ArrayList<String>();

	  /** �d���`�F�b�N�p��hashSet1 */
	  Set<String> hashSet1 = new HashSet<>();

	  /** �d���`�F�b�N�p��hashSet2 */
	  Set<String> hashSet2 = new HashSet<>();
	  
	  public static String[][] board = new String[9][9];
	  public static String[][] answer = new String[9][9];
	  
	  public Sudoku() {
		  MakeSudoku();
	  }
	  
	  /** ���Ƃ̍쐬 */
	  private void MakeSudoku() {
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
	    
	    for(int i = 0;i < Display.listRow;i++) {
	    	for(int j = 0;j < Display.listCol;j++) {
	    		answer[i][j] = board[i][j];
	    		
	    	}
	    }
	  }

}