package com.geeklife.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Logger;
import com.geeklife.congig.GameConfig;
import com.geeklife.util.entity.EntityTemplate;

/**
 * Created by cle99 on 16/06/2017.
 */

public class Monster extends EntityTemplate {

    private static final Logger log = new Logger( Monster.class.getName(), Logger.DEBUG );

    // -- attributes --
    private float angleDeg = 0;
    private float angularSpeed = GameConfig.MONSTER_START_ANG_SPEED;
    private float jumpTime;
    private float radius = GameConfig.PLANET_RADIUS;
    private MonsterState state = MonsterState.WALKING;

    public Monster() {
        setSize( GameConfig.MONSTER_SIZE, GameConfig.MONSTER_SIZE );
    }

    // -- public methods --
    public void update( float delta ) {

        angleDeg += angularSpeed * delta;
        if ( state.isJumping() ) {
            jumpTime += delta;

            if ( jumpTime >= GameConfig.JUMP_TIME ) {
                log.debug( "about to walk" );
                // reset parameters for walking
                jumpTime = 0;
                radius = GameConfig.PLANET_RADIUS;
                walk();
            }
        }
        //angleDeg = angleDeg % 360;

        float originX = GameConfig.WORLD_CENTER_X;
        float originY = GameConfig.WORLD_CENTER_Y;

        radius = MathUtils.clamp(
                jumpHeight( jumpTime ),         // value
                GameConfig.PLANET_RADIUS,   // min
                jumpHeight( jumpTime )         // max
        );

        float xPos = originX + MathUtils.sinDeg( angleDeg ) * radius;
        float yPos = originY + MathUtils.cosDeg( angleDeg ) * radius;

        setPosition( xPos, yPos );

    }

    public float getAngleDeg() {
        return angleDeg;
    }

    public MonsterState getState() {
        return state;
    }

    public void jump() {
        state = MonsterState.JUMPING;
        jumpTime = 0f;
        log.debug( "jumping" );
    }

    private void walk() {
        state = MonsterState.WALKING;
        log.debug( "walking" );
    }

    private float jumpHeight( float jumpTime ) {
        double period = ( jumpTime / GameConfig.JUMP_TIME ) * Math.PI;
        return ( float ) ( GameConfig.PLANET_RADIUS + Math.sin( period ) * GameConfig.JUMP_HEIGHT );
    }


}
