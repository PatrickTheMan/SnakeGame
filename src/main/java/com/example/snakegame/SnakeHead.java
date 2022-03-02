package com.example.snakegame;

public class SnakeHead {

    public static void setUp(boolean up) {
        SnakeHead.up = up;
    }

    public static void setDown(boolean down) {
        SnakeHead.down = down;
    }

    public static void setRight(boolean right) {
        SnakeHead.right = right;
    }

    public static void setLeft(boolean left) {
        SnakeHead.left = left;
    }

    private static boolean up=false,down=false,right=false,left=false;




}
