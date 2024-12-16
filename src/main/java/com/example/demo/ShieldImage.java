package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the shield image displayed in the game to indicate an active shield power-up or effect.
 * <p>The {@code ShieldImage} class extends {@link ImageView} to display a shield icon on the screen.
 * It provides functionality to show or hide the shield as needed during gameplay.</p>
 */
public class ShieldImage extends ImageView {

	/** The file path for the shield image. */
	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";

	/** The size (height and width) of the shield image in pixels. */
	private static final int SHIELD_SIZE = 200;

	/**
	 * Constructs a new {@code ShieldImage} instance at the specified position.
	 *
	 * @param xPosition The X-coordinate for the shield's position on the screen.
	 * @param yPosition The Y-coordinate for the shield's position on the screen.
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
	 * Displays the shield image on the screen by making it visible.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield image from the screen by making it invisible.
	 */
	public void hideShield() {
		this.setVisible(false);
	}

}