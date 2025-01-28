package sudoku.game.src;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.text.*;
import javafx.util.Duration;

public class StopWatch extends Label{
	
	private Timeline timeline;
	private final StringProperty stringProperty = new SimpleStringProperty();
	private Duration time = Duration.ZERO;
	private boolean active;
	
	public StopWatch() {
		
		setFont(new Font("Arial", 40));
		textProperty().bind(stringProperty);
		reset();
	}
	public void action() {
		if(active) {
			
			timeline.stop();
			
			active = false;
			
			stringProperty.set(String.valueOf(time.toSeconds()));
		}else {
			
			active = true;
			
			if(timeline == null) {
				
				class EventHandlerStopWatch implements EventHandler<ActionEvent>{
					
					public void handle(ActionEvent e) {
						
						Duration d = ((KeyFrame) e.getSource()).getTime();
						time = time.add(d);
						stringProperty.set(String.valueOf(time.toSeconds()));
						
						
					}
				}
				
				EventHandlerStopWatch ehsw = new EventHandlerStopWatch();
				
				timeline = new Timeline(new KeyFrame(Duration.seconds(0.01), ehsw));
			}
			
			timeline.setCycleCount(Timeline.INDEFINITE);
			
			timeline.play();
		}
	}
	public void reset() {
		
		time = Duration.ZERO;
		stringProperty.set(String.valueOf(time.toSeconds()));
		
	}
	public boolean isActive() {
		return active;
	}
}