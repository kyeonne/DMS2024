package com.example.demo;

/**
 * Represents the base class for all projectile objects in the game.
 * <p>This abstract class extends {@link ActiveActorDestructible} and defines common behaviors
 * and properties for projectiles, such as how they are destroyed and their position updates.</p>
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructs a new {@code Projectile} object with the specified image and initial position.
	 *
	 * @param imageName    The file path of the image representing the projectile.
	 * @param imageHeight  The height of the projectile image in pixels.
	 * @param initialXPos  The initial X-coordinate position of the projectile.
	 * @param initialYPos  The initial Y-coordinate position of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Handles the behavior when the projectile receives damage.
	 * <p>In the default implementation, taking damage destroys the projectile.</p>
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Updates the position of the projectile.
	 * <p>This method must be implemented by subclasses to define
	 * the unique movement logic for specific types of projectiles.</p>
	 */
	@Override
	public abstract void updatePosition();

}