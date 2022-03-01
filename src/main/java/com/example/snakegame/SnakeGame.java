package com.example.snakegame;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeGame extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        //The main root of the stage
        Pane root = new Pane();

        Scene scene = new Scene(root, 100, 100);
        stage.setTitle("Snake4Ever!!!");
        stage.setScene(scene);
        stage.show();
    }

    private void gameEngine (){

        while (true){



        }

    }

}