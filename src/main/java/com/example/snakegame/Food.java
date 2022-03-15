package com.example.snakegame;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * <Strong>This is the food obj</Strong>
 */
public class Food {

    //region [Getters & Setters]

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

    //endregion

    //region [Class variables]

    private int x;
    private int y;
    private Color c;

    //endregion

    //region [Constructor]

    /**
     * <Strong>This is used for </Strong>
     * @param x is the specified new x location (given in tiles)
     * @param y is the specified new y location (given in tiles)
     * @param c is the color of the food
     */
    public Food(int x, int y, Color c){
        this.x = x;
        this.y = y;
        this.c=c;
    }

    //endregion

    //region [Methods]

    /**
     * <Strong>This is used for moving the food and change its type randomly</Strong>
     * @param x is the specified new x location (given in tiles)
     * @param y is the specified new y location (given in tiles)
     * @param powerUp is the boolean than turns powerUps on (true) or off (false)
     */
    public void newFoodAndLocation(int x, int y, boolean powerUp) {
        this.x = x;
        this.y = y;

        // If powerUp is true, then the food can spawn as non-reds
        if (powerUp){
            Random r = new Random();

            int type = r.nextInt(0,100);

            if (type>=85){
                c = Color.BLUE;
            } else if (type>=75){
                c = Color.GREEN;
            } else if (type>=70){
                c = Color.YELLOW;
            } else {
                c = Color.RED;
            }
        }
    }

    //endregion

}
