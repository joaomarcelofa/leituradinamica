package readerDynamic;

import client.Client;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class ReaderDynamic {
	
	private int tempo;
	private Timeline timeline;
	private Client client;
	private TextField visor;
	
	public ReaderDynamic(Client client, TextField visor) {
		this.client = client;
		this.visor = visor;
	}

	public void setTime(String time){
		this.tempo = Integer.parseInt(time);
	}
	
	public void setConfigsReaderDynamic(){
		this.timeline = new Timeline();
		this.timeline.setCycleCount(Timeline.INDEFINITE);		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(this.tempo), e->{
			String response = this.client.request("word");
			if(response.equals("x-x-x-x FIM x-x-x-x")) {
				this.client.request("stop");
			}
			else{
				this.visor.setText(response);
			}
		});
		this.timeline.getKeyFrames().add(keyFrame);
	}

	public void setTimelinePlay() {
		this.timeline.play();
	}

	public void setTimelinePause() {
		this.timeline.pause();	
	}

	public void setTimelineStop() {
		this.timeline.stop();
	}
}
