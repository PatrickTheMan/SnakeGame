package com.example.snakegame;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;

public class SoundHandler {

    public static int masterVolume = 3;
    public static boolean mute = false;

    public static MediaPlayer gameBackgroundMusic;

    public static void stopGameBackgroundMusic(){

        if (gameBackgroundMusic != null) {
            gameBackgroundMusic.stop();
            gameBackgroundMusic.dispose();
            gameBackgroundMusic = null;
        }

    }

    public static void startGameBackgroundMusic(){
        if (gameBackgroundMusic == null){
            Media sound = new Media(new File("src/main/resources/Sound/Arcade-Oddities.mp3").toURI().toString());

            gameBackgroundMusic = new MediaPlayer(sound);

            if (mute){
                gameBackgroundMusic.setVolume(0);
            } else {
                gameBackgroundMusic.setVolume(0.03*masterVolume);
            }

            gameBackgroundMusic.setOnEndOfMedia(() -> gameBackgroundMusic.seek(Duration.ZERO));

            gameBackgroundMusic.play();
        }
    }

    public static MediaPlayer menuBackgroundMusic;

    public static void stopMenuBackgroundMusic(){

        if (menuBackgroundMusic != null) {
            menuBackgroundMusic.stop();
            menuBackgroundMusic.dispose();
            menuBackgroundMusic = null;
        }

    }

    public static void startMenuBackgroundMusic(){

        if (menuBackgroundMusic == null){
            Media sound = new Media(new File("src/main/resources/Sound/Pixel-Madness.mp3").toURI().toString());

            menuBackgroundMusic = new MediaPlayer(sound);

            if (mute){
                menuBackgroundMusic.setVolume(0);
            } else {
                menuBackgroundMusic.setVolume(0.03 * masterVolume);
            }

            menuBackgroundMusic.setOnEndOfMedia(() -> menuBackgroundMusic.seek(Duration.ZERO));

            menuBackgroundMusic.play();
        }

    }

    public static MediaPlayer deadBackgroundMusic;

    public static void stopDeadBackgroundMusic(){

        if (deadBackgroundMusic != null) {
            deadBackgroundMusic.stop();
            deadBackgroundMusic.dispose();
            deadBackgroundMusic = null;
        }

    }

    public static void startDeadBackgroundMusic(){

        if (deadBackgroundMusic == null){
            Media sound = new Media(new File("src/main/resources/Sound/After-the-Invasion_Looping.mp3").toURI().toString());

            deadBackgroundMusic = new MediaPlayer(sound);

            if (mute){
                deadBackgroundMusic.setVolume(0);
            } else {
                deadBackgroundMusic.setVolume(0.03 * masterVolume);
            }

            deadBackgroundMusic.setOnEndOfMedia(() -> deadBackgroundMusic.seek(Duration.ZERO));

            deadBackgroundMusic.play();
        }

    }

    public static MediaPlayer foodSound;

    public static void playFoodSound(Color color){

        Media sound = new Media(new File("src/main/resources/Sound/PowerUp16 cut.mp3").toURI().toString());

        foodSound = new MediaPlayer(sound);

        foodSound.setVolume(0.015 * masterVolume);

        if (color.equals(Color.BLUE)){
            sound = new Media(new File("src/main/resources/Sound/Creepy4.mp3").toURI().toString());

            foodSound = new MediaPlayer(sound);

            foodSound.setVolume(0.03 * masterVolume);
        }

        if (mute){
            foodSound.setVolume(0);
        }

        foodSound.play();

    }

    public static MediaPlayer collisionSound;

    public static void playCollisionSound(){

        Media sound = new Media(new File("src/main/resources/Sound/SynthChime3.mp3").toURI().toString());

        collisionSound = new MediaPlayer(sound);

        if (mute){
            collisionSound.setVolume(0);
        } else {
            collisionSound.setVolume(0.05 * masterVolume);
        }

        collisionSound.play();
    }

    public static MediaPlayer speedUpSound;

    public static void playSpeedUp(){

        Media sound = new Media(new File("src/main/resources/Sound/Bells4.mp3").toURI().toString());

        speedUpSound = new MediaPlayer(sound);

        if (mute){
            speedUpSound.setVolume(0);
        } else {
            speedUpSound.setVolume(0.02 * masterVolume);
        }

        speedUpSound.play();
    }

    public static MediaPlayer buttonSound;

    public static void playButtonSound(){

        Media sound = new Media(new File("src/main/resources/Sound/RezAlert1.mp3").toURI().toString());

        buttonSound = new MediaPlayer(sound);

        if (mute){
            buttonSound.setVolume(0);
        } else {
            buttonSound.setVolume(0.025 * masterVolume);
        }

        buttonSound.play();
    }

}
