package com.example.demo;

import javafx.scene.Group;

public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final double SHIELD_X_POSITION = 1150;
	private static final double SHIELD_Y_POSITION = 500;
	private static final int WIN_IMAGE_X_POSITION = 200;
	private static final int WIN_IMAGE_Y_POSITION = 0;
	private static final int LOSS_SCREEN_X_POSITION = 200;
	private static final int LOSS_SCREEN_Y_POSITION = 0;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	private final ShieldImage shieldImage ;
	
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION, 1300, 750);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION,1300,750);
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
	}
	
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void showShields() {
		if(!root.getChildren().contains(shieldImage))
			root.getChildren().add(shieldImage);
		 shieldImage.showShield();
	}
	public void hideShields() {
		shieldImage.hideShield();
	}

	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}
	
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}

	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

}
