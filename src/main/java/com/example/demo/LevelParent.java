package com.example.demo;

import java.util.*;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

/**
 * Represents the base class for all game levels, managing game logic, user interaction,
 * and the lifecycle of game objects such as enemies, projectiles, and the user-controlled plane.
 * <p>This class defines core mechanics like collision handling, actor updates, and timeline
 * management, which are common across different levels of the game. Specific level behavior
 * can be achieved by extending this class and implementing the abstract methods.</p>
 */
public abstract class LevelParent extends Observable {

	/**
	 * Adjustment value for determining the maximum vertical position of enemy units.
	 */
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	/**
	 * The delay, in milliseconds, between each game update cycle.
	 */
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	private int currentNumberOfEnemies;
	private final LevelView levelView;

	/**
	 * Initializes the game level with the specified parameters.
	 *
	 * @param backgroundImageName The file path of the background image for the level.
	 * @param screenHeight        The height of the game screen.
	 * @param screenWidth         The width of the game screen.
	 * @param playerInitialHealth The initial health of the player's plane.
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
	}

	/**
	 * Initializes any friendly units (e.g., allied planes) for the game level.
	 * <p>This method is abstract and must be implemented by subclasses to define
	 * the behavior and initialization of allied units.</p>
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Checks whether the game is over, based on specific level conditions.
	 * <p>This method is abstract and must be implemented by subclasses to define
	 * game-over logic specific to the level.</p>
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Handles the spawning of enemy units within the level.
	 * <p>This method is abstract and must be implemented by subclasses to define
	 * the logic for enemy generation during gameplay.</p>
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Creates and returns a view instance associated with this level.
	 *
	 * @return The {@link LevelView} object for managing UI components.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the game scene, including the background, friendly units, and display elements.
	 *
	 * @return The {@link Scene} object configured for the level.
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		//levelView.showShields();
		levelView.showKillCDisplay();
		return scene;
	}

	/**
	 * Starts the game timeline, initiating gameplay updates.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Transitions to the next level by stopping the current game timeline and notifying observers.
	 *
	 * @param levelName The name of the next level to load.
	 */
	public void goToNextLevel(String levelName) {
		timeline.stop();
		setChanged();
		notifyObservers(levelName);
	}

	/**
	 * Updates all game elements during each game loop cycle.
	 * <p>This method handles spawning enemies, updating actor states, generating projectiles,
	 * resolving collisions, and checking victory or defeat conditions.</p>
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);

		// Use dedicated methods for key press and release handling
		background.setOnKeyPressed(this::handleKeyPressed);
		background.setOnKeyReleased(this::handleKeyReleased);

		root.getChildren().add(background);
	}

	/**
	 * Handles key press events to manage user input for movement and shooting.
	 *
	 * @param event The {@link KeyEvent} triggered by a key press.
	 */
	private void handleKeyPressed(KeyEvent event) {
		KeyCode keyCode = event.getCode();
		switch (keyCode) {
			case UP, W -> user.moveUp();
			case DOWN, S -> user.moveDown();
			case SPACE -> fireProjectile();
			default -> {
			}
		}
	}

	/**
	 * Handles key release events to stop movement when the corresponding keys are released.
	 *
	 * @param event The {@link KeyEvent} triggered by a key release.
	 */
	private void handleKeyReleased(KeyEvent event) {
		KeyCode keyCode = event.getCode();
		if (keyCode == KeyCode.UP || keyCode == KeyCode.DOWN || keyCode == KeyCode.W || keyCode == KeyCode.S) {
			user.stop();
		}
	}

	private void handlePlaneCollisions() {
		handleCollisionsBetween(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		handleCollisionsBetween(userProjectiles, enemyUnits);
	}

	private void handleEnemyProjectileCollisions() {
		handleCollisionsBetween(enemyProjectiles, friendlyUnits);
	}

	// New method for handling collisions between two groups of actors
	private void handleCollisionsBetween(List<ActiveActorDestructible> group1, List<ActiveActorDestructible> group2) {
		for (ActiveActorDestructible actor1 : group1) {
			for (ActiveActorDestructible actor2 : group2) {
				if (areActorsColliding(actor1, actor2)) {
					applyCollisionEffects(actor1, actor2);
				}
			}
		}
	}

	// Checks if two actors are colliding
	private boolean areActorsColliding(ActiveActorDestructible actor1, ActiveActorDestructible actor2) {
		return actor1.getBoundsInParent().intersects(actor2.getBoundsInParent());
	}

	// Applies effects  to both actors involved in a collision
	private void applyCollisionEffects(ActiveActorDestructible actor1, ActiveActorDestructible actor2) {
		actor1.takeDamage();
		actor2.takeDamage();
	}

	private void handleEnemyPenetration() {
		// Use streams for cleaner iteration and action
		enemyUnits.stream()
				.filter(this::enemyHasPenetratedDefenses)
				.forEach(enemy -> {
					user.takeDamage();
					enemy.destroy();
				});
	}
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates the position and state of all active game objects (actors) in the scene.
	 */
	private void updateActors() {
		friendlyUnits.forEach(ActiveActorDestructible::updateActor);
		enemyUnits.forEach(ActiveActorDestructible::updateActor);
		userProjectiles.forEach(ActiveActorDestructible::updateActor);
		enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Removes all actors marked as destroyed from the game scene and internal lists.
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(ActiveActorDestructible::isDestroyed)
				.toList();
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
		levelView.updateKillC(user.getNumberOfKills());
	}

	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Displays the win screen and stops the game when the victory conditions are satisfied.
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	/**
	 * Displays the game-over screen and stops the game when the defeat conditions are satisfied.
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	/**
	 * Retrieves the player's {@link UserPlane}.
	 *
	 * @return The {@code UserPlane} controlled by the player.
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Retrieves the root {@link Group}, which contains all visual elements of the level.
	 *
	 * @return The root node of the scene graph.
	 */
	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds a new enemy unit to the game, displaying it in the scene and tracking it internally.
	 *
	 * @param enemy The enemy unit to be added.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Retrieves the maximum Y-coordinate at which enemies can spawn.
	 *
	 * @return The maximum Y-coordinate for enemies.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Retrieves the screen width of the game level.
	 *
	 * @return The screen width.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the player's plane is destroyed.
	 *
	 * @return {@code true} if the user's plane is destroyed; {@code false} otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

}
