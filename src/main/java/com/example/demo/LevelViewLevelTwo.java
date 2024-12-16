package com.example.demo;

import javafx.scene.Group;

/**
 * Represents the specific level view for Level Two.
 * <p>This class extends {@link LevelView} and customizes the HUD with additional elements
 * unique to Level Two, such as the position and behavior of the shield image.</p>
 */
public class LevelViewLevelTwo extends LevelView {

	/** X-coordinate for the shield image in Level Two. */
	private static final int SHIELD_X_POSITION = 1150;

	/** Y-coordinate for the shield image in Level Two. */
	private static final int SHIELD_Y_POSITION = 500;

	private final Group root;
	private final ShieldImage shieldImage;

	/**
	 * Constructs a {@code LevelViewLevelTwo} instance with the specified root group and initial health display.
	 *
	 * @param root            The root {@link Group} of the scene to which this level view's elements will be added.
	 * @param heartsToDisplay The initial number of hearts (health) to display in the HUD.
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}

	/**
	 * Adds the shield image to the root group.
	 * <p>This method is called during object construction to initialize the HUD elements
	 * specific to Level Two.</p>
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}

	/**
	 * Displays the shield on the HUD by making it visible.
	 */
	public void showShield() {
		shieldImage.showShield();
	}

	/**
	 * Hides the shield on the HUD by making it invisible.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}

}