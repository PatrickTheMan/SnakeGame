package com.example.snakegame;

public class SnakePart {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLocation(int x,int y, dir direction) {
        this.x = x;
        this.y = y;
        this.setDir(direction);
    }

    public void setLocation(int x,int y) {
        this.x = x;
        this.y = y;
    }

    private int x;
    private int y;

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    private boolean up=false,down=false,right=false,left=false;

    public enum dir {
        up,
        down,
        right,
        left
    }

    public void setDir(Enum input){

        if (input.equals(dir.up)){
            setUp(true);
            setDown(false);
            setRight(false);
            setLeft(false);
        } else if (input.equals(dir.down)){
            setUp(false);
            setDown(true);
            setRight(false);
            setLeft(false);
        } else if (input.equals(dir.left)){
            setUp(false);
            setDown(false);
            setRight(false);
            setLeft(true);
        } else if (input.equals(dir.right)){
            setUp(false);
            setDown(false);
            setRight(true);
            setLeft(false);
        }

    }

    public dir getDir(){

        if (up){
            return dir.up;
        } else if (down){
            return dir.down;
        } else if (left){
            return dir.left;
        } else{
            return dir.right;
        }

    }

}
