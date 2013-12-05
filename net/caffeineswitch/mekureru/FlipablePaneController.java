package net.caffeineswitch.mekureru;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class FlipablePaneController implements Initializable{
	private Pane flipEffect;

	@FXML
	private AnchorPane flipablePane;

	@FXML
	private AnchorPane flipTargetArea;

	@FXML
	private Pane flipableArea;

	@FXML
	private void handleFlipabeAreaClicked(){
		ObservableList<Node> children = flipTargetArea.getChildren();
		Node n = children.get(children.size()-1);
		for(int i=0;i<children.size();i++){
			n.toBack();
		}
	}

	@FXML
	private void handleFlipableAreaEntered(){
		if(!flipableArea.getChildren().contains(flipEffect)){
			flipableArea.getChildren().add(flipEffect);
			AnchorPane.setTopAnchor(flipEffect, 0.0);
			AnchorPane.setLeftAnchor(flipEffect, 0.0);
			double fromX = -flipEffect.getPrefWidth();
			double fromY = -flipEffect.getPrefHeight();
			double toX = 0.0;
			double toY = 0.0;

			new ParallelTransition(
					ScaleTransitionBuilder.create().node(flipEffect).fromX(0.0).fromY(0.0).toX(1.0).toY(1.0).duration(Duration.millis(200)).build(),
					TranslateTransitionBuilder.create().node(flipEffect).fromX(fromX).fromY(fromY).toX(toX).toY(toY).duration(Duration.millis(200)).build()
				).play();
		}
	}

	@FXML
	private void handleFlipableAreaExited(){
		if(flipableArea.getChildren().contains(flipEffect)){
			double fromX = 0.0;
			double fromY = 0.0;
			double toX = -flipEffect.getPrefWidth();
			double toY = -flipEffect.getPrefHeight();

			ParallelTransitionBuilder.create().children(
					ScaleTransitionBuilder.create().node(flipEffect).fromX(1.0).fromY(1.0).toX(0.0).toY(0.0).duration(Duration.millis(200)).build(),
					TranslateTransitionBuilder.create().node(flipEffect).fromX(fromX).fromY(fromY).toX(toX).toY(toY).duration(Duration.millis(200)).build()
				).onFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						flipableArea.getChildren().remove(flipEffect);
					}
				}).build().play();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			flipEffect = FXMLLoader.load(this.getClass().getResource("FlipEffect.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
