package com.geeklife.congig;

/**
 * Created by cle99 on 15/06/2017.
 */

public class GameConfig {

    // -- constants --
    public static final float WIDTH = 720f;
    public static final float HEIGHT = 1215f;

    public static final float HUD_WIDTH = 720f;
    public static final float HUD_HEIGHT = 1215f;

    public static final float WORLD_WIDTH = 16f;
    public static final float WORLD_HEIGHT = 27f;

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2;
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2;

    public static final int CELL_SIZE = 1;

    public static final float PLANET_SIZE = 9f;
    public static final float PLANET_RADIUS = PLANET_SIZE / 2;

    public static final float MONSTER_SIZE = 1f;
    public static final float MONSTER_RADIUS = MONSTER_SIZE / 2f;
    public static final float MONSTER_START_ANG_SPEED = 45f;
    public static final float START_ANGLE = 0f;

    // monster jump height to be just less than height to jump of side of screen
    public static final float JUMP_HEIGHT = WORLD_WIDTH/2 - PLANET_RADIUS - MONSTER_SIZE - 0.2f;
    // determines width of jump, lower time with lower tolerance to obstacles
    public static final float JUMP_TIME = 1f;

    public static final float COIN_SIZE = 1f;
    public static final float COIN_RADIUS = COIN_SIZE / 2f;
    public static final float COIN_SPAWN_TIME = 1.25f;
    public static final int MAX_COINS = 0;
    public static final int COIN_SCORE = 10;

    public static final  float OBSTACLE_SIZE = 1f;
    public static final float OBSTACLE_RADIUS = OBSTACLE_SIZE/2f;
    public static final float OBSTACLE_SPAWN_TIME = 0.75f;
    public static final int MAX_OBSTACLES = 3;
    public static final int OBSTACLE_SCORE = 5;

    public static final float MIN_ANGULAR_SEPERATION = 60f;



    // private constructor
    private GameConfig() {
    }
}
