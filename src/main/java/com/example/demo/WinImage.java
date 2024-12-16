package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the "You Win" image displayed when the player successfully completes the game.
 * <p>The {@code WinImage} class extends {@link ImageView} and is used to display a congratulatory
 * image when the player wins. The image's size and position can be customized during initialization.</p>
 */
public class WinImage extends ImageView {

	/** The file path for the "You Win" image. */
	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";

	/** The default height of the win image in pixels. */
	private static final int HEIGHT = 500;

	/** The default width of the win image in pixels. */
	private static final int WIDTH = 600;

	/**
	 * Constructs a new {@code WinImage} instance with the specified position and size.
	 *
	 * @param xPosition The X-coordinate for the position of the win image.
	 * @param yPosition The Y-coordinate for the position of the win image.
	 * @param width     The width to set for the win image.
	 * @param height    The height to set for the win image.
	 */
	public WinImage(double xPosition, double yPosition, double width, double height) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));

		// Set layout position
		setLayoutX(xPosition);
		setLayoutY(yPosition);

		// Resize the image
		setFitWidth(width);  // Desired width
		setFitHeight(height);  // Desired height

		// Preserve aspect ratio to prevent image distortion
		setPreserveRatio(true);
	}

	/**
	 * Displays the win image by making it visible.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}
}