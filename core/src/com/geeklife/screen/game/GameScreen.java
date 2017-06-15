package com.geeklife.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.geeklife.util.GameBase;
import com.geeklife.util.GdxUtils;

/**
 * Created by cle99 on 15/06/2017.
 */

public class GameScreen extends ScreenAdapter {

    private GameBase game;
    private final AssetManager assetManager;

    private GameController controller;
    private GameRenderer renderer;

    public GameScreen( GameBase game ) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    @Override
    public void show() {
        controller = new GameController();
        renderer = new GameRenderer(controller);
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
