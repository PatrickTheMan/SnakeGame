package com.example.snakegame;

public class SnakeHead extends SnakePart {

    //region [Constructor]

    public SnakeHead(){
        setDir(dir.right);
    }

    //endregion

    //region [Move Method]

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

    //endregion

    //region [Collision Methods]

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

    //endregion

    //region [Eat Food Method]

    public boolean eatFood(Food food){

        if (getX() == food.getX() && getY() == food.getY()){
            return true;
        }

        return false;
    }

    //endregion

}
