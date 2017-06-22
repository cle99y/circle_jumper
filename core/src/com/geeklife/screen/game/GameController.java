package com.geeklife.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.geeklife.common.GameManager;
import com.geeklife.congig.GameConfig;
import com.geeklife.entity.Coin;
import com.geeklife.entity.Monster;
import com.geeklife.entity.MonsterState;
import com.geeklife.entity.Planet;

/**
 * Created by cle99 on 15/06/2017.
 */

public class GameController {

    // -- constants --
    private static final Logger log = new Logger( GameController.class.getName(), Logger.DEBUG );
    private final GameManager GM = GameManager.INSTANCE;

    // -- attributes --
    private Planet planet;
    private Monster monster;

    private final Array<Coin> coins = new Array<Coin>();
    private Pool<Coin> coinPool = Pools.get( Coin.class, 10 );
    private float coinTimer;

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
        handleInput();
        monster.update( delta );

        spawnCoins( delta );

    }

    public Planet getPlanet() {
        return planet;
    }

    public Monster getMonster() {
        return monster;
    }

    public Array<Coin> getCoins() {
        return coins;
    }

    // - private methods
    private void handleInput() {
        MonsterState state = monster.getState();
        if ( Gdx.input.isKeyJustPressed( Input.Keys.SPACE ) && state.isWalking() ) {
            monster.jump();
        }
    }

    private void spawnCoins( float delta ) {

        if ( coins.size >= GameConfig.MAX_COINS ) {
            coinTimer = 0f;
            return;
        }
        coinTimer += delta;
        if ( coinTimer >= GameConfig.COIN_SPAWN_TIME ) {
            Coin coin = coinPool.obtain();
            float angle = MathUtils.random( 360 );
            coin.setAngle( angle );
            coins.add( coin );
            coinTimer = 0f;
        }
    }


}
