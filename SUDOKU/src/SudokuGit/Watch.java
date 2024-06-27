package SudokuGit;

import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Watch extends Application{
	
	@Override
	public void start(Stage arg0) throws Exception {
		
		Stage stage = new Stage();
		
		final StopWatch stopwatch = new StopWatch();
		
		final Button start = new Button("Start");
		
		start.setOnAction(e ->{
			
			if(stopwatch.isActive()) {
				
				start.setText("Start");
				
			}else {
				
				start.setText("Finish");
			}
			
			stopwatch.action();
		});
		//リセットボタン
	    final Button reset = new Button("リセット");

	    //リセットボタン押下時のイベント
	    reset.setOnAction(e -> {

	      //ストップウォッチをリセット
	      stopwatch.reset();
	    });
		stage.setScene(new Scene(new HBox(stopwatch, new HBox(start, reset))));
		
		stage.show();
	}
	
	public static void watch(Duration d) {
		
	}
}
