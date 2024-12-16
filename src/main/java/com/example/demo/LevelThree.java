package com.example.demo;

/**
 * Represents the third level of the game, featuring a boss enemy and specific gameplay rules.
 * <p>LevelThree introduces a challenging enemy spawn system, where the player must defeat a boss
 * to complete the level. The level also includes a required number of kills before advancing to
 * the boss encounter.</p>
 */
public class LevelThree extends LevelParent {

    /** The file path for the background image used in this level. */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";

    /** The initial health of the player in this level. */
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /** The total number of enemies to maintain on the screen at a time. */
    private static final int TOTAL_ENEMIES = 3;

    /** The number of kills required for the user to advance to the boss fight. */
    private static final int KILLS_TO_ADVANCE = 10;

    /** The probability of spawning an enemy at each spawn attempt. */
    private static final double ENEMY_SPAWN_PROBABILITY = 0.20;

    /** The boss enemy for this level. */
    private final Boss boss;

    /**
     * Constructs a new instance of LevelThree with specified screen dimensions.
     *
     * @param screenHeight The height of the game screen in pixels.
     * @param screenWidth  The width of the game screen in pixels.
     */
    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss();
    }

    /**
     * Checks if the game-over conditions for this level are met.
     * <p>The game ends if the player is destroyed (loss) or the boss is destroyed (win).</p>
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (boss.isDestroyed()) {
            winGame();
        }
    }

    /**
     * Initializes the player's flying unit and adds it to the game scene.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Spawns enemy units randomly, maintaining up to the specified number of enemies on the screen.
     * <p>If the player has reached the required number of kills, the boss is spawned.</p>
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

            if (userHasReachedKillTarget()) {
                addEnemyUnit(boss);
            }
        }
    }

    /**
     * Creates and configures the level's view, initializing it with the player's health display.
     *
     * @return A {@link LevelView} object configured for LevelThree.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks whether the player has reached the required number of kills to engage the boss.
     *
     * @return {@code true} if the player's kill count is greater than or equal to {@value #KILLS_TO_ADVANCE};
     *         {@code false} otherwise.
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}