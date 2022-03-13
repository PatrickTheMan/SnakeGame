package com.example.snakegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class SnakeGame extends Application {

    private static Stage stage;

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;

            Pane pane = new Pane();

            Scene scene = new Scene(pane, 420,300);

            setScene(scene);

            stage.setResizable(false);

            stage.show();

            SceneHandler.setSceneMenu();

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void setScene(Scene scene){

        stage.setScene(scene);

    }

    public static void setStageLocation(double x, double y){

        stage.setX(x);
        stage.setY(y);

    }

}