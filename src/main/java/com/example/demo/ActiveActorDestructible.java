package com.example.demo;

/**
 * Represents a destructible active actor in the game.
 * <p>This class extends the {@link ActiveActor} and implements the {@link Destructible} interface
 * to provide functionality for actors that can take damage and be destroyed.</p>
 * Subclasses must implement specific behavior for updating the actor, updating its position,
 * and defining how it takes damage.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/** Indicates whether the actor is destroyed. */
	private boolean isDestroyed;

	/**
	 * Constructs an {@code ActiveActorDestructible} with the specified image, size, and position.
	 *
	 * @param imageName   The name of the image file representing the actor.
	 * @param imageHeight The height of the actor's image in pixels.
	 * @param initialXPos The initial X-position of the actor on the screen.
	 * @param initialYPos The initial Y-position of the actor on the screen.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the position of the actor.
	 * <p>This method defines how the actor's position changes
	 * during the game and must be implemented by subclasses.</p>
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the state and behavior of the actor.
	 * <p>This method must be implemented by subclasses to define actor-specific updates,
	 * such as movement, interaction with other game elements, or checking conditions for destruction.</p>
	 */
	public abstract void updateActor();

	/**
	 * Defines how the actor takes damage.
	 * <p>This method must be implemented by subclasses to define what happens
	 * when the actor receives damage, such as reducing health or changing state.</p>
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Destroys the actor.
	 * <p>Marks the actor as destroyed by calling {@link #setDestroyed()}.</p>
	 */
	@Override
	public void destroy() {
		setDestroyed();
	}

	/**
	 * Marks the actor as destroyed.
	 * <p>Sets the {@code isDestroyed} field to {@code true}.
	 * This method can be used internally to update the destruction state of the actor.</p>
	 */
	protected void setDestroyed() {
		this.isDestroyed = true;
	}

	/**
	 * Checks if the actor is destroyed.
	 *
	 * @return {@code true} if the actor is destroyed, {@code false} otherwise.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}

}