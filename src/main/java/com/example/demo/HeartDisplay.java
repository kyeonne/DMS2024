package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a visual display of hearts, typically used to indicate player health or lives in a game.
 * <p>The {@code HeartDisplay} class uses an {@link HBox} container to organize heart icons horizontally.
 * Hearts can be added or removed dynamically to reflect changes in the number of lives.</p>
 */
public class HeartDisplay {

	/** The file path to the heart image resource. */
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";

	/** The height of each heart image in pixels. */
	private static final int HEART_HEIGHT = 50;

	/** The index of the first item in the container, used when removing a heart. */
	private static final int INDEX_OF_FIRST_ITEM = 0;

	/** The container that holds the heart images. */
	private HBox container;

	/** The X-coordinate of the container's position on the screen. */
	private double containerXPosition;

	/** The Y-coordinate of the container's position on the screen. */
	private double containerYPosition;

	/** The number of hearts to initially display in the container. */
	private int numberOfHeartsToDisplay;

	/**
	 * Constructs a {@code HeartDisplay} positioned at the specified screen coordinates.
	 *
	 * @param xPosition        The X-coordinate of the heart display container's position.
	 * @param yPosition        The Y-coordinate of the heart display container's position.
	 * @param heartsToDisplay  The number of heart images to initially display.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the heart display container.
	 * <p>The {@link HBox} container is created and its layout position is set using
	 * the specified X and Y coordinates.</p>
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Fills the container with the specified number of heart images.
	 * <p>Each heart is represented by an {@link ImageView} of the heart image file,
	 * resized to {@code HEART_HEIGHT} while maintaining its aspect ratio.</p>
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes a heart from the container.
	 * <p>The heart is removed from the leftmost position in the {@link HBox}, if any hearts remain.
	 * Typically used to indicate the loss of a life in the game.</p>
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty()) {
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
		}
	}

	/**
	 * Retrieves the container holding the hearts.
	 * <p>This method is used to access the {@link HBox} for adding the heart display to the scene graph.</p>
	 *
	 * @return The {@link HBox} container holding the heart images.
	 */
	public HBox getContainer() {
		return container;
	}
}