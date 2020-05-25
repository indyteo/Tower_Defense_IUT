package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import model.*;
import model.enemy.Quartz;
import view.BattlefieldView;

public class Controller implements Initializable{	
	@FXML
	private TilePane tilepane;
	@FXML
    private Pane pane;
	private Battlefield battlefield;
    private BattlefieldView battlefieldView;
    
    public void initialize(URL arg0, ResourceBundle arg1) {
    	battlefield = new Battlefield("battlefields/battlefield1.json");
    	battlefieldView = new BattlefieldView(battlefield, tilepane,pane);
    	battlefieldView.createView();
    	battlefield.getEnemyList().addListener(new EnemyListListener(tilepane,battlefield,battlefieldView));
    	battlefield.addGraph();
    	Quartz q1 = new Quartz(battlefield.getStartCoordinates()[0],battlefield.getStartCoordinates()[1],this.battlefield);
    	battlefield.addEnemy(q1);
    }
    
    @FXML
    void move_button(ActionEvent event) {
    	waveLoop();
    }
    
    public void waveLoop() {
    		for (int i = 0 ; i < battlefield.getEnemyList().size() ; i++) {
    			battlefield.getEnemyList().get(i).move();
    			System.out.println(battlefield.getEnemyList().get(i).getX());
    			System.out.println(battlefield.getEnemyList().get(i).getY());
    		}
    		for (int i = 0 ; i < battlefield.getTurretList().size() ; i++) {
    			battlefield.getTurretList().get(i).action();
    		}
    }

}
