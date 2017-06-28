package com.geeklife.entity;

/**
 * Created by cle99 on 19/06/2017.
 */

public enum MonsterState {
    WALKING,
    JUMPING,
    LANDED;

    // -- public methods --
    public boolean isWalking() {
        return this == WALKING;
    }

    public boolean isJumping() {
        return this == JUMPING;
    }

    public boolean isLanded() {
        return this == LANDED;
    }

}
