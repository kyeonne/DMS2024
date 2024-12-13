package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WinImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;

	public WinImage(double xPosition, double yPosition, double width, double height) {
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
	
	public void showWinImage() {
		this.setVisible(true);
	}

}
