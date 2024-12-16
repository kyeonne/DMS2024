package com.example.demo;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a display for the player's kill count in the game.
 * <p>The {@code KillCDisplay} class uses an {@link HBox} container to arrange an image and a kill count label horizontally.
 * The kill count can be dynamically updated to reflect the player's progress.</p>
 */
public class KillCDisplay {

    /** The file path to the kill count image resource. */
    private static final String KILLC_IMAGE_NAME = "/com/example/demo/images/killcount.png";

    /** The height of the kill count image in pixels. */
    private static final int KILLC_HEIGHT = 50;

    /** The container that holds the kill count image and label. */
    private HBox container;

    /** The label displaying the current number of kills. */
    private Label numberOfKills;

    /** The X-coordinate of the container's position on the screen. */
    private double containerXPosition;

    /** The Y-coordinate of the container's position on the screen. */
    private double containerYPosition;

    /**
     * Constructs a {@code KillCDisplay} at the specified screen coordinates.
     *
     * @param xPosition The X-coordinate of the kill count display container's position.
     * @param yPosition The Y-coordinate of the kill count display container's position.
     */
    public KillCDisplay(double xPosition, double yPosition) {
        this.containerXPosition = xPosition;
        this.containerYPosition = yPosition;
        initializeContainer();
        initializeKillcount();
    }

    /**
     * Initializes the container for the kill count display.
     * <p>The {@link HBox} container is created and its position on the screen is set
     * using the specified X and Y coordinates. A spacing of 10 pixels is added between child elements.</p>
     */
    private void initializeContainer() {
        container = new HBox();
        container.setLayoutX(containerXPosition);
        container.setLayoutY(containerYPosition);
        container.setSpacing(10); // Spacing between the kill count image and text
    }

    /**
     * Initializes the kill count display by adding the image and label to the container.
     * <p>An {@link ImageView} is used to display the kill count icon, and a {@link Label} is
     * used to display the numeric kill count. The label is styled to have large, white text.</p>
     */
    private void initializeKillcount() {
        // Create and configure kill count image
        ImageView killc = new ImageView(new Image(getClass().getResource(KILLC_IMAGE_NAME).toExternalForm()));
        killc.setFitHeight(KILLC_HEIGHT);
        killc.setPreserveRatio(true);
        container.getChildren().add(killc);

        // Create and configure kill count label
        numberOfKills = new Label("0");
        numberOfKills.setStyle("-fx-font-size: 40px; -fx-text-fill: white;"); // Styling the text
        numberOfKills.setLayoutX(killc.getLayoutX() + killc.getFitWidth() + 10);
        numberOfKills.setLayoutY(killc.getLayoutY());
        container.getChildren().add(numberOfKills);
    }

    /**
     * Updates the kill count displayed in the label.
     *
     * @param killCount The current kill count to display.
     */
    public void updateKillC(int killCount) {
        numberOfKills.setText("" + killCount); // Convert killCount to string and update label
    }

    /**
     * Retrieves the container holding the kill count display.
     * <p>This container includes both the kill count icon and the label displaying the actual count.</p>
     *
     * @return The {@link HBox} containing the kill count display elements.
     */
    public HBox getContainer() {
        return container;
    }
}