package readerDynamic;

import client.Client;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class DynamicReader {
	
	private int tempo;
	private Timeline timeline;
	private Client client;
	private TextField visor;
	
	public DynamicReader(Client client) {
		this.client = client;
	}

	public void setTime(String time){
		this.tempo = Integer.parseInt(time);
	}
	
	public void setVisor(TextField visor){
		this.visor = visor;
	}
	
	public void prepareDynamicReader(){
		createNewTimeline();
	}

	public void play() {
		this.timeline.play();
	}

	public void pause() {
		this.timeline.pause();	
	}

	public void stop() {
		this.timeline.stop();
	}
	
	// -------------------- PRIVATE METHODS --------------------
	
	private void createNewTimeline() {
		this.timeline = new Timeline();
		this.timeline.setCycleCount(Timeline.INDEFINITE);
		this.timeline.getKeyFrames().add(setDuration());
	}
	
	private KeyFrame setDuration() {
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(this.tempo), e->{
			
			String response = this.client.request("word");
			
			if(response.equals("x-x-x-x FIM x-x-x-x")) {
				this.client.request("stop");
				this.timeline.stop();
				this.visor.setText("");
			}
			else{
				this.visor.setText(response);
			}
		});
	
		return keyFrame;
	}
}
