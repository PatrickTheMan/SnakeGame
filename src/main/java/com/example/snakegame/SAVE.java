package com.example.snakegame;

public class SAVE {

    /*

    int updateTimer=0;
    int speed = 1;

    public static boolean justColor=false;

    boolean rotateNow=false;

    private void gameLoop(){

        // Increment the timer
        updateTimer++;

        // If the timer hits the specified amount, then the games code runs
        if (updateTimer==11-speed){

            // Reset input if there has been one
            if (SceneHandler.hasInput()){
                SceneHandler.setInput(false);
            }

            // Move snake body
            if (snake.getSnakeParts().size()>0){
                for (int i = this.snake.getSnakeParts().size()-1; i>0;i--){
                    ((SnakeBody)snake.getSnakeParts().get(i)).moveToNextPosition(this.snake,i);
                }
            }


            // Move snake head
            snake.getSnakeHead().moveToNextTile();


            // Is there a wall collision?
            if (snake.getSnakeHead().collisionWallCheck(SceneHandler.getMapX(), SceneHandler.getMapY())){
                SoundHandler.playCollisionSound();
                ScoreHandler.setScore(snake.getSnakeParts().size()-1);

                snake = new Snake();
                speed=1;

                SceneHandler.setSceneDead();
                this.stop();
            }

            // Is there a body collision?
            for (int i = 1; i<snake.getSnakeParts().size();i++){
                if (snake.getSnakeHead().collisionBodyCheck(snake.getSnakeParts().get(i))){
                    SoundHandler.playCollisionSound();
                    ScoreHandler.setScore(snake.getSnakeParts().size()-1);

                    snake = new Snake();
                    speed=1;

                    SceneHandler.setSceneDead();
                    this.stop();
                }
            }


            // Is there food?
            if (snake.getSnakeHead().eatFood(food)){
                snake.addSnakeBody();
                speedUp(snake);

                // Is it blue?
                if (food.getColor().equals(Color.BLUE)){
                    // It is blue
                    SoundHandler.playFoodSound(food.getColor());
                    rotateNow=true;
                } else {
                    // It is red
                    SoundHandler.playFoodSound(food.getColor());
                }

                newFood();
            }



            // Rotate the map
            if (rotateNow){
                PowerUpHandler.flipMapPower(snake,food,SceneHandler.getMapX(),SceneHandler.getMapY());
                rotateNow=false;
            }

            // Draw function
            if (justColor){
                // Just color
                SceneHandler.drawCanvas(snake,food);
            } else {
                // Images
                SceneHandler.drawCanvasIMG(snake,food);
            }


            //Reset Timer
            updateTimer=0;
        }

    }

     */

}
