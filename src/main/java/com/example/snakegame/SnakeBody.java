package com.example.snakegame;

public class SnakeBody extends SnakePart {

    //region [Constructor]

    public SnakeBody(int x,int y, dir direction){
        setLocation(x,y,direction);
    }

    //endregion

    //region [Move Body To Next Position Method]

    public void moveToNextPosition(Snake snake, int num){
        SnakePart snakePart = snake.getSnakeParts().get(num-1);
        setLocation(snakePart.getX(), snakePart.getY(), snakePart.getDir());
    }

    //endregion

}
