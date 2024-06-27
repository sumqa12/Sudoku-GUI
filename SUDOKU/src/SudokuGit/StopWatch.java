package SudokuGit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class StopWatch extends Label{
	
	private Timeline timeline;
	private final StringProperty stringProperty = new SimpleStringProperty();
	private Duration time = Duration.ZERO;
	private boolean active;
	
	public StopWatch() {
		
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
						
						System.out.println(d);
						
						time = time.add(d);
					}
				}
				
				EventHandlerStopWatch ehsw = new EventHandlerStopWatch();
				
				timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), ehsw));
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

