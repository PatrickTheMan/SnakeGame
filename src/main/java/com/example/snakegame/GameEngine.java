package com.example.snakegame;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * <Strong>This is the games loop, it will play 60 times a secound as it extends AnimationTimer</Strong>
 */
public class GameEngine extends AnimationTimer {

    //region [Getters & Setters]

    public static Snake getSnake(){
        return snake;
    }

    //endregion

    //region [Class variables]

    private static Snake snake;
    private static Food food;

    //endregion

    //region [Constructor]

    /**
     * <Strong>This is used for creating a new gameEngine, which makes a new snake, new food and sets the buttons</Strong>
     */
    public GameEngine(){

        // Create a new snake obj
        newSnake();

        // Create a new food obj
        newFood();

        // Set the scenes buttons to the chosen layout
        SceneHandler.setButtons(snake);

    }

    //endregion

    //region [New Snake Method]

    public void newSnake(){
        snake=new Snake();
    }

    //endregion

    //region [New Food Method]

    public static boolean powerUpEnabled=true;

    /**
     * <Strong>This is used for getting a new location and maybe a new type of food aswell</Strong>
     */
    public static void newFood(){
        Random r = new Random();
        boolean canSpawn;

        // If the food doesn't exist, then it gets created
        if (food==null){
            // Creates a new standard red food
            food=new Food(
                    r.nextInt(SceneHandler.getMapX()),
                    r.nextInt(SceneHandler.getMapY()),
                    Color.RED
            );
        } else {
            // Try to change the location of the food, if it spawn in a wrong place, then it will be redone
            do {
                // Set new location
                food.newFoodAndLocation(
                        r.nextInt(SceneHandler.getMapX()),
                        r.nextInt(SceneHandler.getMapY()),
                        powerUpEnabled
                );

                // Reset canSpawn boolean for next search
                canSpawn=true;

                // Test if the location is available else find another
                for (SnakePart snakePart : snake.getSnakeParts()) {
                    if (snakePart.getX() == food.getX() && snakePart.getY() == food.getY()) {
                        canSpawn = false;
                    }
                }
            } while (!canSpawn);
        }
    }

    //endregion

    //region [Handle Method]

    /**
     * <Strong>This is the loop, it gets repeated 60 times a sec</Strong>
     * @param l
     */
    @Override
    public void handle(long l) {
        // The Gameloop
        gameLoop();
    }

    //endregion

    //region [SpeedUp Method]

    /**
     * <Strong>This is used for speeding up the snake (the games loop speed)</Strong>
     * @param snake is the current snake
     */
    public void speedUp(Snake snake){
        // Limits the speed amount to 9
        if (speed!=9 && snake.getSnakeParts().size()%25==0){
            SoundHandler.playSpeedUp();
            speed++;
        }
    }

    //endregion

    //region [Gameloop]

    int updateTimer=0;
    int speed = 1;

    public static boolean justColor=false;

    public static boolean inputDelay=false;

    boolean rotateNow=false;

    boolean multiHead = false;
    int multiHeadTimer = 60;

    boolean phantomMode = false;
    int phantomModeTimer = 60;

    /**
     * <Strong>This is the main code of the gameLoop, this is all the code that gets run when the gameScene is active</Strong>
     */
    private void gameLoop(){

        // Increment the timer
        updateTimer++;

        // Reset input if there has been one
        if (SceneHandler.hasInput() && !inputDelay){
            SceneHandler.setInput(false);
        }

        // If the timer hits the specified amount, then the games code runs
        if (updateTimer==11-speed){

            // Reset input if there has been one
            if (SceneHandler.hasInput() && inputDelay){
                SceneHandler.setInput(false);
            }

            // Move snake body
            if (snake.getSnakeParts().size()>0){
                for (int i = this.snake.getSnakeParts().size()-1; i>0;i--){
                    ((SnakeBody)snake.getSnakeParts().get(i)).moveToNextPosition(this.snake,i);
                }
            }


            // Move snake head
            snake.getSnakeHead().moveToNextTile();


            // Is there a wall collision?
            if (snake.getSnakeHead().collisionWallCheck(SceneHandler.getMapX(), SceneHandler.getMapY())){
                SoundHandler.playCollisionSound();
                ScoreHandler.setScore(snake.getSnakeParts().size()-1);

                snake = new Snake();
                speed=1;

                SceneHandler.setSceneDead();
                this.stop();
            }

            // Is there a body collision?
            for (int i = 1; i<snake.getSnakeParts().size();i++){
                if (snake.getSnakeHead().collisionBodyCheck(snake.getSnakeParts().get(i))){
                    SoundHandler.playCollisionSound();
                    ScoreHandler.setScore(snake.getSnakeParts().size()-1);

                    snake = new Snake();
                    speed=1;

                    SceneHandler.setSceneDead();
                    this.stop();
                }
            }


            // Is there food?
            if (snake.getSnakeHead().eatFood(food)){
                snake.addSnakeBody();
                speedUp(snake);

                // Is it blue?
                if (food.getColor().equals(Color.BLUE)){
                    // It is blue
                    SoundHandler.playFoodSound(food.getColor());
                    rotateNow=true;
                } else if (food.getColor().equals(Color.GREEN)){
                    // It is green
                    SoundHandler.playFoodSound(food.getColor());
                    multiHead=true;
                } else if (food.getColor().equals(Color.YELLOW)){
                    // It is yellow
                    SoundHandler.playFoodSound(food.getColor());
                    PowerUpHandler.bonusFood(snake);
                } else if (food.getColor().equals(Color.PINK)){
                    // It is Pink
                    SoundHandler.playFoodSound(food.getColor());
                    phantomMode=true;
                } else {
                    // It is red
                    SoundHandler.playFoodSound(food.getColor());
                }

                // New food as the old has been eaten
                newFood();
            }

            // activate multiHead and start the timer & End the multiHead when it is done
            if (phantomMode){
                phantomModeTimer=59;
                PowerUpHandler.phantomPowerActive(snake);
                phantomMode=false;
            } else if (phantomModeTimer<60) {
                phantomModeTimer--;
                if (phantomModeTimer==0){
                    phantomModeTimer=60;
                    PowerUpHandler.phantomPowerDisable(snake);
                }
            }

            // activate multiHead and start the timer & End the multiHead when it is done
            if (multiHead){
                multiHeadTimer=59;
                PowerUpHandler.multiHeadPowerActive(snake);
                multiHead=false;
            } else if (multiHeadTimer<60) {
                multiHeadTimer--;
                if (multiHeadTimer==0){
                    multiHeadTimer=60;
                    PowerUpHandler.multiHeadPowerDisable(snake);
                }
            }

            // Rotate the map
            if (rotateNow){
                PowerUpHandler.flipMapPower(snake,food,SceneHandler.getMapX(),SceneHandler.getMapY());
                rotateNow=false;
            }

            // Draw function
            if (justColor){
                // Just color
                SceneHandler.drawCanvas(snake,food);
            } else {
                // Images
                SceneHandler.drawCanvasIMG(snake,food);
            }


            //Reset Timer
            updateTimer=0;
        }

    }

    //endregion

}
