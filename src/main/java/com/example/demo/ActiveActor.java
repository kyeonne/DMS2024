package com.example.demo;

import javafx.scene.image.*;

/**
 * Represents an active actor in the game.
 * <p>An active actor is any entity in the game capable of movement and interaction,
 * represented visually as an {@link ImageView}.</p>
 * Classes that extend this class must implement their specific behavior by overriding the {@link #updatePosition()} method.
 */
public abstract class ActiveActor extends ImageView {

	/** The base location of all game entity images in the resource folder. */
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs an {@code ActiveActor} with a specified image and initial position.
	 * <p>The actor's image is loaded from the resources folder, and its position and size are initialized.</p>
	 *
	 * @param imageName   The name of the image file (excluding the directory path).
	 * @param imageHeight The height of the actor's image in pixels.
	 * @param initialXPos The initial X-position of the actor on the screen.
	 * @param initialYPos The initial Y-position of the actor on the screen.
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the actor's position based on its current behavior.
	 * <p>This method must be implemented by subclasses to define how the actor's
	 * position should change during the game.</p>
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by a specified amount.
	 *
	 * @param horizontalMove The amount to move the actor along the X-axis.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by a specified amount.
	 *
	 * @param verticalMove The amount to move the actor along the Y-axis.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}