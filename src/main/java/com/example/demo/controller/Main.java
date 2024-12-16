package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The entry point of the Sky Battle game application.
 * <p>This class sets up the main game window and launches the game through the {@link Controller}.</p>
 */
public class Main extends Application {

	/** The width of the game screen in pixels. */
	private static final int SCREEN_WIDTH = 1300;

	/** The height of the game screen in pixels. */
	private static final int SCREEN_HEIGHT = 750;

	/** The title of the game displayed on the window. */
	private static final String TITLE = "Sky Battle";

	/**
	 * Starts the JavaFX application.
	 * <p>This method initializes the main stage, sets its properties, and launches the game
	 * by creating an instance of the {@link Controller} class.</p>
	 *
	 * @param stage The primary stage for the JavaFX application.
	 * @throws ClassNotFoundException        If a required class cannot be found during runtime.
	 * @throws NoSuchMethodException         If a required method cannot be found during runtime.
	 * @throws SecurityException             If a security violation occurs.
	 * @throws InstantiationException        If instantiation of a class fails.
	 * @throws IllegalAccessException        If access to a class or method is not permitted.
	 * @throws IllegalArgumentException      If an illegal or inappropriate argument is passed.
	 * @throws InvocationTargetException     If a reflection-based method invocation fails.
	 */
	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);

		// Create and launch the controller for the game
		var myController = new Controller(stage);
		myController.launchGame();
	}

	/**
	 * The main entry point of the application.
	 * <p>Launches the JavaFX application by calling the link Application launch method.</p>
	 *
	 * @param args Command-line arguments passed to the application (not used).
	 */
	public static void main(String[] args) {
		launch();
	}
}