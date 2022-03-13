package com.example.snakegame;

public class SnakePart {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLocation(int x,int y) {
        this.x = x;
        this.y = y;
    }

    private int x;
    private int y;

}
