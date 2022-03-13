package com.example.snakegame;

import java.util.Random;

public class PowerUpHandler {

    public static void flipMapPower(Snake snake, Food food, int mapX, int mapY){

        Random r = new Random();


        switch (r.nextInt(0,3)){
            case 0:

                // Flipping x

                ImageHandler.rotateMap(90);

                switch (snake.getSnakeHead().getDir()){
                    case up:
                        snake.getSnakeHead().setLocation(mapX-1-snake.getSnakeHead().getX(),snake.getSnakeHead().getY(), SnakePart.dir.up);
                        break;
                    case down:
                        snake.getSnakeHead().setLocation(mapX-1-snake.getSnakeHead().getX(),snake.getSnakeHead().getY(), SnakePart.dir.down);
                        break;
                    case right:
                        snake.getSnakeHead().setLocation(mapX-1-snake.getSnakeHead().getX(),snake.getSnakeHead().getY(), SnakePart.dir.left);
                        break;
                    case left:
                        snake.getSnakeHead().setLocation(mapX-1-snake.getSnakeHead().getX(),snake.getSnakeHead().getY(), SnakePart.dir.right);
                        break;
                }

                for (int i = 1; i<snake.getSnakeParts().size();i++){

                    SnakePart snakePart = snake.getSnakeParts().get(i);

                    switch (snakePart.getDir()){
                        case up:
                            snakePart.setLocation(mapX-1-snakePart.getX(),snakePart.getY(), SnakePart.dir.up);
                            break;
                        case down:
                            snakePart.setLocation(mapX-1-snakePart.getX(),snakePart.getY(), SnakePart.dir.down);
                            break;
                        case right:
                            snakePart.setLocation(mapX-1-snakePart.getX(),snakePart.getY(), SnakePart.dir.left);
                            break;
                        case left:
                            snakePart.setLocation(mapX-1-snakePart.getX(),snakePart.getY(), SnakePart.dir.right);
                            break;
                    }

                }

                food.setLocation(mapX-1-food.getX(),food.getY());

                break;
            case 1:

                // Flipping y

                ImageHandler.rotateMap(180);

                switch (snake.getSnakeHead().getDir()){
                    case up:
                        snake.getSnakeHead().setLocation(snake.getSnakeHead().getX(),mapY-1-snake.getSnakeHead().getY(), SnakePart.dir.down);
                        break;
                    case down:
                        snake.getSnakeHead().setLocation(snake.getSnakeHead().getX(),mapY-1-snake.getSnakeHead().getY(), SnakePart.dir.up);
                        break;
                    case right:
                        snake.getSnakeHead().setLocation(snake.getSnakeHead().getX(),mapY-1-snake.getSnakeHead().getY(), SnakePart.dir.right);
                        break;
                    case left:
                        snake.getSnakeHead().setLocation(snake.getSnakeHead().getX(),mapY-1-snake.getSnakeHead().getY(), SnakePart.dir.left);
                        break;
                }

                for (int i = 1; i<snake.getSnakeParts().size();i++){

                    SnakePart snakePart = snake.getSnakeParts().get(i);

                    switch (snakePart.getDir()){
                        case up:
                            snakePart.setLocation(snakePart.getX(),mapY-1- snakePart.getY(), SnakePart.dir.down);
                            break;
                        case down:
                            snakePart.setLocation(snakePart.getX(),mapY-1- snakePart.getY(), SnakePart.dir.up);
                            break;
                        case right:
                            snakePart.setLocation(snakePart.getX(),mapY-1- snakePart.getY(), SnakePart.dir.right);
                            break;
                        case left:
                            snakePart.setLocation(snakePart.getX(),mapY-1- snakePart.getY(), SnakePart.dir.left);
                            break;
                    }

                }

                food.setLocation(food.getX(),mapY-1-food.getY());

                break;
            case 2:

                // Flipping x and y

                ImageHandler.rotateMap(270);

                switch (snake.getSnakeHead().getDir()){
                    case up:
                        snake.getSnakeHead().setLocation(mapX-1-snake.getSnakeHead().getX(),mapY-1-snake.getSnakeHead().getY(), SnakePart.dir.down);
                        break;
                    case down:
                        snake.getSnakeHead().setLocation(mapX-1-snake.getSnakeHead().getX(),mapY-1-snake.getSnakeHead().getY(), SnakePart.dir.up);
                        break;
                    case right:
                        snake.getSnakeHead().setLocation(mapX-1-snake.getSnakeHead().getX(),mapY-1-snake.getSnakeHead().getY(), SnakePart.dir.left);
                        break;
                    case left:
                        snake.getSnakeHead().setLocation(mapX-1-snake.getSnakeHead().getX(),mapY-1-snake.getSnakeHead().getY(), SnakePart.dir.right);
                        break;
                }

                for (int i = 1; i<snake.getSnakeParts().size();i++){

                    SnakePart snakePart = snake.getSnakeParts().get(i);

                    switch (snakePart.getDir()){
                        case up:
                            snakePart.setLocation(mapX-1- snakePart.getX(),mapY-1- snakePart.getY(), SnakePart.dir.down);
                            break;
                        case down:
                            snakePart.setLocation(mapX-1- snakePart.getX(),mapY-1- snakePart.getY(), SnakePart.dir.up);
                            break;
                        case right:
                            snakePart.setLocation(mapX-1- snakePart.getX(),mapY-1- snakePart.getY(), SnakePart.dir.left);
                            break;
                        case left:
                            snakePart.setLocation(mapX-1- snakePart.getX(),mapY-1- snakePart.getY(), SnakePart.dir.right);
                            break;
                    }

                }

                food.setLocation(mapX-1-food.getX(),mapY-1-food.getY());

                break;
        }


    }

}
