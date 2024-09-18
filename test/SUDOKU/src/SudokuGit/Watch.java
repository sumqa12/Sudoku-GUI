package SudokuGit;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Watch extends Application{
	public static Button start;
	public static StopWatch stopwatch;
	
	@Override
	public void start(Stage arg0) throws Exception {
		
		Stage stage = new Stage();
		
		stopwatch = new StopWatch();
		
		start = new Button("Start");
		
		start.setOnAction(e ->{
			if(stopwatch.isActive()) {
				
				start.setText("Start");
				start.fontProperty();
			}else {
				
				start.setText("Stop");
			}
			
			stopwatch.action();
		});
		//リセットボタン
	    final Button reset = new Button("Reset");

	    //リセットボタン押下時のイベント
	    reset.setOnAction(e -> {

	      //ストップウォッチをリセット
	      stopwatch.reset();
	    });
	    start.setFont(new Font("Arial", 40));
	    reset.setFont(new Font("Arial", 40));
	    start.setPrefWidth(400);
	    start.setPrefHeight(60);
	    reset.setPrefWidth(400);
	    reset.setPrefHeight(60);
	    stopwatch.setPrefHeight(65);
	    BorderPane pane = new BorderPane();
	    VBox vBox = new VBox();
	    pane.setTop(start);
	    pane.setCenter(stopwatch);
	    pane.setBottom(reset);
	    vBox.getChildren().addAll(pane);
		
		stage.setScene(new Scene(vBox));
		stage.setTitle("TIMER");
		stage.setHeight(250);
		stage.setWidth(400);
		stage.setX(60);
		stage.setY(500);
		stage.show();
	}
	
	
}
