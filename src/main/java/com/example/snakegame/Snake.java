package com.example.snakegame;

import javafx.beans.property.Property;

import java.util.ArrayList;
import java.util.Properties;

public class Snake {

    public ArrayList<SnakePart> getSnakeParts() {
        return snakeParts;
    }

    public void setSnakeParts(ArrayList<SnakePart> snakeParts) {
        this.snakeParts = snakeParts;
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    private ArrayList<SnakePart> snakeParts = new ArrayList<>();

    private SnakeHead snakeHead;

    public Snake(){
        snakeParts.add(new SnakeHead());
        snakeHead = (SnakeHead) snakeParts.get(0);
    }

    public void addSnakeBody(){

        int offSetX;

        if (snakeHead.getDir().equals(SnakeHead.dir.right)){
            offSetX=1;
        } else {
            offSetX=-1;
        }

        snakeParts.add(new SnakeBody(
                getSnakeParts().get(getSnakeParts().size()-1).getX()-offSetX,
                getSnakeParts().get(getSnakeParts().size()-1).getY()
        ));
    }




}
