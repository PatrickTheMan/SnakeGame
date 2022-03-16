package com.example.snakegame;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * <Strong>This is for handling the different images</Strong>
 */
public class ImageHandler {

    //region [Class variables]

    private static Image mapImg = null;
    private static ArrayList<Image> mapCollection = new ArrayList<>();
    private static ArrayList<Image> backgroundMenu = new ArrayList<>();
    private static ArrayList<Image> foodCollection = new ArrayList<>();
    private static ArrayList<Image> snakeNormalCollection = new ArrayList<>();

    //endregion

    //region [Load Method]

    /**
     * <Strong>This is for creating all the different images' rotations</Strong>
     */
    public static void makeAllVersionsOfImages(){

        // Load the background for the menu's
        backgroundMenu.add(new Image(new File("src/main/resources/Image/SnakeBackgrounds Main.png").toURI().toString()));
        backgroundMenu.add(new Image(new File("src/main/resources/Image/SnakeBackgrounds Leaderboard.png").toURI().toString()));
        backgroundMenu.add(new Image(new File("src/main/resources/Image/SnakeBackgrounds Options.png").toURI().toString()));
        backgroundMenu.add(new Image(new File("src/main/resources/Image/SnakeBackgrounds Dead.png").toURI().toString()));

        // Load the background for the game
        mapCollection.add(new Image(new File("src/main/resources/Image/SnakeMap.png").toURI().toString()));
        mapCollection.add(new Image(new File("src/main/resources/Image/SnakeMap Forrest.png").toURI().toString()));
        mapCollection.add(new Image(new File("src/main/resources/Image/SnakeMap Frozen.png").toURI().toString()));
        mapCollection.add(new Image(new File("src/main/resources/Image/SnakeMap Treasure.png").toURI().toString()));

        // Load the food img
        foodCollection.add(new Image(new File("src/main/resources/Image/Bones Food Red.png").toURI().toString()));
        foodCollection.add(new Image(new File("src/main/resources/Image/Bones Food Blue.png").toURI().toString()));
        foodCollection.add(new Image(new File("src/main/resources/Image/Bones Food Green.png").toURI().toString()));
        foodCollection.add(new Image(new File("src/main/resources/Image/Bones Food Yellow.png").toURI().toString()));
        foodCollection.add(new Image(new File("src/main/resources/Image/Bones Food Pink.png").toURI().toString()));

        // Load the images for the normal snake
        Image snakeHead = new Image(new File("src/main/resources/Image/SnakeHead.png").toURI().toString());
        Image snakeBody = new Image(new File("src/main/resources/Image/SnakeBody.png").toURI().toString());
        Image snakeTail = new Image(new File("src/main/resources/Image/SnakeTail.png").toURI().toString());
        Image snakeBodyTurn = new Image(new File("src/main/resources/Image/SnakeBody Turn.png").toURI().toString());

        // Load the images for the phantom snake
        Image snakePhantomHead = new Image(new File("src/main/resources/Image/SnakeHead Phantoms.png").toURI().toString());
        Image snakePhantomBody = new Image(new File("src/main/resources/Image/SnakeBody Phantoms.png").toURI().toString());
        Image snakePhantomTail = new Image(new File("src/main/resources/Image/SnakeTail Phantoms.png").toURI().toString());
        Image snakePhantomBodyTurn = new Image(new File("src/main/resources/Image/SnakeBody Turn Phantoms.png").toURI().toString());

        // Loading the normal snake img --------------------------------------------------------------------------------
        // All snakeHead (0-3)
        snakeNormalCollection.add(snakeHead);
        createRotatedVersions(snakeHead);

        // All snakeBody (4-7)
        snakeNormalCollection.add(snakeBody);
        createRotatedVersions(snakeBody);

        // All snakeTail (8-11)
        snakeNormalCollection.add(snakeTail);
        createRotatedVersions(snakeTail);

        // All snakeBodyTurn (12-15)
        snakeNormalCollection.add(snakeBodyTurn);
        createRotatedVersions(snakeBodyTurn);

        // Loading the phantom snake img -------------------------------------------------------------------------------
        // All snakeHead (16-19)
        snakeNormalCollection.add(snakePhantomHead);
        createRotatedVersions(snakePhantomHead);

        // All snakeBody (20-23)
        snakeNormalCollection.add(snakePhantomBody);
        createRotatedVersions(snakePhantomBody);

        // All snakeTail (24-27)
        snakeNormalCollection.add(snakePhantomTail);
        createRotatedVersions(snakePhantomTail);

        // All snakeTail (28-31)
        snakeNormalCollection.add(snakePhantomBodyTurn);
        createRotatedVersions(snakePhantomBodyTurn);
    }

