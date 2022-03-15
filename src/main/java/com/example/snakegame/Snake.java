package com.example.snakegame;

import java.util.ArrayList;

/**
 * <Strong>This is the snake obj</Strong>
 */
public class Snake {

    //region [Class variable]

    public ArrayList<SnakePart> getSnakeParts() {
        return snakeParts;
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    private ArrayList<SnakePart> snakeParts = new ArrayList<>();

    private SnakeHead snakeHead;

    //endregion

    //region [Constructor]

    public Snake(){
        snakeParts.add(new SnakeHead());
        snakeHead = (SnakeHead) snakeParts.get(0);
    }

    //endregion

    //region [Add snake body Method]

    public void addSnakeBody(){

        int offSetX=0;
        int offSetY=0;

        // Sets the direction of the snakes body, when created
        switch (snakeParts.get(snakeParts.size()-1).getDir()){
            case up:
                offSetY=1;
                break;
            case down:
                offSetY=-1;
                break;
            case right:
                offSetX=-1;
                break;
            case left:
                offSetX=1;
                break;
        }

        // Adds a new snakebody to the snake
        snakeParts.add(new SnakeBody(
                getSnakeParts().get(getSnakeParts().size()-1).getX()+offSetX,
                getSnakeParts().get(getSnakeParts().size()-1).getY()+offSetY,
                getSnakeParts().get(getSnakeParts().size()-1).getDir()
        ));
    }

    //endregion

}
