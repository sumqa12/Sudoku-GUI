package SudokuGit;

import javax.swing.JPanel;
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
		JPanel panel = new JPanel();
		panel.setBounds(100, 100, 70, 70);
		panel.setVisible(active);
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
						
						System.out.println(time);
						
						time = time.add(d);
					}
				}
				
				EventHandlerStopWatch ehsw = new EventHandlerStopWatch();
				
				timeline = new Timeline(new KeyFrame(Duration.seconds(1), ehsw));
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

