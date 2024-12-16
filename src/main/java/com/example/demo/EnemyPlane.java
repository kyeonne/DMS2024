package com.example.demo;

/**
 * Represents an enemy plane in the game.
 * <p>The {@code EnemyPlane} is a type of {@link FighterPlane} that moves horizontally across the screen
 * and sporadically fires projectiles.</p>
 */
public class EnemyPlane extends FighterPlane {

	/** The name of the image file representing the enemy plane. */
	private static final String IMAGE_NAME = "enemyplane.png";

	/** The height of the enemy plane's image in pixels. */
	private static final int IMAGE_HEIGHT = 150;

	/** The horizontal velocity of the enemy plane (pixels per frame). */
	private static final int HORIZONTAL_VELOCITY = -6;

	/** The X-coordinate offset for the position of projectiles fired by the enemy plane. */
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;

	/** The Y-coordinate offset for the position of projectiles fired by the enemy plane. */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;

	/** The initial health of the enemy plane. */
	private static final int INITIAL_HEALTH = 1;

	/** The probability of the enemy plane firing its projectile during each frame. */
	private static final double FIRE_RATE = 0.01;

	/**
	 * Constructs an {@code EnemyPlane} at the specified initial position.
	 *
	 * @param initialXPos The initial X-coordinate of the enemy plane on the screen.
	 * @param initialYPos The initial Y-coordinate of the enemy plane on the screen.
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally.
	 * <p>The movement is determined by the predefined {@code HORIZONTAL_VELOCITY}.</p>
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile from the enemy plane.
	 * <p>The enemy plane fires a projectile with a probability defined by {@code FIRE_RATE}. If the plane
	 * fires, an {@link EnemyProjectile} is created at a calculated starting position based on offsets.</p>
	 *
	 * @return A new {@link ActiveActorDestructible} representing the fired projectile, or {@code null} if no projectile is fired.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Updates the state of the enemy plane.
	 * <p>For the enemy plane, this involves moving its position by calling {@link #updatePosition()}.</p>
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}