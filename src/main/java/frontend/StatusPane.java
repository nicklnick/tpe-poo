package frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/*
 * clase para modelar la barra de información
 * en la parte inferior de la aplicación.
 */
public class StatusPane extends BorderPane {

	private final Label statusLabel;

	public StatusPane() {
		setStyle("-fx-background-color: #4EBCF8");
		statusLabel = new Label("Paint 1.0");
		statusLabel.setAlignment(Pos.CENTER);
		statusLabel.setStyle("-fx-font-size: 16");
		setCenter(statusLabel);
	}
	
	public void updateStatus(String text) {
		statusLabel.setText(text);
	}
}