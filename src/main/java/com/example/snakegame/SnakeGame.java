package com.example.snakegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

/**
 * <Strong>This is the MAIN method, which extends the application obj</Strong>
 */
public class SnakeGame extends Application {

    //region [Class variables]

    private static Stage stage;

    //endregion

    //region [Main Method]

    /**
     * <Strong>This is the main method, you start the game from here</Strong>
     * @param args is the commandline-arguments
     */
    public static void main(String[] args) {
        launch();
    }

    //endregion

    //region [Start Method]

    /**
     * <Strong>This is for starting the application</Strong>
     * @param primaryStage is the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        // Try to start everything
        try {
            // The stage is set
            stage = primaryStage;

            // The root
            Pane pane = new Pane();

            // The scene is created with the root
            Scene scene = new Scene(pane, 1,1);

            // The scene is set
            setScene(scene);

            // The rule of not resizeable is set
            stage.setResizable(false);

            // Set stage icon
            stage.getIcons().add(new Image(new File("src/main/resources/Image/SnakeHead.png").toURI().toString()));

            // Set stage name
            stage.setTitle("Snake4Ever");

            // The stage is shown
            stage.show();

            // load images
            ImageHandler.makeAllVersionsOfImages();

            // Setting the scene to the menu
            SceneHandler.setSceneMenu();

        } catch (Exception e) {
            System.out.println("Error: On Startup");
        }
    }

    //endregion

    //region [Set (Stage/Scene) Methods]

    /**
     * <Strong>This is for setting the scene in the stage</Strong>
     * @param scene is the scene you want to be the current one
     */
    public static void setScene(Scene scene){

        stage.setScene(scene);

    }

    /**
     * <Strong>This is for setting the stage's location on the screen</Strong>
     * @param x is the x position given in pixels
     * @param y is the y position given in pixels
     */
    public static void setStageLocation(double x, double y){

        stage.setX(x);
        stage.setY(y);

    }

    //endregion

}