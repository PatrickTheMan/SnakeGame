package com.example.snakegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class SnakeGame extends Application {

    //region [Class variables]

    private static Stage stage;

    //endregion

    //region [Main Method]

    public static void main(String[] args) {
        launch();
    }

    //endregion

    //region [Start Method]

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;

            Pane pane = new Pane();

            Scene scene = new Scene(pane, 420,300);

            setScene(scene);

            stage.setResizable(false);

            stage.show();

            // load images
            ImageHandler.makeAllVersionsOfImages();

            SceneHandler.setSceneMenu();

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    //endregion

    //region [Set (Stage/Scene) Methods]

    public static void setScene(Scene scene){

        stage.setScene(scene);

    }

    public static void setStageLocation(double x, double y){

        stage.setX(x);
        stage.setY(y);

    }

    //endregion

}