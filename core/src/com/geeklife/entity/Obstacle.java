package com.geeklife.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.geeklife.congig.GameConfig;
import com.geeklife.util.entity.EntityTemplate;

/**
 * Created by cle99 on 23/06/2017.
 */

public class Obstacle extends EntityTemplate implements Pool.Poolable {

    // -- attributes --
    private float angle;
    private Rectangle sensor;
    private boolean hit;
    private boolean sensorHit;

    // -- constructors --
    public Obstacle() {
        setSize( GameConfig.OBSTACLE_SIZE, GameConfig.OBSTACLE_SIZE );
        sensor = new Rectangle();
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

    public boolean isSensorHit() {
        return sensorHit;
    }

    public void setSensorHit( boolean hit ) {
        this.sensorHit = hit;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    // -- public methods --
    public void setAngle( float angle ) {
        this.angle = angle;

        // obstacle
        float radius = GameConfig.PLANET_RADIUS;
        float originX = GameConfig.WORLD_CENTER_X;
        float originY = GameConfig.WORLD_CENTER_Y;

        float xPos = originX + MathUtils.sinDeg( angle ) * radius;
        float yPos = originY + MathUtils.cosDeg( angle ) * radius;

        setPosition( xPos, yPos );

        // sensor
        radius = GameConfig.PLANET_RADIUS + getHeight();

        xPos = originX + MathUtils.sinDeg( angle ) * radius;
        yPos = originY + MathUtils.cosDeg( angle ) * radius;

        sensor.set( xPos, yPos, getWidth(), GameConfig.JUMP_HEIGHT - getHeight() );

    }

    @Override
    public void reset() {
        hit = false;
        sensorHit=false;
    }

}
