package SudokuGit;

public class Solve {
	int c = 0;
	// solve���\�b�h
    public boolean solve(String[][] board) {
        
        //�l����(0)�̃A�h���X���擾
        int[] emptySpot = findEmptySpot(board);
        
        //�������ׂĖ��܂�����
        if (emptySpot == null) {
            return true; // �������I
        }
        
        //�擾�����A�h���X�����ꂼ��ϐ��ɂɓ����
        int row = emptySpot[0];
        int col = emptySpot[1];
        int cnt = 0, num = 0;
        for (int trynum = 1; trynum <= 9 ; trynum++) {
        	//����num�����݂̗�A�s�A3*3�̃O���b�h�ɂȂ�������
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
    
    
    //�d�����Ȃ����ǂ���
    private boolean isSafe(String[][] board, int row, int col, String num) {
    	
        return !usedInRow(board, row, num) && !usedInCol(board, col, num) && !usedInBox(board, row - row % 3, col - col % 3, num);
    }
    
    //���݂̍s��num�����邩�ǂ���
    private boolean usedInRow(String[][] board, int row, String num) {
        for (int col = 0; col < board.length; col++) {
            if (board[row][col].equals(num)) {
                return true;
            }
        }
        return false;
    }

    //���݂̗��num�����邩�ǂ���
    public static boolean usedInCol(String[][] board, int col, String num) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][col].equals(num)) {
                return true;
            }
        }
        return false;
    }

    //���݂�3*3�̃O���b�h��num�����邩�ǂ���
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
    
    //board�̒l����(0)�̏ꏊ��T��
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
