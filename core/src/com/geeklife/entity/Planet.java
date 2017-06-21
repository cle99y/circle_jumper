package com.geeklife.entity;

import com.badlogic.gdx.math.Circle;
import com.geeklife.congig.GameConfig;

/**
 * Created by cle99 on 15/06/2017.
 */

public class Planet {

    // -- attributes --
    private float x;
    private float y;

    private float width = 1;
    private float height = 1;

    private Circle bounds;

    // -- constrictors --

    public Planet() {
        bounds = new Circle( x, y, GameConfig.PLANET_RADIUS );
    }

    public void setPosition( float x, float y ) {
        this.x = x;
        this.y = y;
        updateBounds();
    }

    public void setSize( float width, float height ) {
        this.width = width;
        this.height = height;
        updateBounds();
    }

    public float getX() {
        return x;
    }

    public void setX( float x ) {
        this.x = x;
        updateBounds();
    }

    public float getY() {
        return y;
    }

    public void setY( float y ) {
        this.y = y;
        updateBounds();
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Circle getBounds() {
        return bounds;
    }

    protected void updateBounds() {
        bounds.setPosition( x + getWidth() / 2, y + getHeight() / 2 );
    }
}
