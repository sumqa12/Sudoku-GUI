package SudokuGit;
public class GameMain {
	public static void main(String[] args) {
		new Sudoku().MakeSudoku();
		new DefineVoid().defineVoid(Sudoku.board);
		new Display("SUDOKU", Sudoku.board);
		new Display("Done", Sudoku.board);
	}

}
