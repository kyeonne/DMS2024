package com.example.demo;

/**
 * Represents the first level of the game.
 * <p>The {@code LevelOne} class extends {@link LevelParent} to implement the logic and configuration
 * specific to the first level, including background image, player health, enemy spawning, and
 * level objectives.</p>
 */
public class LevelOne extends LevelParent {

	/** The file path to the background image for Level One. */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";

	/** The fully qualified name of the next level class to transition to upon completion. */
	private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";

	/** The total number of enemies allowed on the screen at any given time. */
	private static final int TOTAL_ENEMIES = 5;

	/** The number of kills required by the player to proceed to the next level. */
	private static final int KILLS_TO_ADVANCE = 10;

	/** The probability of spawning an enemy when an enemy spawn attempt is made. */
	private static final double ENEMY_SPAWN_PROBABILITY = 0.20;

	/** The initial health of the player at the start of the level. */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * Constructs a {@code LevelOne} instance with the specified screen dimensions.
	 *
	 * @param screenHeight The height of the game screen in pixels.
	 * @param screenWidth The width of the game screen in pixels.
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over, either due to the player's destruction
	 * or achieving the required kills to advance to the next level.
	 * <p>If the player's health is depleted, the game ends with a loss.
	 * If the player reaches the kill target, they advance to the next level.</p>
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Initializes and adds friendly units (such as the player's character) to the level.
	 * <p>For Level One, this involves adding the player character to the scene's root.</p>
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Handles the spawning of enemy units within the game.
	 * <p>The method calculates the number of enemies to spawn by subtracting the
	 * current number of enemies from the {@code TOTAL_ENEMIES}. For each potential
	 * enemy, spawning is determined probabilistically based on {@code ENEMY_SPAWN_PROBABILITY}.</p>
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Instantiates and returns the view for the level.
	 *
	 * @return A {@link LevelView} object configured with the root node and player's initial health.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks whether the player has reached the required number of kills to advance.
	 *
	 * @return {@code true} if the player has achieved the kill target, otherwise {@code false}.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}