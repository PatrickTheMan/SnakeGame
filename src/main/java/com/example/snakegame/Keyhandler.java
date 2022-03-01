package com.example.snakegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyhandler implements KeyListener {

    //region [Variables]
    boolean upPressed,downPressed,leftPressed,rightPressed;
    //endregion

    //region [keyTyped]
    @Override
    public void keyTyped(KeyEvent e) {

    }
    //endregion

    //region [keyPressed]
    @Override
    public void keyPressed(KeyEvent e) {

        // Player Movement
        int keyCode = e.getKeyCode();

        //Change boolean values to true, when pressed
        if (keyCode == KeyEvent.VK_W ||keyCode == KeyEvent.VK_UP) upPressed=true;
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) downPressed=true;
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) leftPressed=true;
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) rightPressed=true;

    }
    //endregion

    //region [keyReleased]
    @Override
    public void keyReleased(KeyEvent e) {

        // Player Movement
        int keyCode = e.getKeyCode();

        //Change boolean values to false when released
        if (keyCode == KeyEvent.VK_W ||keyCode == KeyEvent.VK_UP) upPressed=false;
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) downPressed=false;
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) leftPressed=false;
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) rightPressed=false;

    }
    //endregion

}
