package SudokuGit;
import javafx.application.Application;
class GameMain{
	
	public static void main(String[] args) {
		new Sudoku();
		new DefineVoid();
		new Display("SUDOKU", Sudoku.board);
		new Display("Done", Sudoku.board);
		Application.launch(Watch.class);
	}

	
}


