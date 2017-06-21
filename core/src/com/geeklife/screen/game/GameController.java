package com.geeklife.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Logger;
import com.geeklife.congig.GameConfig;
import com.geeklife.entity.Monster;
import com.geeklife.entity.MonsterState;
import com.geeklife.entity.Planet;

/**
 * Created by cle99 on 15/06/2017.
 */

public class GameController {

    // -- constants --
    private static final Logger log = new Logger( GameController.class.getName(), Logger.DEBUG );

    // -- attributes --
    private Planet planet;
    private Monster monster;

    // -- constructors --
    public GameController() {
        planet = new Planet();
        planet.setSize( GameConfig.PLANET_SIZE, GameConfig.PLANET_SIZE );
        planet.setPosition(
                GameConfig.WORLD_CENTER_X - planet.getWidth() / 2f,
                GameConfig.WORLD_CENTER_Y - planet.getHeight() / 2f
        );

        monster = new Monster();
        monster.setPosition(
                GameConfig.WORLD_CENTER_X - monster.getWidth() / 2f,
                GameConfig.WORLD_CENTER_Y + planet.getHeight() / 2f
        );

    }

    public void update( float delta ) {
        log.debug("jump cycles " + delta  );
        handleInput();
        monster.update( delta );

    }

    public Planet getPlanet() {
        return planet;
    }

    public Monster getMonster() {
        return monster;
    }

    // - private methods
    private void handleInput() {
        MonsterState state = monster.getState();
        if ( Gdx.input.isKeyJustPressed( Input.Keys.SPACE ) && state == MonsterState.WALKING){
            monster.jump();
        }
    }
}
