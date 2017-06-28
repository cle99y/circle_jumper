package com.geeklife.common;

/**
 * Created by cle99 on 21/06/2017.
 */

public class GameManager {

    public static final GameManager INSTANCE = new GameManager();

    // -- attributes --
    private int score;
    private int displayScore;
    private int highScore;
    private int displayHighScore;
    private boolean gameOver;

    // -- constructors --
    private GameManager() {
    }

    // -- public methods --
    public void reset() {
        score = 0;
        displayScore = 0;
        gameOver = false;
    }

    public void addToScore( int amount ) {
        score += amount;
        if ( score > highScore ) {
            highScore = score;
        }
    }

    public void updateDisplayScore( float delta ) {
        if ( displayScore < score ) {
            displayScore = Math.min( score, displayScore * ( int ) ( 100 * delta ) );
        }
    }

    public void updateDisplayHighScore( float delta ) {
        if ( displayHighScore < highScore ) {
            displayHighScore = Math.min( highScore, displayHighScore * ( int ) ( 100 * delta ) );
        }
    }


    public int getDisplayScore() {
        return displayScore;
    }

    public int getDisplayHighScore() {
        return displayHighScore;
    }

    public void setGameOver( boolean gameOver ) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}

