package com.example.demo;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class KillCDisplay {
    private static final String KILLC_IMAGE_NAME = "/com/example/demo/images/killcount.png";
    private static final int KILLC_HEIGHT = 50;
    private HBox container;
    private Label numberOfKills;

    private double containerXPosition;
    private double containerYPosition;

    public KillCDisplay(double xPosition, double yPosition) {
        this.containerXPosition = xPosition;
        this.containerYPosition = yPosition;
        initializeContainer();
        initializeKillcount();
    }

    private void initializeContainer() {
        container = new HBox();
        container.setLayoutX(containerXPosition);
        container.setLayoutY(containerYPosition);
        container.setSpacing(10);
    }

    private void initializeKillcount() {
        ImageView killc = new ImageView(new Image(getClass().getResource(KILLC_IMAGE_NAME).toExternalForm()));
        killc.setFitHeight(KILLC_HEIGHT);
        killc.setPreserveRatio(true);
        container.getChildren().add(killc);
        numberOfKills = new Label("0");
        numberOfKills.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");
        numberOfKills.setLayoutX(killc.getLayoutX() + killc.getFitWidth() + 10);
        numberOfKills.setLayoutY(killc.getLayoutY());
        container.getChildren().add(numberOfKills);

    }

    public void updateKillC(int killCount) {
        numberOfKills.setText(""+killCount); // Update the counter label
    }

    public HBox getContainer() {
        return container;
    }
}
