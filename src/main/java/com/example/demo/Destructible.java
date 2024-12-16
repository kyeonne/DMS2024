package com.example.demo;

/**
 * Represents an entity that can be damaged and destroyed.
 * <p>Classes implementing the {@code Destructible} interface are expected to define behavior for
 * taking damage and being destroyed.</p>
 */
public interface Destructible {

	/**
	 * Applies damage to the entity.
	 * <p>Implementing classes should define the effects of damage, such as reducing health
	 * or triggering visual/auditory feedback.</p>
	 */
	void takeDamage();

	/**
	 * Destroys the entity.
	 * <p>Implementing classes should define how an entity is removed from the game or its state
	 * is altered upon destruction.</p>
	 */
	void destroy();

}