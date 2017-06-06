package gui;

import client.Client;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import readerDynamic.ReaderDynamic;

public class Panel {

	private GridPane grid;
	private TextField visor;
	private Button bttPlay;
	private Button bttPause;
	private Button bttStop;
	private HBox bttContainer;	

	private Client client;
	private ReaderDynamic rd;
	
	
	public Panel(Client client, ReaderDynamic rd) {
		super();
		this.client = client;
		this.rd = rd;
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
		rd.setVisor(this.visor);
		return this.visor;
	}
	

	// -------------------- ACTION METHODS --------------------	
	
	private HBox createButtons(HBox bttContainer){
		this.bttContainer = bttContainer;
		
		this.bttPlay = new Button("PLAY");
		this.bttPlay.setMinWidth(70.0);
		this.bttPlay.setOnAction(e->{
			rd.play();
			bttPlay.setText("PLAY");
		});
		
		this.bttPause = new Button("PAUSE");
		this.bttPause.setMinWidth(65.0);
		this.bttPause.setOnAction(e->{
			rd.pause();
			if (bttPlay.getText().equals("PLAY")){
				bttPlay.setText("REINICIAR");
			}
		});
		
		this.bttStop = new Button("STOP");
		this.bttStop.setMinWidth(65.0);	
		this.bttStop.setOnAction(e-> {
			this.visor.setText(client.request("stop"));
			rd.stop();
			bttPlay.setText("PLAY");
		});
		
		this.bttContainer.setSpacing(10.0);
		this.bttContainer.getChildren().addAll(bttPlay, bttPause, bttStop);
		return this.bttContainer;
	}

}
