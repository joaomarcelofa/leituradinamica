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
	private HBox bttContainer;	

	private EventManager manager;
	
	public Panel(EventManager manager) {
		super();
		this.manager = manager;
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
		
		manager.setVisor(this.visor);
		
		return this.visor;
	}
	

	// -------------------- ACTION METHODS --------------------	
	
	private HBox createButtons(HBox bttContainer){
		this.bttContainer = bttContainer;
		
		Button bttPlay = new Button("PLAY");
		bttPlay.setMinWidth(70.0);
		bttPlay.setOnAction(e->{
			manager.onPlay(bttPlay);
		});
		
		Button bttPause = new Button("PAUSE");
		bttPause.setMinWidth(65.0);
		bttPause.setOnAction(e->{
			manager.onPause(bttPlay);
		});
		
		Button bttStop = new Button("STOP");
		bttStop.setMinWidth(65.0);	
		bttStop.setOnAction(e-> {
			manager.onStop(this.visor, bttPlay);
		});
		
		this.bttContainer.setSpacing(10.0);
		this.bttContainer.getChildren().addAll(bttPlay, bttPause, bttStop);
		return this.bttContainer;
	}

}
