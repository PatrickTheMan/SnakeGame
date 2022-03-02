package com.example.snakegame;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SceneHandler {

    private static int mapX;
    private static int mapY;
    private static int tileSize=32;

    public static void setSceneRestart(){
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: Black");

        Scene scene = new Scene(pane,mapX*tileSize,mapY*tileSize);

        Label label = new Label();
        label.setText("Game Over");
        label.setStyle("-fx-font-weight: bold");
        label.setScaleX(2);
        label.setScaleY(2);
        label.setLayoutX(145);
        label.setLayoutY(25);
        pane.getChildren().add(label);
    }

    public static void setSceneGame(int mapX, int mapY){

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: Black");

        SceneHandler.mapX =mapX;
        SceneHandler.mapY =mapY;
        Scene scene = new Scene(pane,mapX*tileSize,mapY*tileSize);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    SnakeHead.setUp(true); break;
                case DOWN:  SnakeHead.setDown(true); break;
                case LEFT:  SnakeHead.setRight(true); break;
                case RIGHT: SnakeHead.setLeft(true); break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:    SnakeHead.setUp(false); break;
                case DOWN:  SnakeHead.setDown(false); break;
                case LEFT:  SnakeHead.setLeft(false); break;
                case RIGHT: SnakeHead.setRight(false); break;
            }
        });



        SnakeGame.setScene(scene);

    }

}
