package com.geeklife.congig;

/**
 * Created by cle99 on 15/06/2017.
 */

public class GameConfig {

    // -- constants --
    public static final float WIDTH = 720f;
    public static final float HEIGHT = 1215f;

    public static final float WORLD_WIDTH = 16f;
    public static final float WORLD_HEIGHT = 27f;

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2;
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2;

    public static final int CELL_SIZE = 1;

    public static final float PLANET_SIZE = 9f;
    public static final float PLANET_RADIUS = PLANET_SIZE / 2;

    public static final float MONSTER_SIZE = 1f;
    public static final float MONSTER_HALF_SIZE = MONSTER_SIZE / 2f;
    public static final float MONSTER_START_ANG_SPEED = 45f;
    public static final float MONSTER_START_ANGLE = 0f;

    public static final float JUMP_ACCELERATION = 3f;
    public static final float JUMP_HEIGHT = 2f;
    public static final float JUMP_TIME = 1f;




    // private constructor
    private GameConfig() {
    }
}
