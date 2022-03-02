package com.example.snakegame;

import java.util.ArrayList;

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

    private SnakeHead snakeHead = new SnakeHead();

    public void addSnakeHead(){
        if (snakeParts.size()==0){
            snakeParts.add(new SnakeHead());
        } else {
            System.out.println("Snake Already has a head");
        }
    }

    public void addBodyPart(){
        snakeParts.add(new SnakeBody());
    }




}
