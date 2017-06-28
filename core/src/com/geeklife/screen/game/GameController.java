package com.geeklife.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.geeklife.common.CollisionListener;
import com.geeklife.common.GameManager;
import com.geeklife.congig.GameConfig;
import com.geeklife.entity.Coin;
import com.geeklife.entity.Monster;
import com.geeklife.entity.MonsterState;
import com.geeklife.entity.Obstacle;
import com.geeklife.entity.Planet;
import com.geeklife.util.GameBase;

/**
 * Created by cle99 on 15/06/2017.
 */

public class GameController {

    // -- constants --
    private static final Logger log = new Logger( GameController.class.getName(), Logger.DEBUG );
    private final GameManager GM = GameManager.INSTANCE;

    // -- attributes --
    private GameBase game;
    private CollisionListener listener;
    private Planet planet;
    private Monster monster;

    private final Array<Coin> coins = new Array<Coin>();
    private Pool<Coin> coinPool = Pools.get( Coin.class, 10 );
    private float coinTimer;

    private final Array<Obstacle> obstacles = new Array<Obstacle>();
    private Pool<Obstacle> obstaclePool = Pools.get( Obstacle.class, 10 );
    private float obstacleTimer;

    private final float monsterStartX;
    private final float monsterStartY;

    // -- constructors --
    public GameController( GameBase game, CollisionListener listener ) {
        this.game = game;
        this.listener = listener;
        planet = new Planet();
        planet.setSize( GameConfig.PLANET_SIZE, GameConfig.PLANET_SIZE );
        planet.setPosition(
                GameConfig.WORLD_CENTER_X - planet.getWidth() / 2f,
                GameConfig.WORLD_CENTER_Y - planet.getHeight() / 2f
        );

        monster = new Monster();
        monsterStartX = GameConfig.WORLD_CENTER_X - monster.getWidth() / 2f;
        monsterStartY = GameConfig.WORLD_CENTER_Y + planet.getHeight() / 2f;
        monster.setPosition( monsterStartX, monsterStartY );

    }

    public void update( float delta ) {
        handleInput();
        monster.update( delta );

        checkForCollisions();
        if ( GM.isGameOver() ) {
            restart();
        } else {
            if ( monster.getState() == MonsterState.LANDED ) {
                removeObstacles();
            }
        }

        spawnCoins( delta );
        spawnObstacles( delta );
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

    public Array<Obstacle> getObstacles() {
        return obstacles;
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

    private void spawnObstacles( float delta ) {

        if ( obstacles.size >= GameConfig.MAX_OBSTACLES ) {
            obstacleTimer = 0f;
            return;
        }
        obstacleTimer += delta;
        if ( obstacleTimer >= GameConfig.OBSTACLE_SPAWN_TIME ) {
            Obstacle obstacle = obstaclePool.obtain();
            float angle = MathUtils.random( 360 );
            obstacle.setAngle( angle );
            obstacles.add( obstacle );
            obstacleTimer = 0f;
        }
    }

    private void checkForCollisions() {
        boolean hitCoin;
        Rectangle monsterBounds = monster.getBounds();

        for ( Obstacle obstacle : obstacles ) {
            Rectangle sensorBounds = obstacle.getSensor();

            obstacle.setHit(
                    obstacle.isHit() ||
                            Intersector.overlaps( monsterBounds, obstacle.getBounds() )
            );

            obstacle.setSensorHit(
                    obstacle.isSensorHit() ||
                            Intersector.overlaps( monsterBounds, sensorBounds ) &&
                                    !obstacle.isHit()
            );

            if ( obstacle.isHit() ) {
                listener.hitObstacle();

            }

            if ( obstacle.isSensorHit() && !obstacle.isHit() ) {
                listener.hitObstacleSensor();
            }

        }

        for ( Coin coin : coins ) {
            hitCoin = Intersector.overlaps( monsterBounds, coin.getBounds() );
        }
    }

    private void removeObstacles() {
        if ( obstacles.size > 0 ) {
            for ( Obstacle obstacle : obstacles ) {
                log.debug( "sensor hit = " + obstacle.isSensorHit() );
                if ( obstacle.isSensorHit() ) {
                    obstacles.removeValue( obstacle, true );
                    obstaclePool.free( obstacle );
                }
            }
            monster.walk();
        }
    }

    private void restart() {
        coinPool.freeAll( coins );
        coins.clear();

        obstaclePool.freeAll( obstacles );
        obstacles.clear();

        monster.reset();
        monster.setPosition( monsterStartX, monsterStartY );

        GM.reset();
    }
}
