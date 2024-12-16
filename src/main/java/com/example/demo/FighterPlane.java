package com.example.demo;

/**
 * Represents a base class for all fighter planes in the game.
 * <p>The {@code FighterPlane} class extends {@link ActiveActorDestructible} and adds functionality
 * for managing health, firing projectiles, and handling damage. This class is intended to be
 * extended by specific types of fighter planes, such as enemy or boss planes.</p>
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	/** The health of the fighter plane, representing how many hits it can take before being destroyed. */
	private int health;

	/**
	 * Constructs a {@code FighterPlane} with the specified properties.
	 *
	 * @param imageName     The name of the image file representing the fighter plane.
	 * @param imageHeight   The height of the plane's image in pixels.
	 * @param initialXPos   The initial X-coordinate of the plane on the screen.
	 * @param initialYPos   The initial Y-coordinate of the plane on the screen.
	 * @param health        The initial health of the plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the fighter plane.
	 * <p>Implementing classes must define the behavior of firing a projectile, including the
	 * type of projectile created and its starting position.</p>
	 *
	 * @return An {@link ActiveActorDestructible} representing the fired projectile, or {@code null}
	 *         if no projectile is fired.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Applies damage to the fighter plane by reducing its health by 1.
	 * <p>If the health reaches zero, the plane is destroyed by calling {@link #destroy()}.</p>
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the X-coordinate for a projectile fired by the fighter plane.
	 * <p>This method uses the plane's current position and a provided offset to calculate
	 * the horizontal starting position.</p>
	 *
	 * @param xPositionOffset The offset to apply to the plane's current X-coordinate.
	 * @return The calculated X-coordinate for the projectile's starting position.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y-coordinate for a projectile fired by the fighter plane.
	 * <p>This method uses the plane's current position and a provided offset to calculate
	 * the vertical starting position.</p>
	 *
	 * @param yPositionOffset The offset to apply to the plane's current Y-coordinate.
	 * @return The calculated Y-coordinate for the projectile's starting position.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks whether the health of the fighter plane has reached zero.
	 *
	 * @return {@code true} if the health is zero, otherwise {@code false}.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Retrieves the current health of the fighter plane.
	 *
	 * @return The current health value.
	 */
	public int getHealth() {
		return health;
	}
}