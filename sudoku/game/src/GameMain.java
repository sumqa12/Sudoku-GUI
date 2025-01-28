package sudoku.game.src;

import javafx.application.Application;
class GameMain{
	
	public static void main(String[] args) {
		new Sudoku();
		new VefineVoid();
		new Display("SUDOKU", Sudoku.board);
		new Display("Done", Sudoku.board);
		Application.launch(Watch.class);
	}

	
}