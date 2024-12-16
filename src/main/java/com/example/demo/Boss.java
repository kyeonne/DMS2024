package com.example.demo;

import java.util.*;

/**
 * Represents the boss enemy in the game.
 * <p>The {@code Boss} class extends the {@link FighterPlane} class and features unique behaviors such as
 * firing projectiles, shielding, and moving in a shuffled pattern.</p>
 */
public class Boss extends FighterPlane {

	/** The name of the boss's image file. */
	private static final String IMAGE_NAME = "bossplane.png";

	/** The initial X-coordinate of the boss on the screen. */
	private static final double INITIAL_X_POSITION = 1000.0;

	/** The initial Y-coordinate of the boss on the screen. */
	private static final double INITIAL_Y_POSITION = 400;

	/** The offset for the Y-coordinate of the projectiles fired by the boss. */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;

	/** The probability of the boss firing a projectile in the current frame. */
	private static final double BOSS_FIRE_RATE = 0.04;

	/** The probability of the boss activating its shield during the game. */
	private static final double BOSS_SHIELD_PROBABILITY = 0.002;

	/** The height of the boss's image in pixels. */
	private static final int IMAGE_HEIGHT = 300;

	/** The boss's vertical velocity (change in Y-position per move). */
	private static final int VERTICAL_VELOCITY = 8;

	/** The initial health of the boss. */
	private static final int HEALTH = 10;

	/** The frequency of boss movements in one cycle of its move pattern. */
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

	/** Represents no movement in the move pattern. */
	private static final int ZERO = 0;

	/** The maximum number of consecutive frames with the same move for the boss. */
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;

	/** The upper Y-coordinate bound for the boss's movement. */
	private static final int Y_POSITION_UPPER_BOUND = -100;

	/** The lower Y-coordinate bound for the boss's movement. */
	private static final int Y_POSITION_LOWER_BOUND = 475;

	/** The maximum number of frames the boss can keep its shield activated. */
	private static final int MAX_FRAMES_WITH_SHIELD = 50;

	/** The boss's movement pattern consisting of potential vertical velocities. */
	private final List<Integer> movePattern;

	/** Indicates whether the boss currently has its shield activated. */
	private boolean isShielded;

	/** Tracks the number of consecutive moves in the same direction. */
	private int consecutiveMovesInSameDirection;

	/** The index of the current move in the boss's movement pattern. */
	private int indexOfCurrentMove;

	/** Tracks the number of frames the shield has been activated. */
	private int framesWithShieldActivated;

	/** A reference to the {@link LevelView} for UI updates related to the boss (e.g., shield effects). */
	private LevelView levelView;

	/**
	 * Constructs a {@code Boss} object.
	 * <p>Initializes the boss with its image, size, health, and position, and generates its move pattern.</p>
	 */
	public Boss() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		initializeMovePattern();
	}

	/**
	 * Updates the boss's position.
	 * <p>The boss moves along the vertical axis according to its shuffled movement pattern.
	 * This method ensures the boss does not exceed its predefined movement bounds.</p>
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the boss's gameplay state.
	 * <p>This method updates the boss's position and manages its shield behavior.</p>
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile from the boss.
	 * <p>The boss fires with a probability defined by {@code BOSS_FIRE_RATE}.
	 * If the boss fires, a {@link BossProjectile} is created with the starting position adjusted by
	 * {@code PROJECTILE_Y_POSITION_OFFSET}.</p>
	 *
	 * @return A new {@link ActiveActorDestructible} representing the fired projectile, or {@code null} if no projectile is fired.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Handles taking damage to the boss.
	 * <p>If the shield is not activated, the boss takes damage as defined by the parent class.</p>
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Initializes the boss's move pattern by creating a shuffled sequence of vertical velocities.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the boss's shield behavior.
	 * <p>If the shield is active, tracks the number of frames it remains active. The shield may
	 * activate or deactivate based on probabilities and frame limits.</p>
	 */
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
		} else if (shieldShouldBeActivated()) {
			activateShield();
			levelView.showShields();
		}
		if (shieldExhausted()) {
			deactivateShield();
			levelView.hideShields();
		}
	}

	/**
	 * Determines the next move in the move pattern.
	 * <p>Shuffles the movement pattern after a certain number of consecutive moves in the same direction.</p>
	 *
	 * @return The current move's vertical velocity.
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Determines whether the boss should fire a projectile in the current frame.
	 *
	 * @return {@code true} if the boss fires, {@code false} otherwise.
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Calculates the initial Y-coordinate for the boss's projectile.
	 *
	 * @return The Y-position at which the projectile starts.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Determines whether the shield should be activated based on a probability.
	 *
	 * @return {@code true} if the shield should activate, {@code false} otherwise.
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Determines whether the shield is exhausted (i.e., active for the maximum allowed frames).
	 *
	 * @return {@code true} if the shield is exhausted, {@code false} otherwise.
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the shield for the boss.
	 */
	private void activateShield() {
		isShielded = true;
	}

	/**
	 * Deactivates the shield and resets the shield duration counter.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
	}
}