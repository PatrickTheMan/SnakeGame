package com.example.snakegame;

public class SnakeBody extends SnakePart {

    public SnakeBody(int x,int y, dir direction){
        setLocation(x,y,direction);
    }

    public void moveToNextPosition(Snake snake, int num){
        SnakePart snakePart = snake.getSnakeParts().get(num-1);
        setLocation(snakePart.getX(), snakePart.getY(), snakePart.getDir());
    }

}
