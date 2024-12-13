package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	public GameOverImage(double xPosition, double yPosition, double width, double height) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));

		// Set layout position
		setLayoutX(xPosition);
		setLayoutY(yPosition);

		// Resize the image
		setFitWidth(width);  // Desired width
		setFitHeight(height);  // Desired height

		// Optional: Preserve aspect ratio (prevents distortion)
		setPreserveRatio(true);
	}
}
