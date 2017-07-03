package com.geeklife.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.geeklife.common.CollisionListener;
import com.geeklife.common.GameManager;
import com.geeklife.congig.GameConfig;
import com.geeklife.util.GameBase;
import com.geeklife.util.GdxUtils;

/**
 * Created by cle99 on 15/06/2017.
 */

public class GameScreen extends ScreenAdapter {

    private static final Logger log = new Logger( GameScreen.class.getName(), Logger.DEBUG );
    private static final GameManager GM = GameManager.INSTANCE;

    private GameBase game;
    private final AssetManager assetManager;
    private SpriteBatch sb;

    private CollisionListener listener;
    private GameController controller;
    private GameRenderer renderer;

    public GameScreen( final GameBase game ) {
        this.game = game;
        this.sb = game.getSb();
        assetManager = game.getAssetManager();

        listener = new CollisionListener() {
            @Override
            public void hitObstacle() {
                GM.setGameOver( true );
            }

            @Override
            public void hitCoin() {
                GM.addToScore( GameConfig.COIN_SCORE );
            }

            @Override
            public void hitObstacleSensor() {
                GM.addToScore( GameConfig.OBSTACLE_SCORE );
            }
        };
    }

    @Override
    public void show() {
        controller = new GameController( game, listener );
        renderer = new GameRenderer( sb, assetManager, controller );

    }

    @Override
    public void render( float delta ) {
        GdxUtils.clearScreen();

        controller.update( delta );
        renderer.render( delta );
    }

    @Override
    public void resize( int width, int height ) {
        renderer.resize( width, height );
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
