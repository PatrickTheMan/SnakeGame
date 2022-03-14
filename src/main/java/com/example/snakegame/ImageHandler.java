package com.example.snakegame;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;

public class ImageHandler {

    //region [Getters & Setters]

    public static ArrayList<Image> getFoodCollection() {
        return foodCollection;
    }

    public static ArrayList<Image> getSnakeNormalCollection() {
        return snakeNormalCollection;
    }

    public static Image getMapImg(){
        return mapImg;
    }

    //endregion

    //region [Class variables]

    private static Image mapImg = null;
    private static ArrayList<Image> backgroundMenu = new ArrayList<>();
    private static ArrayList<Image> foodCollection = new ArrayList<>();
    private static ArrayList<Image> snakeNormalCollection = new ArrayList<>();

    //endregion

    //region [Load Method]

    public static void makeAllVersionsOfImages(){

        // Load the background for the menu's
        backgroundMenu.add(new Image(new File("src/main/resources/Image/SnakeBackgrounds Main.png").toURI().toString()));
        backgroundMenu.add(new Image(new File("src/main/resources/Image/SnakeBackgrounds Leaderboard.png").toURI().toString()));
        backgroundMenu.add(new Image(new File("src/main/resources/Image/SnakeBackgrounds Options.png").toURI().toString()));
        backgroundMenu.add(new Image(new File("src/main/resources/Image/SnakeBackgrounds Dead.png").toURI().toString()));

        // Load the background for the game
        mapImg = new Image(new File("src/main/resources/Image/SnakeMap.png").toURI().toString());

        Image snakeHead = new Image(new File("src/main/resources/Image/SnakeHead.png").toURI().toString());
        Image snakeBody = new Image(new File("src/main/resources/Image/SnakeBody.png").toURI().toString());
        Image snakeTail = new Image(new File("src/main/resources/Image/SnakeTail.png").toURI().toString());

        ImageView imageView = new ImageView();

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);


        // Load the normal snake img

        // All snakeHead (0-3)
        imageView.setImage(snakeHead);
        snakeNormalCollection.add(snakeHead);
        for (int i = 0; i<=2; i++){

            imageView.setRotate(90);

            snakeNormalCollection.add(imageView.snapshot(parameters,null));

            imageView.setImage(imageView.snapshot(parameters,null));
        }

        // All snakeBody (4-7)
        imageView.setImage(snakeBody);
        snakeNormalCollection.add(snakeBody);
        for (int i = 0; i<=2; i++){

            imageView.setRotate(90);

            snakeNormalCollection.add(imageView.snapshot(parameters,null));

            imageView.setImage(imageView.snapshot(parameters,null));
        }

        // All snakeTail (8-11)
        imageView.setImage(snakeTail);
        snakeNormalCollection.add(snakeTail);
        for (int i = 0; i <=2; i++){

            imageView.setRotate(90);

            snakeNormalCollection.add(imageView.snapshot(parameters,null));

            imageView.setImage(imageView.snapshot(parameters,null));
        }



        // Load the food img
        foodCollection.add(new Image(new File("src/main/resources/Image/Bones Food Red.png").toURI().toString()));
        foodCollection.add(new Image(new File("src/main/resources/Image/Bones Food Blue.png").toURI().toString()));

    }

    //endregion

    //region [Background Methods]

    public static Background getBackground(int num){

        BackgroundImage backgroundImage =
                new BackgroundImage(
                        backgroundMenu.get(num),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT
                );

        return new Background(backgroundImage);

    }

    public static void rotateMap(int v){

        ImageView imageView = new ImageView(mapImg);

        imageView.setRotate(v);

        mapImg = imageView.snapshot(null,null);

    }

    //endregion

    //region [Food Methods]

    public static Image getFoodImg(Color color){

        Image img = null;

        if (color.equals(Color.RED)){
            img = foodCollection.get(0);
        } else if (color.equals(Color.BLUE)){
            img = foodCollection.get(1);
        }

        return img;
    }

    //endregion

    //region [Get Img Method]

    public static Image matchDirImg(Snake snake, int location){

        Image img = null;

        if (location == 0) {
            switch (snake.getSnakeHead().getDir()) {
                case up:
                    img = snakeNormalCollection.get(0);
                    break;
                case down:
                    img = snakeNormalCollection.get(2);
                    break;
                case right:
                    img = snakeNormalCollection.get(1);
                    break;
                case left:
                    img = snakeNormalCollection.get(3);
                    break;
            }
        } else if (location!=snake.getSnakeParts().size()-1){
            switch (snake.getSnakeParts().get(location).getDir()) {
                case up:
                    img = snakeNormalCollection.get(4);
                    break;
                case down:
                    img = snakeNormalCollection.get(6);
                    break;
                case right:
                    img = snakeNormalCollection.get(5);
                    break;
                case left:
                    img = snakeNormalCollection.get(7);
                    break;
            }
        } else {
            switch (snake.getSnakeParts().get(location).getDir()) {
                case up:
                    img = snakeNormalCollection.get(8);
                    break;
                case down:
                    img = snakeNormalCollection.get(10);
                    break;
                case right:
                    img = snakeNormalCollection.get(9);
                    break;
                case left:
                    img = snakeNormalCollection.get(11);
                    break;
            }
        }

        return img;
    }

    //endregion

}
