package com.example.snakegame;

/**
 * <Strong>This is the snakeBody obj, which extends the snakePart obj</Strong>
 */
public class SnakeBody extends SnakePart {

    //region [Constructor]

    /**
     * <Strong>This is used for creating a new snakeBody</Strong>
     * @param x is the start x position given in tiles
     * @param y is the start y position given in tiles
     * @param direction is the direction of the snakeBody
     */
    public SnakeBody(int x,int y, dir direction){
        setLocation(x,y,direction);
    }

    //endregion

    //region [Move Body To Next Position Method]

    /**
     * <Strong>This is used for moving a snakeBody to its next position, and change the direction</Strong>
     * @param snake is the current snake
     * @param num is the position of the snakeBody in the snake
     */
    public void moveToNextPosition(Snake snake, int num){
        SnakePart snakePart = snake.getSnakeParts().get(num-1);
        setLocation(snakePart.getX(), snakePart.getY(), snakePart.getDir());
    }

    //endregion

}
