package com.example.snakegame;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.io.File;

/**
 * <Strong>This is used for handling the different scenes</Strong>
 */
public class SceneHandler {

    //region [Getters & Setters]

    public static int getMapX() {
        return mapX;
    }

    public static int getMapY() {
        return mapY;
    }

    public static boolean hasInput() {
        return input;
    }

    public static void setInput(boolean input) {
        SceneHandler.input = input;
    }

    //endregion

    //region [Class Variables]

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

        // Set the scene
        scene = new Scene(pane,420,400);

        // Set the style of the scene to the css file
        scene.getStylesheets().add(new File("src/main/resources/fontstyle.css").toURI().toString());

        // The Headline
        Label label = new Label();
        label.setText("Snake4Ever");
        label.setScaleX(2);
        label.setScaleY(2);
        label.setStyle("-fx-font-size: 20");
        label.setLayoutX(160);
        label.setLayoutY(25);
        pane.getChildren().add(label);

        // The start button
        Button buttonStart = new Button();
        buttonStart.setText("Start");
        buttonStart.setScaleX(2);
        buttonStart.setScaleY(2);
        buttonStart.setMinWidth(100);
        buttonStart.setLayoutX(160);
        buttonStart.setLayoutY(90);
        // The action of the start button
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

        // The Leaderboard button
        Button buttonLead = new Button();
        buttonLead.setText("Leaderboard");
        buttonLead.setScaleX(2);
        buttonLead.setScaleY(2);
        buttonLead.setMinWidth(100);
        buttonLead.setLayoutX(160);
        buttonLead.setLayoutY(150);
        // The action of the leaderboard button
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

