package com.example.snakegame;

public class Score {

   //region [Getter & Setters]

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

   //endregion

    //region [Variables]

    private String username = null;
    private int score = 0;

    //endregion

    //region [Constructors]

    public Score() {
    }

    public Score(String username, int score) {
        this.username = username;
        this.score = score;
    }

    //endregion

}
