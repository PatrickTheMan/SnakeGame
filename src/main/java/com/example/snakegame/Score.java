package com.example.snakegame;

public class Score {

    private String username = null;
    private int score = 0;

    public Score() {
    }

    public Score(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String firstName) {
        this.username = firstName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
