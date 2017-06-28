package com.jga.jumper.entity;

/**
 * Created by goran on 4/10/2016.
 */

public enum MonsterState {
    WALKING,
    JUMPING,
    FALLING;

    // == public methods ==
    public boolean isWalking() {
        return this == WALKING;
    }

    public boolean isJumping() {
        return this == JUMPING;
    }

    public boolean isFalling() {
        return this == FALLING;
    }
}
