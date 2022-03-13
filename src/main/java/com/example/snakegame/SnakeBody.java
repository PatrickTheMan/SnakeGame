package com.example.snakegame;

public class SnakeBody extends SnakePart {

    public SnakeBody(int x,int y){
        setLocation(x,y);
    }

    public void moveToNextPosition(Snake snake, int num){
        setLocation(snake.getSnakeParts().get(num-1).getX(),snake.getSnakeParts().get(num-1).getY());
    }

}
