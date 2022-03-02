package com.example.snakegame;

import javafx.animation.AnimationTimer;

public class GameEngine extends AnimationTimer {

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    Snake snake;

    public GameEngine(){

        newSnake();

    }

    public void newSnake(){
        if (snake==null){
            System.out.println("new snake made");
            this.snake=new Snake();
            this.snake.addSnakeHead();
        }

    }

    @Override
    public void handle(long l) {

        gameLoop();
    }


    int updateTimer=0;

    private void gameLoop(){

        updateTimer++;

        if (updateTimer==20){

            // Move snake head
            snake.getSnakeHead().moveToNextTile();

            // Move snake body
            //---



            System.out.println("X: "+snake.getSnakeHead().getX()+" / Y: "+snake.getSnakeHead().getY());


            //Reset Timer
            updateTimer=0;
        }

    }

}
