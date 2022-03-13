package com.example.snakegame;

import javafx.beans.property.Property;

import java.util.ArrayList;
import java.util.Properties;

public class Snake {

    public ArrayList<SnakePart> getSnakeParts() {
        return snakeParts;
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

        int offSetX=0;
        int offSetY=0;

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

        snakeParts.add(new SnakeBody(
                getSnakeParts().get(getSnakeParts().size()-1).getX()+offSetX,
                getSnakeParts().get(getSnakeParts().size()-1).getY()+offSetY,
                getSnakeParts().get(getSnakeParts().size()-1).getDir()
        ));
    }




}
