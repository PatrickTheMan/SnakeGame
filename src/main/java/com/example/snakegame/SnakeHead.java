package com.example.snakegame;

public class SnakeHead extends SnakePart {

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

    enum dir {
        up,
        down,
        right,
        left
    }

    public dir getDir(){

        if (up){
            return dir.up;
        } else if (down){
            return dir.down;
        } else if (right){
            return dir.right;
        } else{
            return dir.left;
        }

    }

    public void moveToNextTile(){

        if (getDir()==dir.up){
            setY(getY()-1);
        } else if (getDir()==dir.down){
            setY(getY()+1);
        } else if (getDir()==dir.right){
            setX(getX()+1);
        } else if (getDir()==dir.left){
            setX(getX()-1);
        }

    }


}
