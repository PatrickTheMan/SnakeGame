package com.example.snakegame;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.io.File;
import java.util.ArrayList;


public class SceneHandler {

    //region [Getters & Setters]

    public static int getMapX() {
        return mapX;
    }

    public static int getMapY() {
        return mapY;
    }

    public static int getTileSize() {
        return tileSize;
    }

    public static boolean hasInput() {
        return input;
    }

    public static void setInput(boolean input) {
        SceneHandler.input = input;
    }

    //endregion

    //region [Variables]

    private static Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    private static double screenX = screenBounds.getMaxX();
    private static double screenY = screenBounds.getMaxY();

    private static Scene scene;
    private static Canvas canvas;
    private static int mapX = 20;
    private static int mapY = 20;
    private static int tileSize=32;

    //endregion

    //region [Scenes]

        //region [Menu Scene]

    public static void setSceneMenu(){

        // The root
        Pane pane = new Pane();

        // Set the background
        pane.setBackground(ImageHandler.getBackground(0));



        scene = new Scene(pane,420,400);

        scene.getStylesheets().add(new File("src/main/resources/fontstyle.css").toURI().toString());



        Label label = new Label();
        label.setText("Snake4Ever");
        label.setScaleX(2);
        label.setScaleY(2);
        label.setStyle("-fx-font-size: 20");
        label.setLayoutX(160);
        label.setLayoutY(25);
        pane.getChildren().add(label);


        Button buttonStart = new Button();
        buttonStart.setText("Start");
        buttonStart.setScaleX(2);
        buttonStart.setScaleY(2);
        buttonStart.setMinWidth(100);
        buttonStart.setLayoutX(160);
        buttonStart.setLayoutY(90);

        buttonStart.setOnAction(actionEvent -> {
            try {

                // Stop menu music
                SoundHandler.stopMenuBackgroundMusic();

                // Set window location
                setSceneLocation(true);

                // Set the scene to the game scene
                setSceneGame();

                // Start a gameengine
                new GameEngine().start();

                // Play button sound
                SoundHandler.playButtonSound();

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        pane.getChildren().add(buttonStart);


        Button buttonLead = new Button();
        buttonLead.setText("Leaderboard");
        buttonLead.setScaleX(2);
        buttonLead.setScaleY(2);
        buttonLead.setMinWidth(100);
        buttonLead.setLayoutX(160);
        buttonLead.setLayoutY(150);

        buttonLead.setOnAction(actionEvent -> {
            try {

                // Change scene
                setSceneLeaderboard();

                // Play button sound
                SoundHandler.playButtonSound();

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        pane.getChildren().add(buttonLead);


        Button buttonOptions = new Button();
        buttonOptions.setText("Settings");
        buttonOptions.setScaleX(2);
        buttonOptions.setScaleY(2);
        buttonOptions.setMinWidth(100);
        buttonOptions.setLayoutX(160);
        buttonOptions.setLayoutY(210);

        buttonOptions.setOnAction(actionEvent -> {
            try {

                // -
                SceneHandler.setSettingsScene();

                // Play button sound
                SoundHandler.playButtonSound();

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        pane.getChildren().add(buttonOptions);



        Button buttonExit = new Button();
        buttonExit.setText("Exit");
        buttonExit.setScaleX(2);
        buttonExit.setScaleY(2);
        buttonExit.setMinWidth(100);
        buttonExit.setLayoutX(160);
        buttonExit.setLayoutY(330);

        buttonExit.setOnAction(actionEvent -> {
            try {

                // Close the program
                System.exit(0);

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        pane.getChildren().add(buttonExit);


        SnakeGame.setScene(scene);

        setSceneLocation(false);


        SoundHandler.startMenuBackgroundMusic();

    }

        //endregion


        //region [Leaderboard Scene]

    public static void setSceneLeaderboard(){

        Pane pane = new Pane();

        // Set the background
        pane.setBackground(ImageHandler.getBackground(1));

        scene = new Scene(pane,420,400);

        scene.getStylesheets().add(new File("src/main/resources/fontstyle.css").toURI().toString());

        Label label = new Label();
        label.setText("Leaderboard");
        label.setStyle("-fx-font-weight: bold");
        label.setStyle("-fx-font-size: 15");
        label.setScaleX(2);
        label.setScaleY(2);
        label.setLayoutX(165);
        label.setLayoutY(25);
        pane.getChildren().add(label);


        // Refresh data
        ScoreHandler.getData();

        TableView leaderboardTbl = ScoreHandler.getLeaderboard();

        pane.getChildren().add(leaderboardTbl);





        Button buttonDelete = new Button();
        buttonDelete.setText("Delete Selected");
        buttonDelete.setScaleX(1.5);
        buttonDelete.setScaleY(1.5);
        buttonDelete.setMinWidth(100);
        buttonDelete.setLayoutX(80);
        buttonDelete.setLayoutY(280);

        buttonDelete.setOnAction(actionEvent -> {
            try {

                if (leaderboardTbl.getSelectionModel().getSelectedItem()!=null){
                    // The score to remove
                    ScoreHandler.removeScore(
                            ((Score)leaderboardTbl.getSelectionModel().getSelectedItem()).getUsername(),
                            ((Score)leaderboardTbl.getSelectionModel().getSelectedItem()).getScore());
                }

                // Update the list
                setSceneLeaderboard();

                // Play button sound
                SoundHandler.playButtonSound();

            } catch (Exception e){
                e.printStackTrace();
            }
        });

        pane.getChildren().add(buttonDelete);



        Button buttonClear = new Button();
        buttonClear.setText("Clear Board");
        buttonClear.setScaleX(1.5);
        buttonClear.setScaleY(1.5);
        buttonClear.setMinWidth(100);
        buttonClear.setLayoutX(240);
        buttonClear.setLayoutY(280);

        buttonClear.setOnAction(actionEvent -> {
            try {

                // Clear the leaderboard
                ScoreHandler.clearData();

                // Update the list
                setSceneLeaderboard();

                // Play button sound
                SoundHandler.playButtonSound();

            } catch (Exception e){
                e.printStackTrace();
            }
        });

        pane.getChildren().add(buttonClear);



        
        Button buttonMenu = new Button();
        buttonMenu.setText("Back to Menu");
        buttonMenu.setScaleX(2);
        buttonMenu.setScaleY(2);
        buttonMenu.setMinWidth(100);
        buttonMenu.setLayoutX(160);
        buttonMenu.setLayoutY(330);

        buttonMenu.setOnAction(actionEvent -> {
            try {

                // Change scene to menu
                setSceneMenu();

                // Play button sound
                SoundHandler.playButtonSound();

                // Stop music for the dead
                SoundHandler.stopDeadBackgroundMusic();

                // Start music for the menu
                SoundHandler.startMenuBackgroundMusic();

            } catch (Exception e){
                e.printStackTrace();
            }
        });

        pane.getChildren().add(buttonMenu);




        setSceneLocation(false);

        SnakeGame.setScene(scene);

    }

        //endregion


        //region [Settings Scene]

    public static void setSettingsScene(){

        // The Root
        Pane pane = new Pane();

        // Set the background
        pane.setBackground(ImageHandler.getBackground(2));

        // The scene is made with the root
        Scene scene = new Scene(pane, 420,400);

        // Set stylesheet
        scene.getStylesheets().add(new File("src/main/resources/fontstyle.css").toURI().toString());

        // The header
        Label label = new Label();
        label.setText("Settings");
        label.setStyle("-fx-font-weight: bold");
        label.setStyle("-fx-font-size: 15");
        label.setScaleX(2);
        label.setScaleY(2);
        label.setLayoutX(175);
        label.setLayoutY(25);
        pane.getChildren().add(label);

        // The mapX option
        Label labelMapX = new Label();
        labelMapX.setText("MapX:");
        labelMapX.setStyle("-fx-font-weight: bold");
        labelMapX.setScaleX(1.5);
        labelMapX.setScaleY(1.5);
        labelMapX.setStyle("-fx-font-size: 13");
        labelMapX.setLayoutX(60);
        labelMapX.setLayoutY(90);
        Label labelMapXTiles = new Label();
        labelMapXTiles.setText("Tiles");
        labelMapXTiles.setScaleX(1.5);
        labelMapXTiles.setScaleY(1.5);
        labelMapXTiles.setStyle("-fx-font-size: 10");
        labelMapXTiles.setLayoutX(320);
        labelMapXTiles.setLayoutY(95);
        TextField textFieldMapX = new TextField();
        textFieldMapX.setPromptText("10 to 55 (default 20)");
        textFieldMapX.setText(""+mapX);
        textFieldMapX.setScaleX(1.5);
        textFieldMapX.setScaleY(1.5);
        textFieldMapX.setLayoutX(150);
        textFieldMapX.setLayoutY(90);
        pane.getChildren().add(labelMapX);
        pane.getChildren().add(labelMapXTiles);
        pane.getChildren().add(textFieldMapX);

        // The mapY option
        Label labelMapY = new Label();
        labelMapY.setText("MapY:");
        labelMapY.setStyle("-fx-font-weight: bold");
        labelMapY.setScaleX(1.5);
        labelMapY.setScaleY(1.5);
        labelMapY.setStyle("-fx-font-size: 13");
        labelMapY.setLayoutX(60);
        labelMapY.setLayoutY(130);
        Label labelMapYTiles = new Label();
        labelMapYTiles.setText("Tiles");
        labelMapYTiles.setScaleX(1.5);
        labelMapYTiles.setScaleY(1.5);
        labelMapYTiles.setStyle("-fx-font-size: 10");
        labelMapYTiles.setLayoutX(320);
        labelMapYTiles.setLayoutY(135);
        TextField textFieldMapY = new TextField();
        textFieldMapY.setPromptText("10 to 30 (default 20)");
        textFieldMapY.setText(""+mapY);
        textFieldMapY.setScaleX(1.5);
        textFieldMapY.setScaleY(1.5);
        textFieldMapY.setLayoutX(150);
        textFieldMapY.setLayoutY(130);
        pane.getChildren().add(labelMapY);
        pane.getChildren().add(labelMapYTiles);
        pane.getChildren().add(textFieldMapY);

        // The powerUp option
        CheckBox powerUpCheck = new CheckBox();
        powerUpCheck.setText("PowerUps");
        powerUpCheck.setSelected(GameEngine.powerUpEnabled);
        powerUpCheck.setScaleX(1.5);
        powerUpCheck.setScaleY(1.5);
        powerUpCheck.setLayoutX(138);
        powerUpCheck.setLayoutY(180);
        pane.getChildren().add(powerUpCheck);

        // The buttonControl option
        CheckBox fourButtonControlCheck = new CheckBox();
        fourButtonControlCheck.setText("FourButtonControl");
        fourButtonControlCheck.setSelected(fourButtonOption);
        fourButtonControlCheck.setScaleX(1.5);
        fourButtonControlCheck.setScaleY(1.5);
        fourButtonControlCheck.setLayoutX(150);
        fourButtonControlCheck.setLayoutY(210);
        pane.getChildren().add(fourButtonControlCheck);

        // The colorOnly option
        CheckBox colorOnlyCheck = new CheckBox();
        colorOnlyCheck.setText("Color Only");
        colorOnlyCheck.setSelected(GameEngine.justColor);
        colorOnlyCheck.setScaleX(1.5);
        colorOnlyCheck.setScaleY(1.5);
        colorOnlyCheck.setLayoutX(138);
        colorOnlyCheck.setLayoutY(240);
        pane.getChildren().add(colorOnlyCheck);

        // The soundOn option
        CheckBox soundCheck = new CheckBox();
        soundCheck.setText("Mute Sound");
        soundCheck.setSelected(SoundHandler.mute);
        soundCheck.setScaleX(1.5);
        soundCheck.setScaleY(1.5);
        soundCheck.setLayoutX(141);
        soundCheck.setLayoutY(270);
        pane.getChildren().add(soundCheck);


        // The save and back button
        Button buttonSaveBack = new Button();
        buttonSaveBack.setText("Save & Back");
        buttonSaveBack.setScaleX(2);
        buttonSaveBack.setScaleY(2);
        buttonSaveBack.setMinWidth(100);
        buttonSaveBack.setLayoutX(160);
        buttonSaveBack.setLayoutY(330);
        // The event is made and the limits are programmed to autohandle problems
        buttonSaveBack.setOnAction(actionEvent -> {
            try {

                // Test value in textfieldmapx
                if (textFieldMapX.getText()==""){
                    textFieldMapX.setText("20");
                } else if (Integer.parseInt(textFieldMapX.getText())>55){
                    textFieldMapX.setText("55");
                } else if (Integer.parseInt(textFieldMapX.getText())<10){
                    textFieldMapX.setText("10");
                }

                // Test value in textfieldmapy
                if (textFieldMapY.getText()==""){
                    textFieldMapY.setText("20");
                } else if (Integer.parseInt(textFieldMapY.getText())>30){
                    textFieldMapY.setText("30");
                } else if (Integer.parseInt(textFieldMapY.getText())<10){
                    textFieldMapY.setText("10");
                }

                // Set boolean to the users chosen value for the button functions
                fourButtonOption = fourButtonControlCheck.isSelected();

                // Set boolean to the users chosen value for wether or not the powerups are there
                GameEngine.powerUpEnabled = fourButtonControlCheck.isSelected();

                // Set boolean to the users chosen value for wether or not it is just color mode
                GameEngine.justColor = colorOnlyCheck.isSelected();

                // Set boolean to the users chosen value for the mute sound
                SoundHandler.mute = soundCheck.isSelected();

                // Set the screensize and placement
                setSceneSize(Integer.parseInt(textFieldMapX.getText()),
                        Integer.parseInt(textFieldMapY.getText()));

                // Set the scene to the menu
                setSceneMenu();

                // Stop music if the mute is true else try to start it
                if (SoundHandler.mute){
                    SoundHandler.stopMenuBackgroundMusic();
                } else {
                    SoundHandler.startMenuBackgroundMusic();
                }

                // Play button sound
                SoundHandler.playButtonSound();

            } catch (Exception e){

                System.out.println(e.getMessage());

                textFieldMapX.setText("");
                textFieldMapY.setText("");
            }
        });

        pane.getChildren().add(buttonSaveBack);

        // The current scene is changed to the new one
        SnakeGame.setScene(scene);

        // The window location is now changed to menu
        setSceneLocation(false);

    }

        //endregion


        //region [Game Scene]

    public static void setSceneGame(){

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: Black");

        canvas = new Canvas(mapX*tileSize,mapY*tileSize);

        pane.getChildren().add(canvas);

        scene = new Scene(pane,mapX*tileSize,mapY*tileSize);

        SnakeGame.setScene(scene);

        SoundHandler.startGameBackgroundMusic();

    }

            //endregion


        //region [Dead Scene]

    public static void setSceneDead(){

        Pane pane = new Pane();

        // Set the background
        pane.setBackground(ImageHandler.getBackground(3));

        scene = new Scene(pane,420,400);

        // Set stylesheet
        scene.getStylesheets().add(new File("src/main/resources/fontstyle.css").toURI().toString());

        Label label = new Label();
        label.setText("Game Over");
        label.setStyle("-fx-font-weight: bold");
        label.setStyle("-fx-font-size: 15");
        label.setScaleX(2);
        label.setScaleY(2);
        label.setLayoutX(170);
        label.setLayoutY(25);
        pane.getChildren().add(label);

        Label labelPoints = new Label();
        labelPoints.setText("Points: "+ScoreHandler.getScore());
        labelPoints.setScaleX(1.5);
        labelPoints.setScaleY(1.5);
        labelPoints.setLayoutX(190);
        labelPoints.setLayoutY(90);
        pane.getChildren().add(labelPoints);


        // The username section
        Label labelUsername = new Label();
        labelUsername.setText("Username:");
        labelUsername.setStyle("-fx-font-weight: bold");
        labelUsername.setScaleX(1.5);
        labelUsername.setScaleY(1.5);
        labelUsername.setLayoutX(60);
        labelUsername.setLayoutY(130);
        TextField textFieldUsername = new TextField();
        textFieldUsername.setScaleX(1.5);
        textFieldUsername.setScaleY(1.5);
        textFieldUsername.setLayoutX(180);
        textFieldUsername.setLayoutY(130);
        pane.getChildren().add(labelUsername);
        pane.getChildren().add(textFieldUsername);


        Button buttonSave = new Button();
        buttonSave.setText("Save");
        buttonSave.setScaleX(2);
        buttonSave.setScaleY(2);
        buttonSave.setMinWidth(100);
        buttonSave.setLayoutX(160);
        buttonSave.setLayoutY(210);

        buttonSave.setOnAction(actionEvent -> {
            try {
                // Save the score with the chosen username or '-' if it is empty
                ScoreHandler.saveScore(textFieldUsername.getText());

                // Set leaderboard scene
                setSceneLeaderboard();

                // Play button sound
                SoundHandler.playButtonSound();

                // Stop music for the dead
                SoundHandler.stopDeadBackgroundMusic();

                // Start music for the menu
                SoundHandler.startMenuBackgroundMusic();

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        pane.getChildren().add(buttonSave);


        Button buttonAgain = new Button();
        buttonAgain.setText("Again");
        buttonAgain.setScaleX(2);
        buttonAgain.setScaleY(2);
        buttonAgain.setMinWidth(100);
        buttonAgain.setLayoutX(160);
        buttonAgain.setLayoutY(270);

        buttonAgain.setOnAction(actionEvent -> {
            try {

                // Set window location
                setSceneLocation(true);

                // Change scene to game
                setSceneGame();

                // Start new game loop
                new GameEngine().start();

                // Play button sound
                SoundHandler.playButtonSound();

                // Stop dead music
                SoundHandler.stopDeadBackgroundMusic();

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        pane.getChildren().add(buttonAgain);


        Button buttonMenu = new Button();
        buttonMenu.setText("Back to Menu");
        buttonMenu.setScaleX(2);
        buttonMenu.setScaleY(2);
        buttonMenu.setMinWidth(100);
        buttonMenu.setLayoutX(160);
        buttonMenu.setLayoutY(330);

        buttonMenu.setOnAction(actionEvent -> {
            try {

                // Change scene to menu
                setSceneMenu();

                // Play button sound
                SoundHandler.playButtonSound();

                // Stop music for the dead
                SoundHandler.stopDeadBackgroundMusic();

                // Start music for the menu
                SoundHandler.startMenuBackgroundMusic();

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        pane.getChildren().add(buttonMenu);







        setSceneLocation(false);

        SnakeGame.setScene(scene);


        SoundHandler.stopGameBackgroundMusic();

        SoundHandler.startDeadBackgroundMusic();
    }

        //endregion

    //endregion


    //region [Other Methods]

    public static void setSceneSize(int mapX, int mapY){

        SceneHandler.mapX =mapX;
        SceneHandler.mapY =mapY;

    }

    public static void setSceneLocation(boolean gameOn){

        if (gameOn){
            SnakeGame.setStageLocation((screenX/2)-mapX*(tileSize/2),(screenY/2)-mapY*(tileSize/2));
        } else {
            SnakeGame.setStageLocation((screenX/2)-210,(screenY/2)-250);
        }

    }

    private static boolean input=false;
    private static boolean fourButtonOption = true;

    public static void setButtons(Snake snake){

        if (fourButtonOption){
            scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case UP:
                        if (!snake.getSnakeHead().getDir().equals(SnakeHead.dir.down) && !input){
                            snake.getSnakeHead().setDir(SnakeHead.dir.up);
                        }
                        break;
                    case DOWN:
                        if (!snake.getSnakeHead().getDir().equals(SnakeHead.dir.up) && !input){
                            snake.getSnakeHead().setDir(SnakeHead.dir.down);
                        }
                        break;
                    case LEFT:
                        if (!snake.getSnakeHead().getDir().equals(SnakeHead.dir.right) && !input){
                            snake.getSnakeHead().setDir(SnakeHead.dir.left);
                        }
                        break;
                    case RIGHT:
                        if (!snake.getSnakeHead().getDir().equals(SnakeHead.dir.left) && !input){
                            snake.getSnakeHead().setDir(SnakeHead.dir.right);
                        }
                        break;
                    case B:
                        //
                        break;
                }
                input=true;
            });
        } else {
            scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case LEFT:
                        if (!input){
                            switch (snake.getSnakeHead().getDir()){
                                case up:
                                    snake.getSnakeHead().setDir(SnakeHead.dir.left);
                                    break;
                                case down:
                                    snake.getSnakeHead().setDir(SnakeHead.dir.right);
                                    break;
                                case right:
                                    snake.getSnakeHead().setDir(SnakeHead.dir.up);
                                    break;
                                case left:
                                    snake.getSnakeHead().setDir(SnakeHead.dir.down);
                                    break;
                            }

                            input=true;
                        }
                        break;
                    case RIGHT:
                        if (!input){
                            switch (snake.getSnakeHead().getDir()){
                                case up:
                                    snake.getSnakeHead().setDir(SnakeHead.dir.right);
                                    break;
                                case down:
                                    snake.getSnakeHead().setDir(SnakeHead.dir.left);
                                    break;
                                case right:
                                    snake.getSnakeHead().setDir(SnakeHead.dir.down);
                                    break;
                                case left:
                                    snake.getSnakeHead().setDir(SnakeHead.dir.up);
                                    break;
                            }

                            input=true;
                        }
                        break;
                }
            });
        }
    }

    //endregion

    //region [Draw Canvas]

    public static void drawCanvas(Snake snake, Food food){

        canvas.getGraphicsContext2D().clearRect(0,0,mapX*tileSize,mapY*tileSize);

        canvas.getGraphicsContext2D().setFill(food.getColor());
        canvas.getGraphicsContext2D().fillRect(food.getX()*tileSize,food.getY()*tileSize,tileSize,tileSize);

        canvas.getGraphicsContext2D().setFill(Color.YELLOW);
        for (int i = 1; i<snake.getSnakeParts().size();i++){
            canvas.getGraphicsContext2D().fillRect(
                    snake.getSnakeParts().get(i).getX()*tileSize,
                    snake.getSnakeParts().get(i).getY()*tileSize, tileSize,tileSize)
            ;}

        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(snake.getSnakeHead().getX()*tileSize,snake.getSnakeHead().getY()*tileSize,tileSize,tileSize);

    }

    public static void drawCanvasIMG(Snake snake, Food food){


        // Clear the screen
        canvas.getGraphicsContext2D().drawImage(ImageHandler.getMapImg(),0,0);


        canvas.getGraphicsContext2D().drawImage(ImageHandler.getFoodImg(food.getColor()),food.getX()*tileSize,food.getY()*tileSize);

        for (int i = 1; i<snake.getSnakeParts().size();i++){

            canvas.getGraphicsContext2D().drawImage(ImageHandler.matchDirImg(snake,i),
                    snake.getSnakeParts().get(i).getX()*tileSize,
                    snake.getSnakeParts().get(i).getY()*tileSize, tileSize,tileSize);
        }

        canvas.getGraphicsContext2D().drawImage(ImageHandler.matchDirImg(snake,0),snake.getSnakeHead().getX()*tileSize,snake.getSnakeHead().getY()*tileSize);

    }

    //endregion

}
