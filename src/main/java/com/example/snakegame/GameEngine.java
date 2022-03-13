package com.example.snakegame;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

import java.util.Random;

public class GameEngine extends AnimationTimer {

    public static Snake getSnake(){
        return snake;
    }

    private static Snake snake;
    private static Food food;

    private static boolean fourArrowNav=true;


    public GameEngine(){

        newSnake();
        newFood();
        SceneHandler.setButtons(snake);

    }

    public void newSnake(){
        snake=new Snake();
    }

    public static boolean powerUpEnabled=true;

    public static void newFood(){
        Random r = new Random();
        boolean canSpawn;

        if (food==null){
            food=new Food(
                    r.nextInt(SceneHandler.getMapX()),
                    r.nextInt(SceneHandler.getMapY()),
                    Color.RED
            );
        } else {
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

    @Override
    public void handle(long l) {

        gameLoop();
    }

    public void speedUp(Snake snake){
        if (speed!=9 && snake.getSnakeParts().size()%15==0){
            SoundHandler.playSpeedUp();
            speed++;
        }
    }

    int updateTimer=0;
    int speed = 1;

    boolean rotateNow=false;

    private void gameLoop(){

        updateTimer++;

        if (updateTimer==11-speed){

            // Reset input if there has been one
            if (SceneHandler.hasInput()){
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

                if (food.getColor().equals(Color.BLUE)){
                    SoundHandler.playFoodSound(food.getColor());
                    rotateNow=true;
                } else {
                    SoundHandler.playFoodSound(food.getColor());
                }

                newFood();
            }



            // Rotate the map
            if (rotateNow){
                PowerUpHandler.flipMapPower(snake,food,SceneHandler.getMapX(),SceneHandler.getMapY());
                rotateNow=false;
            }

            // Draw function
            SceneHandler.drawCanvas(snake,food);

            //Reset Timer
            updateTimer=0;
        }

    }

}
