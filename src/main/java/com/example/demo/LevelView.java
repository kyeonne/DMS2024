package com.example.demo;

import javafx.scene.Group;

/**
 * Represents the visual components of a game level, including HUD elements and end-game screens.
 * <p>The {@code LevelView} class manages the display of critical UI elements, such as the player's
 * health (hearts), shields, kill count, and game-over/win screens. It interacts with the game's root
 * {@code Group} to add or remove components dynamically during gameplay.</p>
 */
public class LevelView {

	/** X-coordinate for the heart display. */
	private static final double HEART_DISPLAY_X_POSITION = 5;

	/** Y-coordinate for the heart display. */
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	/** X-coordinate for the shield image. */
	private static final double SHIELD_X_POSITION = 1200;

	/** Y-coordinate for the shield image. */
	private static final double SHIELD_Y_POSITION = 500;

	/** X-coordinate for the kill count display. */
	private static final double KILLC_DISPLAY_X_POSITION = 650;

	/** Y-coordinate for the kill count display. */
	private static final double KILLC_DISPLAY_Y_POSITION = 25;

	/** X-coordinate for the win image. */
	private static final int WIN_IMAGE_X_POSITION = 200;

	/** Y-coordinate for the win image. */
	private static final int WIN_IMAGE_Y_POSITION = 0;

	/** X-coordinate for the game-over image. */
	private static final int LOSS_SCREEN_X_POSITION = 200;

	/** Y-coordinate for the game-over image. */
	private static final int LOSS_SCREEN_Y_POSITION = 0;

	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	private final ShieldImage shieldImage;
	private final KillCDisplay killDisplay;

	/**
	 * Constructs a {@code LevelView} instance with the specified elements to display.
	 *
	 * @param root            The root {@link Group} to which UI elements will be added.
	 * @param heartsToDisplay The initial number of hearts to display in the health bar.
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION, 1300, 750);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION, 1300, 750);
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		this.killDisplay = new KillCDisplay(KILLC_DISPLAY_X_POSITION, KILLC_DISPLAY_Y_POSITION);
	}

	/**
	 * Displays the heart health bar by adding it to the root group.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the shield image if it is not already visible and enables its visibility.
	 */
	public void showShields() {
		if (!root.getChildren().contains(shieldImage)) {
			root.getChildren().add(shieldImage);
		}
		shieldImage.showShield();
	}

	/**
	 * Hides the shield image from view.
	 */
	public void hideShields() {
		shieldImage.hideShield();
	}

	/**
	 * Displays the kill count indicator by adding it to the root group.
	 */
	public void showKillCDisplay() {
		root.getChildren().add(killDisplay.getContainer());
	}

	/**
	 * Updates the displayed kill count with the given value.
	 *
	 * @param killCount The player's current kill count.
	 */
	public void updateKillC(int killCount) {
		killDisplay.updateKillC(killCount);
	}

	/**
	 * Displays the win screen by adding the win image to the root group and enabling its visibility.
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	/**
	 * Displays the game-over screen by adding the game-over image to the root group.
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}

	/**
	 * Removes hearts from the health display to reflect the remaining health of the player.
	 *
	 * @param heartsRemaining The number of hearts (health) the player currently has.
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}
}