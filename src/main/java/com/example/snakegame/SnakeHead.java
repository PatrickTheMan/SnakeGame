package com.example.snakegame;

/**
 * <Strong>This is the snakeHead obj, which extends the snakePart obj</Strong>
 */
public class SnakeHead extends SnakePart {

    //region [Gettes & Setters]

    public boolean isMultiHead() {
        return multiHead;
    }

    public void setMultiHead(boolean fatHead) {
        this.multiHead = fatHead;
    }

    public boolean isPhantomMode() {
        return phantomMode;
    }

    public void setPhantomMode(boolean phantomMode) {
        this.phantomMode = phantomMode;
    }

    //endregion

    //region [Class variables]

    boolean multiHead = false;

    private boolean phantomMode = false;

    //endregion

    //region [Constructor]

    public SnakeHead(){
        setDir(dir.right);
    }

    //endregion

    //region [Move Method]

    /**
     * <Strong>This is for moving the snakeHead to the next tile, decided via the current direction</Strong>
     */
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

        if (!phantomMode){
            if (getX() == snakePart.getX() && getY() == snakePart.getY()){
                return true;
            }
        }

        return false;
    }

    //endregion

    //region [Eat Food Method]

    /**
     * <Strong>This is for checking whether or not there is a food obj on the snakeHead's current position</Strong>
     * @param food is the current food obj
     * @return true if the snakeHead and food share position
     */
    public boolean eatFood(Food food){

        // Is there multiHead?
        if (multiHead){
            // multiHead
            switch (getDir()){
                case up:
                case down:
                    if ((getX()-1 == food.getX() || getX() == food.getX() || getX()+1 == food.getX()) && getY() == food.getY()){
                        return true;
                    }
                    break;
                case right:
                case left:
                    if (getX() == food.getX() && (getY()-1 == food.getY() || getY() == food.getY() || getY()+1 == food.getY())){
                        return true;
                    }
                    break;
            }

        } else {
            // normalHead
            if (getX() == food.getX() && getY() == food.getY()){
                return true;
            }
        }

        return false;
    }

    //endregion

}
