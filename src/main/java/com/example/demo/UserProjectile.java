package com.example.demo;

/**
 * Represents a projectile fired by the user's airplane in the game.
 * <p>The {@code UserProjectile} class extends {@link Projectile} and defines the
 * behavior of projectiles fired by the player, including their movement logic.</p>
 */
public class UserProjectile extends Projectile {

	/** The image file name for the user's projectile. */
	private static final String IMAGE_NAME = "userfire.png";

	/** The height of the projectile image in pixels. */
	private static final int IMAGE_HEIGHT = 125;

	/** The horizontal velocity of the projectile in pixels per update. */
	private static final int HORIZONTAL_VELOCITY = 15;

	/**
	 * Constructs a new {@code UserProjectile} at the specified initial position.
	 *
	 * @param initialXPos The initial X-coordinate position of the projectile.
	 * @param initialYPos The initial Y-coordinate position of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the projectile, including its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

}