package com.example.snakegame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.EventListener;

public class SnakeGame extends Application {

    private static Stage stage;


    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) {
        try {
            this.stage = primaryStage;

            Pane pane = new Pane();


            Label label = new Label();
            label.setText("Snake4Ever: Settings");
            label.setStyle("-fx-font-weight: bold");
            label.setScaleX(2);
            label.setScaleY(2);
            label.setLayoutX(145);
            label.setLayoutY(25);
            pane.getChildren().add(label);


            Label labelMapX = new Label();
            labelMapX.setText("MapX:");
            labelMapX.setStyle("-fx-font-weight: bold");
            labelMapX.setScaleX(1.5);
            labelMapX.setScaleY(1.5);
            labelMapX.setLayoutX(50);
            labelMapX.setLayoutY(100);
            Label labelMapXTiles = new Label();
            labelMapXTiles.setText("Tiles");
            labelMapXTiles.setScaleX(1.5);
            labelMapXTiles.setScaleY(1.5);
            labelMapXTiles.setLayoutX(350);
            labelMapXTiles.setLayoutY(100);
            TextField textFieldMapX = new TextField();
            textFieldMapX.setText("20");
            textFieldMapX.setScaleX(1.5);
            textFieldMapX.setScaleY(1.5);
            textFieldMapX.setLayoutX(150);
            textFieldMapX.setLayoutY(100);
            pane.getChildren().add(labelMapX);
            pane.getChildren().add(labelMapXTiles);
            pane.getChildren().add(textFieldMapX);


            Label labelMapY = new Label();
            labelMapY.setText("MapY:");
            labelMapY.setStyle("-fx-font-weight: bold");
            labelMapY.setScaleX(1.5);
            labelMapY.setScaleY(1.5);
            labelMapY.setLayoutX(50);
            labelMapY.setLayoutY(150);
            Label labelMapYTiles = new Label();
            labelMapYTiles.setText("Tiles");
            labelMapYTiles.setScaleX(1.5);
            labelMapYTiles.setScaleY(1.5);
            labelMapYTiles.setLayoutX(350);
            labelMapYTiles.setLayoutY(150);
            TextField textFieldMapY = new TextField();
            textFieldMapY.setText("20");
            textFieldMapY.setScaleX(1.5);
            textFieldMapY.setScaleY(1.5);
            textFieldMapY.setLayoutX(150);
            textFieldMapY.setLayoutY(150);
            pane.getChildren().add(labelMapY);
            pane.getChildren().add(labelMapYTiles);
            pane.getChildren().add(textFieldMapY);


            Button buttonStart = new Button();
            buttonStart.setText("Start");
            buttonStart.setScaleX(2);
            buttonStart.setScaleY(2);
            buttonStart.setLayoutX(180);
            buttonStart.setLayoutY(230);

            buttonStart.setOnAction(actionEvent -> {
                try {
                    SceneHandler.setSceneGame(Integer.parseInt(textFieldMapX.getText()),
                            Integer.parseInt(textFieldMapY.getText()));
                } catch (Exception e){
                    System.out.println("Error: MapX or MapY isn't a number");
                }
            });

            pane.getChildren().add(buttonStart);


            Scene scene = new Scene(pane, 400,300);

            this.stage.setScene(scene);

            this.stage.setResizable(false);

            this.stage.show();

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void setScene(Scene scene){

        stage.setScene(scene);

    }

}