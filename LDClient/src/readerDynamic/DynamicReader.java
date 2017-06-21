package readerDynamic;

import client.Client;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 * Classe DynamicReader � respons�vel pela leitura dinamica e sua intera��o com o usuarios. Para
 * isso, utiliza o recurso de Timeline que possiblita a cria��o de uma outra thread para as a��es
 * de uma leitura din�mica, como: play, pause e stop.
 *
 */
public class DynamicReader {
	
	private int tempo;
	private Timeline timeline;
	private Client client;
	private TextField visor;
	
	public DynamicReader(Client client) {
		this.client = client;
	}

	public Timeline getTimeline() {
		return this.timeline;
	}
	
	public void setTime(String time){
		this.tempo = Integer.parseInt(time);
	}
	
	public void setVisor(TextField visor){
		this.visor = visor;
	}
	
	public void createDynamicReader(Button play, Button pause, Button stop){
		this.timeline = new Timeline();
		this.timeline.setCycleCount(Timeline.INDEFINITE);
		
		KeyFrame keyFrame = new KeyFrame(Duration.millis(this.tempo), e->{
			
			String response = this.client.request("word");
			
			if(response.equals("x-x-x-x FIM x-x-x-x")) {
				this.client.request("stop");
				this.timeline.stop();
				this.visor.setText("");
				play.setDisable(false);
				pause.setDisable(true);
				stop.setDisable(true);
			}
			else{
				this.visor.setText(response);
			}
		});
		
		this.timeline.getKeyFrames().add(keyFrame);
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
}
