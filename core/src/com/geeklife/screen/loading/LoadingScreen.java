package com.geeklife.screen.loading;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.geeklife.assets.AssetDescriptors;
import com.geeklife.congig.GameConfig;
import com.geeklife.screen.game.GameScreen;
import com.geeklife.util.GameBase;
import com.geeklife.util.GdxUtils;

/**
 * Created by cle99 on 22/06/2017.
 */

public class LoadingScreen extends ScreenAdapter {

    // -- constants --
    private static final float PROGRESS_BAR_WIDTH = GameConfig.HUD_WIDTH;
    private static final float PROGRESS_BAR_HEIGHT = GameConfig.HUD_HEIGHT;

    // -- attributes --
    private final GameBase game;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;

    private ShapeRenderer renderer;
    private float progress;
    private float waitTime = 0.75f;

    private boolean changeScreen;

    // -- constructors --
    public LoadingScreen( GameBase game ) {
        this.game = game;
        assetManager = game.getAssetManager();
    }
    // --public methods --

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport( GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, camera );
        renderer = new ShapeRenderer();

        assetManager.load( AssetDescriptors.FONT );
    }

    @Override
    public void render( float delta ) {
        update( delta );

        GdxUtils.clearScreen();
        viewport.apply();
        renderer.setProjectionMatrix( camera.combined );
        renderer.begin( ShapeRenderer.ShapeType.Filled );

        draw();

        renderer.end();

        if ( changeScreen ) {
            game.setScreen( new GameScreen( game ) );
        }
    }

    @Override
    public void resize( int width, int height ) {
        viewport.update( width, height, true );
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    // -- private methods --
    private void update( float delta ) {
        progress = assetManager.getProgress();

        if ( assetManager.update() ) {
            waitTime -= delta;
        }

        if ( waitTime <= 0 ) {
            changeScreen = true;
        }
    }

    private void draw() {
        float x = GameConfig.HUD_WIDTH / 2 - PROGRESS_BAR_WIDTH / 2;
        float y = GameConfig.HUD_HEIGHT / 2 - PROGRESS_BAR_HEIGHT / 2;
        renderer.rect(
                x, y,
                progress * PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT
        );
    }
}
