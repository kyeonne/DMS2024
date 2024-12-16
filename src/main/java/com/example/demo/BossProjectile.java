package com.example.demo;

/**
 * Represents a projectile fired by the boss enemy in the game.
 * <p>The {@code BossProjectile} moves horizontally from its initial position
 * with a predefined velocity and is represented visually by a fireball image.</p>
 */
public class BossProjectile extends Projectile {

	/** The name of the image file representing the projectile. */
	private static final String IMAGE_NAME = "fireball.png";

	/** The height of the projectile's image in pixels. */
	private static final int IMAGE_HEIGHT = 75;

	/** The horizontal velocity of the projectile (pixels per frame). */
	private static final int HORIZONTAL_VELOCITY = -15;

	/** The initial X-coordinate of the projectile on the screen. */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a {@code BossProjectile} at the specified Y-coordinate.
	 *
	 * @param initialYPos The initial Y-position of the projectile on the screen.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally.
	 * <p>The projectile moves from right to left at a constant speed defined by {@code HORIZONTAL_VELOCITY}.</p>
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the projectile.
	 * <p>For the boss projectile, this involves updating its position.</p>
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

}
