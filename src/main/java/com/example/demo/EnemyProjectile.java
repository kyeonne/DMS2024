package com.example.demo;

/**
 * Represents a projectile fired by an enemy plane in the game.
 * <p>The {@code EnemyProjectile} moves horizontally across the screen at a constant speed
 * and is visually represented by an enemy fire image.</p>
 */
public class EnemyProjectile extends Projectile {

	/** The name of the image file representing the enemy projectile. */
	private static final String IMAGE_NAME = "enemyFire.png";

	/** The height of the enemy projectile's image in pixels. */
	private static final int IMAGE_HEIGHT = 20;

	/** The horizontal velocity of the enemy projectile (pixels per frame). */
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs an {@code EnemyProjectile} at the specified position.
	 *
	 * @param initialXPos The initial X-coordinate of the projectile on the screen.
	 * @param initialYPos The initial Y-coordinate of the projectile on the screen.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the enemy projectile by moving it horizontally.
	 * <p>The movement is determined by the predefined {@code HORIZONTAL_VELOCITY}.</p>
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the enemy projectile.
	 * <p>For the enemy projectile, this involves calling {@link #updatePosition()} to adjust its position.</p>
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}