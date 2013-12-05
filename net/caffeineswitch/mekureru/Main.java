package net.caffeineswitch.mekureru;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	private AnchorPane root;
	@Override
	public void start(Stage stage) throws Exception {
		root = FXMLLoader.load(FlipablePaneController.class.getResource("FlipablePane.fxml"));
		AnchorPane flipTargetArea = (AnchorPane) root.lookup("#flipTargetArea");
		flipTargetArea.getChildren().add(0,
				(Node) FXMLLoader.load(FlipablePaneController.class
						.getResource("Page1.fxml")));
		flipTargetArea.getChildren().add(0,
				(Node) FXMLLoader.load(FlipablePaneController.class
						.getResource("Page2.fxml")));
		flipTargetArea.getChildren().add(0,
				(Node) FXMLLoader.load(FlipablePaneController.class
						.getResource("Page3.fxml")));
		Scene scene = new Scene(root,980,640);
		stage.setTitle("メクレルUI");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
