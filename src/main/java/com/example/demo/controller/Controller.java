package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.LevelParent;

/**
 * The controller class manages the transitions between levels in the game.
 * <p>It interacts with the {@link LevelParent} and handles communication between the game's levels and the primary {@link Stage}.</p>
 * Implements {@link Observer} to listen for level transition events.
 */
public class Controller implements Observer {

	/** The fully qualified class name for the first level of the game. */
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";

	/** The primary {@link Stage} used to display the game. */
	private final Stage stage;

	/**
	 * Constructs a {@code Controller} for managing the game flow.
	 *
	 * @param stage The primary JavaFX {@link Stage} where the game is displayed.
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game by initializing the first level and showing the stage.
	 *
	 * @throws ClassNotFoundException        If the first level class cannot be found.
	 * @throws NoSuchMethodException         If the constructor for the level class cannot be found.
	 * @throws SecurityException             If a security violation occurs.
	 * @throws InstantiationException        If the instantiation of the level class fails.
	 * @throws IllegalAccessException        If the constructor cannot be accessed.
	 * @throws IllegalArgumentException      If invalid arguments are provided to the constructor.
	 * @throws InvocationTargetException     If an error occurs during the execution of the constructor.
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Transitions to a specific level in the game.
	 *
	 * @param className The fully qualified name of the level class to load.
	 * @throws ClassNotFoundException        If the level class cannot be found.
	 * @throws NoSuchMethodException         If the constructor for the level class cannot be found.
	 * @throws SecurityException             If a security violation occurs.
	 * @throws InstantiationException        If the instantiation of the level class fails.
	 * @throws IllegalAccessException        If the constructor cannot be accessed.
	 * @throws IllegalArgumentException      If invalid arguments are provided to the constructor.
	 * @throws InvocationTargetException     If an error occurs during the execution of the constructor.
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
		myLevel.addObserver(this);
		Scene scene = myLevel.initializeScene();
		stage.setScene(scene);
		myLevel.startGame();
	}

	/**
	 * Updates the controller when notified by an observed object.
	 * <p>If the observed object requires a level change, this method transitions to the specified level.</p>
	 *
	 * @param observable The observable object that triggered the update (unused in this implementation).
	 * @param arg        The argument passed by the observable, typically the class name of the next level to load.
	 */
	@Override
	public void update(Observable observable, Object arg) {
		try {
			goToLevel((String) arg);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}
}