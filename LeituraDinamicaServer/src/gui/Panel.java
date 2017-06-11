package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Panel  {

	private GridPane grid;
	private TextField visor;
	private Button bttPlay;
	private Button bttPause;
	private Button bttStop;
	
	private EventManager manager;
	
	public Panel(EventManager manager) {
		super();
		this.manager = manager;
		this.manager.setPanel(this);
	}
	
	public Button getButton(String name){
		Button btt = new Button();
		switch(name){
			case "Play":
				btt = this.bttPlay;
				break;
			case "Pause":
				btt = this.bttPause;
				break;
			case "Stop":
				btt = this.bttStop;
				break;
		}
		
		return btt;
	}
	
	
	// -------------------- GUI METHODS --------------------
	
	public GridPane createPanel(GridPane grid){
		this.grid = grid;
		this.grid.setAlignment(Pos.CENTER);
		this.grid.setHgap(3);
		this.grid.setVgap(5);
		this.grid.add(createVisor(new TextField()), 1, 1);
		this.grid.add(createButtons(new HBox()), 1, 2);
		return this.grid;
	}
	
	private TextField createVisor(TextField visor){
		this.visor = visor;
		this.visor.setPrefSize(200, 30);
		this.visor.setStyle("-fx-text-alignment:left;-fx-text-fill: black");
		this.visor.setAlignment(Pos.CENTER_RIGHT);
		this.visor.setFont(Font.font(18));
		
		this.manager.setVisor(this.visor);
		
		return this.visor;
	}
	

	// -------------------- ACTION METHODS --------------------	
	
	private HBox createButtons(HBox bttContainer){
		
		this.bttPlay = createButton("PLAY", 70.0);
		this.bttPlay.setOnAction(e->{
			this.manager.onPlay(this.bttPlay);
		});
		
		this.bttPause = createButton("PAUSE", 65.0);
		this.bttPause.setOnAction(e->{
			this.manager.onPause(this.bttPlay);
		});
		
		this.bttStop = createButton("STOP", 65.0);	
		this.bttStop.setOnAction(e-> {
			this.manager.onStop(this.visor, this.bttPlay);
		});
		
		bttContainer.setSpacing(10.0);
		bttContainer.getChildren().addAll(this.bttPlay, this.bttPause, this.bttStop);
		return bttContainer;
	}
	private Button createButton(String name, double size){
		Button btt = new Button(name);
		btt.setMinWidth(size);
		btt.setDisable(true);
		return btt;
	}

}
