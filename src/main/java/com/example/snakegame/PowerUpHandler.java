package com.example.snakegame;

import java.util.Random;

public class PowerUpHandler {

    public static void flipMapPower(Snake snake, Food food, int mapX, int mapY){

        Random r = new Random();


        switch (r.nextInt(0,3)){
            case 0:

                // Flipping x
                snake.getSnakeHead().setLocation(mapX-1-snake.getSnakeHead().getX(),snake.getSnakeHead().getY());

                switch (snake.getSnakeHead().getDir()){
                    case up:
                        snake.getSnakeHead().setDir(SnakeHead.dir.up);
                        break;
                    case down:
                        snake.getSnakeHead().setDir(SnakeHead.dir.down);
                        break;
                    case right:
                        snake.getSnakeHead().setDir(SnakeHead.dir.left);
                        break;
                    case left:
                        snake.getSnakeHead().setDir(SnakeHead.dir.right);
                        break;
                }

                for (int i = 1; i<snake.getSnakeParts().size();i++){

                    snake.getSnakeParts().get(i).setLocation(mapX-1-snake.getSnakeParts().get(i).getX(),snake.getSnakeParts().get(i).getY());
                }

                food.setLocation(mapX-1-food.getX(),food.getY());

                break;
            case 1:

                // Flipping y
                snake.getSnakeHead().setLocation(snake.getSnakeHead().getX(),mapY-1-snake.getSnakeHead().getY());

                switch (snake.getSnakeHead().getDir()){
                    case up:
                        snake.getSnakeHead().setDir(SnakeHead.dir.down);
                        break;
                    case down:
                        snake.getSnakeHead().setDir(SnakeHead.dir.up);
                        break;
                    case right:
                        snake.getSnakeHead().setDir(SnakeHead.dir.right);
                        break;
                    case left:
                        snake.getSnakeHead().setDir(SnakeHead.dir.left);
                        break;
                }

                for (int i = 1; i<snake.getSnakeParts().size();i++){

                    snake.getSnakeParts().get(i).setLocation(snake.getSnakeParts().get(i).getX(),mapY-1-snake.getSnakeParts().get(i).getY());
                }

                food.setLocation(food.getX(),mapY-1-food.getY());

                break;
            case 2:

                // Flipping x and y
                snake.getSnakeHead().setLocation(mapX-1-snake.getSnakeHead().getX(),mapY-1-snake.getSnakeHead().getY());

                switch (snake.getSnakeHead().getDir()){
                    case up:
                        snake.getSnakeHead().setDir(SnakeHead.dir.down);
                        break;
                    case down:
                        snake.getSnakeHead().setDir(SnakeHead.dir.up);
                        break;
                    case right:
                        snake.getSnakeHead().setDir(SnakeHead.dir.left);
                        break;
                    case left:
                        snake.getSnakeHead().setDir(SnakeHead.dir.right);
                        break;
                }

                for (int i = 1; i<snake.getSnakeParts().size();i++){

                    snake.getSnakeParts().get(i).setLocation(mapX-1-snake.getSnakeParts().get(i).getX(),mapY-1-snake.getSnakeParts().get(i).getY());
                }

                food.setLocation(mapX-1-food.getX(),mapY-1-food.getY());

                break;
        }


    }

}