        // The options button
        Button buttonOptions = new Button();
        buttonOptions.setText("Settings");
        buttonOptions.setScaleX(2);
        buttonOptions.setScaleY(2);
        buttonOptions.setMinWidth(100);
        buttonOptions.setLayoutX(160);
        buttonOptions.setLayoutY(210);
        // The action of the option button
        buttonOptions.setOnAction(actionEvent -> {
            try {
                // Set the scene to the settings scene
                SceneHandler.setSettingsScene();

                // Play button sound
                SoundHandler.playButtonSound();

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
        pane.getChildren().add(buttonOptions);

        // The exit button
        Button buttonExit = new Button();
        buttonExit.setText("Exit");
        buttonExit.setScaleX(2);
        buttonExit.setScaleY(2);
        buttonExit.setMinWidth(100);
        buttonExit.setLayoutX(160);
        buttonExit.setLayoutY(330);
        // The action of the exit button
        buttonExit.setOnAction(actionEvent -> {
            try {
                // Close the program
                System.exit(0);

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
        pane.getChildren().add(buttonExit);

        // Set the current scene to this one
        SnakeGame.setScene(scene);

        // Set the location of the scene to the middle for a menu scene
        setSceneLocation(false);

        // Start the menu music
        SoundHandler.startMenuBackgroundMusic();

    }

        //endregion


        //region [Leaderboard Scene]

    public static void setSceneLeaderboard(){

        // The root
        Pane pane = new Pane();

        // Set the background
        pane.setBackground(ImageHandler.getBackground(1));

        // Set the scene with the root
        scene = new Scene(pane,420,400);

        // Get the styleing from the css file
        scene.getStylesheets().add(new File("src/main/resources/fontstyle.css").toURI().toString());

        // The headline
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

        // Creating a TableView with the scores in the saveFile
        TableView leaderboardTbl = ScoreHandler.getLeaderboard();

        // Adding the TableView to the scene
        pane.getChildren().add(leaderboardTbl);

        // The delete button
        Button buttonDelete = new Button();
        buttonDelete.setText("Delete Selected");
        buttonDelete.setScaleX(1.5);
        buttonDelete.setScaleY(1.5);
        buttonDelete.setMinWidth(100);
        buttonDelete.setLayoutX(80);
        buttonDelete.setLayoutY(280);
        // The action of the delete button
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

        // The clear button
        Button buttonClear = new Button();
        buttonClear.setText("Clear Board");
        buttonClear.setScaleX(1.5);
        buttonClear.setScaleY(1.5);
        buttonClear.setMinWidth(100);
        buttonClear.setLayoutX(240);
        buttonClear.setLayoutY(280);
        // The action of the clear button
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

        // The menu button
        Button buttonMenu = new Button();
        buttonMenu.setText("Back to Menu");
        buttonMenu.setScaleX(2);
        buttonMenu.setScaleY(2);
        buttonMenu.setMinWidth(100);
        buttonMenu.setLayoutX(160);
        buttonMenu.setLayoutY(330);
        // The action of the menu button
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

        // Set the screens position to the middle of the screen
        setSceneLocation(false);

        // Set the current scene to this one
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
        powerUpCheck.setLayoutY(170);
        // The action of the powerUp checkbox
        powerUpCheck.setOnAction(actionEvent -> {
            SoundHandler.playButtonSound();
        });
        pane.getChildren().add(powerUpCheck);

        // The buttonControl option
        CheckBox fourButtonControlCheck = new CheckBox();
        fourButtonControlCheck.setText("FourButtonControl");
        fourButtonControlCheck.setSelected(fourButtonOption);
        fourButtonControlCheck.setScaleX(1.5);
        fourButtonControlCheck.setScaleY(1.5);
        fourButtonControlCheck.setLayoutX(150);
        fourButtonControlCheck.setLayoutY(200);
        // The action of the buttonControl checkbox
        fourButtonControlCheck.setOnAction(actionEvent -> {
            SoundHandler.playButtonSound();
        });
        pane.getChildren().add(fourButtonControlCheck);

        // The colorOnly option
        CheckBox colorOnlyCheck = new CheckBox();
        colorOnlyCheck.setText("Color Only");
        colorOnlyCheck.setSelected(GameEngine.justColor);
        colorOnlyCheck.setScaleX(1.5);
        colorOnlyCheck.setScaleY(1.5);
        colorOnlyCheck.setLayoutX(138);
        colorOnlyCheck.setLayoutY(230);
        // The action of the colorOnly checkbox
        colorOnlyCheck.setOnAction(actionEvent -> {
            SoundHandler.playButtonSound();
        });
        pane.getChildren().add(colorOnlyCheck);

        // The inputDelay option
        CheckBox inputDelayCheck = new CheckBox();
        inputDelayCheck.setText("Input delay");
        inputDelayCheck.setSelected(GameEngine.inputDelay);
        inputDelayCheck.setScaleX(1.5);
        inputDelayCheck.setScaleY(1.5);
        inputDelayCheck.setLayoutX(140);
        inputDelayCheck.setLayoutY(260);
        // The action of the  checkbox
        inputDelayCheck.setOnAction(actionEvent -> {
            SoundHandler.playButtonSound();
        });
        pane.getChildren().add(inputDelayCheck);

        // The mute option
        CheckBox soundCheck = new CheckBox();
        soundCheck.setText("Mute Sound");
        soundCheck.setSelected(SoundHandler.mute);
        soundCheck.setScaleX(1.5);
        soundCheck.setScaleY(1.5);
        soundCheck.setLayoutX(141);
        soundCheck.setLayoutY(290);
        // The action of the mute checkbox
        soundCheck.setOnAction(actionEvent -> {
            SoundHandler.playButtonSound();
        });
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
                GameEngine.powerUpEnabled = powerUpCheck.isSelected();

                // Set boolean to the users chosen value for wether or not it is just color mode
                GameEngine.justColor = colorOnlyCheck.isSelected();

                // Set boolean to the users chosen value for wether or not the input delay is present
                GameEngine.inputDelay =inputDelayCheck.isSelected();

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

                // Reset the two textFields
                textFieldMapX.setText("20");
                textFieldMapY.setText("20");
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

        // The root & set background to black
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: Black");

        // Create the game canvas
        canvas = new Canvas(mapX*tileSize,mapY*tileSize);

        // Add the canvas to the root
        pane.getChildren().add(canvas);

        // Create the scene with the root
        scene = new Scene(pane,mapX*tileSize,mapY*tileSize);

        // Set the current scene to this one
        SnakeGame.setScene(scene);

        // Start the game music
        SoundHandler.startGameBackgroundMusic();

    }

            //endregion


        //region [Dead Scene]

    public static void setSceneDead(){

        // The root
        Pane pane = new Pane();

        // Set the background
        pane.setBackground(ImageHandler.getBackground(3));

        // Create the scene with the root
        scene = new Scene(pane,420,400);

        // Set stylesheet
        scene.getStylesheets().add(new File("src/main/resources/fontstyle.css").toURI().toString());

        // The Headline
        Label label = new Label();
        label.setText("Game Over");
        label.setStyle("-fx-font-weight: bold");
        label.setStyle("-fx-font-size: 15");
        label.setScaleX(2);
        label.setScaleY(2);
        label.setLayoutX(170);
        label.setLayoutY(25);
        pane.getChildren().add(label);

        // The point label
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

        // The save button
        Button buttonSave = new Button();
        buttonSave.setText("Save");
        buttonSave.setScaleX(2);
        buttonSave.setScaleY(2);
        buttonSave.setMinWidth(100);
        buttonSave.setLayoutX(160);
        buttonSave.setLayoutY(210);
        // The action of the save button
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

        // The again button
        Button buttonAgain = new Button();
        buttonAgain.setText("Again");
        buttonAgain.setScaleX(2);
        buttonAgain.setScaleY(2);
        buttonAgain.setMinWidth(100);
        buttonAgain.setLayoutX(160);
        buttonAgain.setLayoutY(270);
        // The action of the again button
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

        // The menu button
        Button buttonMenu = new Button();
        buttonMenu.setText("Back to Menu");
        buttonMenu.setScaleX(2);
        buttonMenu.setScaleY(2);
        buttonMenu.setMinWidth(100);
        buttonMenu.setLayoutX(160);
        buttonMenu.setLayoutY(330);
        // The action of the menu button
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

        // Set the location of the window so it is in the middle
        setSceneLocation(false);

        // Set the current scene to this one
        SnakeGame.setScene(scene);

        // Stop the game music
        SoundHandler.stopGameBackgroundMusic();

        // Start the dead music
        SoundHandler.startDeadBackgroundMusic();
    }

        //endregion

    //endregion

    //region [Other Methods]

    /**
     * <Strong>This is used for setting the scenes size</Strong>
     * @param mapX is the maps x in tiles
     * @param mapY is the maps y in tiles
     */
    public static void setSceneSize(int mapX, int mapY){

        SceneHandler.mapX =mapX;
        SceneHandler.mapY =mapY;

    }

    /**
     * <Strong>This is used for setting the stages location</Strong>
     * @param gameOn is the boolean of wether or not the game is going on
     */
    public static void setSceneLocation(boolean gameOn){

        if (gameOn){
            SnakeGame.setStageLocation((screenX/2)-mapX*(tileSize/2),(screenY/2)-mapY*(tileSize/2));
        } else {
            SnakeGame.setStageLocation((screenX/2)-210,(screenY/2)-250);
        }

    }

    private static boolean input=false;
    private static boolean fourButtonOption = true;

    /**
     *<Strong>This is used for setting the buttons which is used in the scene</Strong>
     * @param snake is the current snake
     */
    public static void setButtons(Snake snake){

        // Whether or not the buttonlayout is 4 arrows or 2
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

    /**
     * <Strong>This is used for drawing on the canvas, this one is the color only draw method</Strong>
     * @param snake is the current snake
     * @param food is the current food
     */
    public static void drawCanvas(Snake snake, Food food){

        // The clearing of the canvas
        canvas.getGraphicsContext2D().clearRect(0,0,mapX*tileSize,mapY*tileSize);

        // The food gets drawn
        canvas.getGraphicsContext2D().setFill(food.getColor());
        canvas.getGraphicsContext2D().fillRect(food.getX()*tileSize,food.getY()*tileSize,tileSize,tileSize);

        // The snakes body gets drawn
        canvas.getGraphicsContext2D().setFill(Color.YELLOW);
        for (int i = 1; i<snake.getSnakeParts().size();i++){
            canvas.getGraphicsContext2D().fillRect(
                    snake.getSnakeParts().get(i).getX()*tileSize,
                    snake.getSnakeParts().get(i).getY()*tileSize, tileSize,tileSize)
            ;}

        // The head gets drawn
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        if (snake.getSnakeHead().isMultiHead()){
            // multiHead
            if (snake.getSnakeHead().getDir().equals(SnakePart.dir.up)||snake.getSnakeHead().getDir().equals(SnakePart.dir.down)){
                canvas.getGraphicsContext2D().fillRect(snake.getSnakeHead().getX()*tileSize-tileSize,snake.getSnakeHead().getY()*tileSize,tileSize*3,tileSize);
            } else {
                canvas.getGraphicsContext2D().fillRect(snake.getSnakeHead().getX()*tileSize,snake.getSnakeHead().getY()*tileSize-tileSize,tileSize,tileSize*3);
            }
        } else {
            // normalHead
            canvas.getGraphicsContext2D().fillRect(snake.getSnakeHead().getX()*tileSize,snake.getSnakeHead().getY()*tileSize,tileSize,tileSize);
        }

    }

    /**
     * <Strong>This is for drawing on the canvas, this one is for the imgs</Strong>
     * @param snake is the current snake
     * @param food is the current food
     */
    public static void drawCanvasIMG(Snake snake, Food food){

        // Clear the canvas
        canvas.getGraphicsContext2D().drawImage(ImageHandler.getMapImg(),0,0);

        // The food gets drawn
        canvas.getGraphicsContext2D().drawImage(ImageHandler.getFoodImg(food.getColor()),food.getX()*tileSize,food.getY()*tileSize);

        // The snakes body gets drawn
        for (int i = 1; i<snake.getSnakeParts().size();i++){
            canvas.getGraphicsContext2D().drawImage(ImageHandler.matchDirImg(snake,i,false),
                    snake.getSnakeParts().get(i).getX()*tileSize,
                    snake.getSnakeParts().get(i).getY()*tileSize, tileSize,tileSize);
        }

        // The snakes head gets drawn
        if (snake.getSnakeHead().isMultiHead()){
            if (snake.getSnakeHead().getDir().equals(SnakePart.dir.up)||snake.getSnakeHead().getDir().equals(SnakePart.dir.down)){
                canvas.getGraphicsContext2D().drawImage(ImageHandler.matchDirImg(snake,0,true),snake.getSnakeHead().getX()*tileSize-tileSize,snake.getSnakeHead().getY()*tileSize);
                canvas.getGraphicsContext2D().drawImage(ImageHandler.matchDirImg(snake,0,false),snake.getSnakeHead().getX()*tileSize,snake.getSnakeHead().getY()*tileSize);
                canvas.getGraphicsContext2D().drawImage(ImageHandler.matchDirImg(snake,0,true),snake.getSnakeHead().getX()*tileSize+tileSize,snake.getSnakeHead().getY()*tileSize);
            } else {
                canvas.getGraphicsContext2D().drawImage(ImageHandler.matchDirImg(snake,0,true),snake.getSnakeHead().getX()*tileSize,snake.getSnakeHead().getY()*tileSize-tileSize);
                canvas.getGraphicsContext2D().drawImage(ImageHandler.matchDirImg(snake,0,false),snake.getSnakeHead().getX()*tileSize,snake.getSnakeHead().getY()*tileSize);
                canvas.getGraphicsContext2D().drawImage(ImageHandler.matchDirImg(snake,0,true),snake.getSnakeHead().getX()*tileSize,snake.getSnakeHead().getY()*tileSize+tileSize);
            }
        } else {
            canvas.getGraphicsContext2D().drawImage(ImageHandler.matchDirImg(snake,0,false),snake.getSnakeHead().getX()*tileSize,snake.getSnakeHead().getY()*tileSize);
        }


    }

    //endregion

}
