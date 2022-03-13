package com.example.snakegame;

import javafx.scene.paint.Color;

import java.util.Random;

public class Food {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return c;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void newFoodAndLocation(int x, int y, boolean powerUp) {
        this.x = x;
        this.y = y;

        if (powerUp){
            Random r = new Random();

            if (r.nextInt(0,100)>80){
                c = Color.BLUE;
            } else {
                c = Color.RED;
            }
        }

    }

    private int x;
    private int y;
    private Color c;

    public Food(int x, int y, Color c){
        this.x = x;
        this.y = y;
        this.c=c;
    }

}
