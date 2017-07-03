package com.geeklife.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;
import com.geeklife.congig.GameConfig;
import com.geeklife.util.entity.EntityTemplate;

/**
 * Created by cle99 on 21/06/2017.
 */

public class Coin extends EntityTemplate implements Pool.Poolable {

    private float angle = 0f;
    private boolean hit;


    // -- constructors --
    public Coin() {
        setSize( GameConfig.COIN_SIZE, GameConfig.COIN_SIZE );
    }

    public float getAngle() {
        return angle;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit( boolean hit ) {
        this.hit = hit;
    }

    // -- public methods --
    public void setAngle( float angle ) {
        this.angle = angle;
        float radius = GameConfig.PLANET_RADIUS;
        float originX = GameConfig.WORLD_CENTER_X;
        float originY = GameConfig.WORLD_CENTER_Y;

        float xPos = originX + MathUtils.sinDeg( angle ) * radius;
        float yPos = originY + MathUtils.cosDeg( angle ) * radius;

        setPosition( xPos, yPos );

    }

    @Override
    public void reset() {

    }
}
