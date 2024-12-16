package com.example.demo;

/**
 * Represents the second level of the game, where the player must defeat a boss to advance.
 * <p>LevelTwo introduces a single boss enemy as the primary challenge,
 * requiring the player to survive while eliminating the boss to proceed to the next level.</p>
 */
public class LevelTwo extends LevelParent {

	/** The file path for the background image used in this level. */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";

	/** The fully qualified class name of the next level (LevelThree). */
	private static final String NEXT_LEVEL = "com.example.demo.LevelThree";

	/** The initial health of the player in this level. */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/** The boss enemy for this level. */
	private final Boss boss;

	/**
	 * Constructs a new instance of LevelTwo with specified screen dimensions.
	 *
	 * @param screenHeight The height of the game screen in pixels.
	 * @param screenWidth  The width of the game screen in pixels.
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		this.boss = new Boss();
	}

	/**
	 * Checks if the game-over conditions for this level are met.
	 * <p>The game is lost if the player's health is depleted. The player
	 * advances to the next level if the boss is defeated.</p>
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (boss.isDestroyed()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Initializes the player's unit and adds it to the game scene.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Spawns enemy units in the level.
	 * <p>Only the boss enemy is spawned in this level. If no enemies are currently present,
	 * the boss is added to the scene.</p>
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	/**
	 * Creates and configures the level's view object.
	 *
	 * @return A {@link LevelViewLevelTwo} object tailored for LevelTwo.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
	}
}