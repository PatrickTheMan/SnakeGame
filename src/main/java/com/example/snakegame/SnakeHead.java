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

    public enum dir {
        up,
        down,
        right,
        left
    }

    public SnakeHead(){
        setDir(dir.right);
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

    public static void setDir(Enum input){

        if (input.equals(dir.up)){
            setUp(true);
            setDown(false);
            setRight(false);
            setLeft(false);
        } else if (input.equals(dir.down)){
            SnakeHead.setUp(false);
            SnakeHead.setDown(true);
            SnakeHead.setRight(false);
            SnakeHead.setLeft(false);
        } else if (input.equals(dir.left)){
            SnakeHead.setUp(false);
            SnakeHead.setDown(false);
            SnakeHead.setRight(false);
            SnakeHead.setLeft(true);
        } else if (input.equals(dir.right)){
            SnakeHead.setUp(false);
            SnakeHead.setDown(false);
            SnakeHead.setRight(true);
            SnakeHead.setLeft(false);
        }

    }

    public void moveToNextTile(){

        if (getDir()==dir.up){
            setLocation(getX(),getY()-1);
        } else if (getDir()==dir.down){
            setLocation(getX(),getY()+1);
        } else if (getDir()==dir.right){
            setLocation(getX()+1,getY());
        } else if (getDir()==dir.left){
            setLocation(getX()-1,getY());
        }

    }

    public boolean collisionWallCheck(int mapX, int mapY){

        if (getX()>mapX-1 || getX()<0 || getY()>mapY-1 || getY()<0){
            return true;
        }

        return false;
    }

    public boolean collisionBodyCheck(SnakePart snakePart){



        if (getX() == snakePart.getX() && getY() == snakePart.getY()){
            return true;
        }

        return false;
    }

    public boolean eatFood(Food food){

        if (getX() == food.getX() && getY() == food.getY()){
            return true;
        }

        return false;
    }


}