    private static void createRotatedVersions(Image image){

        // Create the temp imageView
        ImageView imageView = new ImageView();

        // Create the temp parameters for a snapshot
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);

        // Set imageView
        imageView.setImage(image);

        for (int i = 0; i <=2; i++){

            imageView.setRotate(90);

            snakeNormalCollection.add(imageView.snapshot(parameters,null));

            imageView.setImage(imageView.snapshot(parameters,null));
        }

    }

    //endregion

    //region [Background Methods]

    /**
     * <Strong>Get the background img</Strong>
     * @param num is the num on the background it gets (0=main,1=leaderboard,2=options,3=dead)
     * @return the image as a BackGroundImage obj
     */
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

    /**
     * <Strong>This is used to get a new random map, from the collection</Strong>
     */
    public static void newMapImg(){

        Random r = new Random();

        int randomNum = r.nextInt(0,100);

        if (randomNum>=95){
            mapImg = mapCollection.get(3);
        } else if (randomNum>=70){
            mapImg = mapCollection.get(2);
        } else if (randomNum>=65) {
            mapImg = mapCollection.get(1);
        } else {
            mapImg = mapCollection.get(0);
        }

    }

    /**
     * <Strong>This is used for getting the current maps img</Strong>
     * @return the image of the map
     */
    public static Image getMapImg(){

        return mapImg;
    }

    /**
     * <Strong>This is for rotating the map image</Strong>
     * @param a is the angle of the rotation
     */
    public static void rotateMap(int a){

        ImageView imageView = new ImageView(mapImg);

        imageView.setRotate(a);

        mapImg = imageView.snapshot(null,null);

    }

    //endregion

    //region [Food Methods]

    /**
     * <Strong>This is for getting the food image, changes with color</Strong>
     * @param color is the color of the current food obj
     * @return the image of the current colored food
     */
    public static Image getFoodImg(Color color){

        Image img = null;

        if (color.equals(Color.RED)){
            img = foodCollection.get(0);
        } else if (color.equals(Color.BLUE)){
            img = foodCollection.get(1);
        } else if (color.equals(Color.GREEN)){
            img = foodCollection.get(2);
        } else if (color.equals(Color.YELLOW)){
            img = foodCollection.get(3);
        } else if (color.equals(Color.PINK)){
            img = foodCollection.get(4);
        }

        return img;
    }

    //endregion

    //region [Get Img Method]

    /**
     * <Strong>This is for getting the image from the snake as you want</Strong>
     * @param snake is the current snake
     * @param location is the location of the snakePart
     * @return the image that matches the parts location and rotation
     */
    public static Image matchDirImg(Snake snake, int location, boolean phantoms){

        Image img = null;

        if (phantoms){
            // Phantom part
            if (location == 0) {
                // snakeHead img  phantom
                switch (snake.getSnakeHead().getDir()) {
                    case up:
                        img = snakeNormalCollection.get(16);
                        break;
                    case down:
                        img = snakeNormalCollection.get(18);
                        break;
                    case right:
                        img = snakeNormalCollection.get(17);
                        break;
                    case left:
                        img = snakeNormalCollection.get(19);
                        break;
                }
            } else if (location!=snake.getSnakeParts().size()-1){
                // snakeBody middle part phantom
                switch (snake.getSnakeParts().get(location).getDir()) {
                    case up:
                        if (snake.getSnakeParts().get(location+1).getDir() == snake.getSnakeParts().get(location).getDir()){
                            // Normal snakeBody phantom
                            img = snakeNormalCollection.get(20);
                        } else {
                            switch (snake.getSnakeParts().get(location+1).getDir()){
                                // Turned snakeBody phantom
                                case right:
                                    img = snakeNormalCollection.get(30);
                                    break;
                                case left:
                                    img = snakeNormalCollection.get(31);
                                    break;
                            }
                        }
                        break;
                    case down:
                        if (snake.getSnakeParts().get(location+1).getDir() == snake.getSnakeParts().get(location).getDir()){
                            // Normal snakeBody phantom
                            img = snakeNormalCollection.get(22);
                        } else {
                            switch (snake.getSnakeParts().get(location+1).getDir()){
                                // Turned snakeBody phantom
                                case right:
                                    img = snakeNormalCollection.get(29);
                                    break;
                                case left:
                                    img = snakeNormalCollection.get(28);
                                    break;
                            }
                        }
                        break;
                    case right:
                        if (snake.getSnakeParts().get(location+1).getDir() == snake.getSnakeParts().get(location).getDir()){
                            // Normal snakeBody phantom
                            img = snakeNormalCollection.get(21);
                        } else {
                            switch (snake.getSnakeParts().get(location+1).getDir()){
                                // Turned snakeBody phantom
                                case up:
                                    img = snakeNormalCollection.get(28);
                                    break;
                                case down:
                                    img = snakeNormalCollection.get(31);
                                    break;
                            }
                        }
                        break;
                    case left:
                        if (snake.getSnakeParts().get(location+1).getDir() == snake.getSnakeParts().get(location).getDir()){
                            // Normal snakeBody phantom
                            img = snakeNormalCollection.get(23);
                        } else {
                            switch (snake.getSnakeParts().get(location+1).getDir()){
                                // Turned snakeBody phantom
                                case up:
                                    img = snakeNormalCollection.get(29);
                                    break;
                                case down:
                                    img = snakeNormalCollection.get(30);
                                    break;
                            }
                        }
                        break;
                }
            } else {
                // snakeBody tail part phantom
                switch (snake.getSnakeParts().get(location).getDir()) {
                    case up:
                        img = snakeNormalCollection.get(24);
                        break;
                    case down:
                        img = snakeNormalCollection.get(26);
                        break;
                    case right:
                        img = snakeNormalCollection.get(25);
                        break;
                    case left:
                        img = snakeNormalCollection.get(27);
                        break;
                }
            }
        } else {
            // Normal part
            if (location == 0) {
                // snakeHead img
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
                // snakeBody middle part
                switch (snake.getSnakeParts().get(location).getDir()) {
                    case up:
                        if (snake.getSnakeParts().get(location+1).getDir() == snake.getSnakeParts().get(location).getDir()){
                            // Normal snakeBody
                            img = snakeNormalCollection.get(4);
                        } else {
                            switch (snake.getSnakeParts().get(location+1).getDir()){
                                // Turned snakeBody
                                case right:
                                    img = snakeNormalCollection.get(14);
                                    break;
                                case left:
                                    img = snakeNormalCollection.get(15);
                                    break;
                            }
                        }
                        break;
                    case down:
                        if (snake.getSnakeParts().get(location+1).getDir() == snake.getSnakeParts().get(location).getDir()){
                            // Normal snakeBody
                            img = snakeNormalCollection.get(6);
                        } else {
                            switch (snake.getSnakeParts().get(location+1).getDir()){
                                // Turned snakeBody
                                case right:
                                    img = snakeNormalCollection.get(13);
                                    break;
                                case left:
                                    img = snakeNormalCollection.get(12);
                                    break;
                            }
                        }
                        break;
                    case right:
                        if (snake.getSnakeParts().get(location+1).getDir() == snake.getSnakeParts().get(location).getDir()){
                            // Normal snakeBody
                            img = snakeNormalCollection.get(5);
                        } else {
                            switch (snake.getSnakeParts().get(location+1).getDir()){
                                // Turned snakeBody
                                case up:
                                    img = snakeNormalCollection.get(12);
                                    break;
                                case down:
                                    img = snakeNormalCollection.get(15);
                                    break;
                            }
                        }
                        break;
                    case left:
                        if (snake.getSnakeParts().get(location+1).getDir() == snake.getSnakeParts().get(location).getDir()){
                            // Normal snakeBody
                            img = snakeNormalCollection.get(7);
                        } else {
                            switch (snake.getSnakeParts().get(location+1).getDir()){
                                // Turned snakeBody
                                case up:
                                    img = snakeNormalCollection.get(13);
                                    break;
                                case down:
                                    img = snakeNormalCollection.get(14);
                                    break;
                            }
                        }
                        break;
                }
            } else {
                // snakeBody tail part
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
        }

        return img;
    }

    //endregion

}
