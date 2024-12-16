package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a game over screen image displayed when the game ends.
 * <p>The {@code GameOverImage} class extends {@link ImageView} to render a "Game Over" image
 * at a specified position and with specified dimensions.</p>
 */
public class GameOverImage extends ImageView {

	/** The file path to the "Game Over" image resource. */
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
	 * Constructs a {@code GameOverImage} with the specified position and size.
	 *
	 * @param xPosition The X-coordinate of the image's top-left corner on the screen.
	 * @param yPosition The Y-coordinate of the image's top-left corner on the screen.
	 * @param width     The desired width of the image.
	 * @param height    The desired height of the image.
	 */
	public GameOverImage(double xPosition, double yPosition, double width, double height) {
		// Set the image to be displayed
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));

		// Set the position of the image on the screen
		setLayoutX(xPosition);
		setLayoutY(yPosition);

		// Set the size of the image
		setFitWidth(width);  // Desired width
		setFitHeight(height);  // Desired height

		// Preserve the aspect ratio of the image to avoid distortion
		setPreserveRatio(true);
	}
}