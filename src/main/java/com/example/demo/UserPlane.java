package com.example.demo;

/**
 * Represents the player's controllable airplane in the game.
 * <p>The {@code UserPlane} class extends the {@link FighterPlane} class and provides
 * custom functionality for player movement, projectile firing, and tracking kill count.</p>
 */
public class UserPlane extends FighterPlane {

	/** The image file name for the user's airplane. */
	private static final String IMAGE_NAME = "userplane.png";

	/** The upper boundary for the airplane's vertical position. */
	private static final double Y_UPPER_BOUND = 0;

	/** The lower boundary for the airplane's vertical position. */
	private static final double Y_LOWER_BOUND = 600.0;

	/** The initial X-coordinate for the airplane's position. */
	private static final double INITIAL_X_POSITION = 5.0;

	/** The initial Y-coordinate for the airplane's position. */
	private static final double INITIAL_Y_POSITION = 300.0;

	/** The height of the airplane image in pixels. */
	private static final int IMAGE_HEIGHT = 50;

	/** The vertical velocity of the airplane. */
	private static final int VERTICAL_VELOCITY = 8;

	/** The X-coordinate offset for projectiles fired by the airplane. */
	private static final int PROJECTILE_X_POSITION = 110;

	/** The Y-coordinate offset for projectiles fired by the airplane. */
	private static final int PROJECTILE_Y_POSITION_OFFSET = 0;

	private int velocityMultiplier;
	private int numberOfKills;

	/**
	 * Constructs a new {@code UserPlane} with the specified initial health.
	 *
	 * @param initialHealth The starting health of the user's airplane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		this.velocityMultiplier = 0;
	}

	/**
	 * Updates the position of the airplane based on vertical movement.
	 * <p>If the airplane moves beyond the vertical boundaries defined by {@code Y_UPPER_BOUND}
	 * and {@code Y_LOWER_BOUND}, its position is reset to the previous valid position.</p>
	 */
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}

	/**
	 * Updates the state of the airplane, including its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the airplane's current position.
	 *
	 * @return A {@link UserProjectile} instance representing the projectile fired by the user.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	/**
	 * Checks if the airplane is currently moving vertically.
	 *
	 * @return {@code true} if the airplane is moving; {@code false} otherwise.
	 */
	private boolean isMoving() {
		return velocityMultiplier != 0;
	}

	/**
	 * Moves the airplane upward by adjusting its velocity.
	 */
	public void moveUp() {
		velocityMultiplier = -1;
	}

	/**
	 * Moves the airplane downward by adjusting its velocity.
	 */
	public void moveDown() {
		velocityMultiplier = 1;
	}

	/**
	 * Stops the airplane's movement by setting the velocity multiplier to zero.
	 */
	public void stop() {
		velocityMultiplier = 0;
	}

	/**
	 * Gets the current kill count of the player's airplane.
	 *
	 * @return The number of kills recorded by the player's airplane.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the kill count of the player's airplane by one.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}

}