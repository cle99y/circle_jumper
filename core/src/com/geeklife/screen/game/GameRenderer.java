package com.geeklife.screen.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.geeklife.congig.GameConfig;
import com.geeklife.entity.Planet;
import com.geeklife.util.ViewportUtils;
import com.geeklife.util.debug.DebugCameraController;

/**
 * Created by cle99 on 15/06/2017.
 */

public class GameRenderer implements Disposable{

    private final GameController controller;

    private Viewport viewport;
    private ShapeRenderer renderer;
    private OrthographicCamera camera;
    private DebugCameraController debugCameraController;

    private Planet planet;

    // -- constructors --

    public GameRenderer( GameController controller ) {
        this.controller = controller;
        init();
    }

    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport( GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera );
        renderer = new ShapeRenderer();
        planet = new Planet();


        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition( GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y );
    }

    // -- public methods --
    public void render( float delta ) {
        debugCameraController.handleDebugInput( delta );
        debugCameraController.applyTo( camera );

        viewport.apply();
        renderer.setProjectionMatrix( camera.combined );
        renderer.begin( ShapeRenderer.ShapeType.Line);

        renderer.circle(
                GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y,
                GameConfig.PLANET_HALF_SIZE, 30
        );

        renderer.end();

        renderDebug();
    }

    public void resize(int width, int height) {
        viewport.update( width, height, true );
        ViewportUtils.debugPixelsPerUnit( viewport );
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    // -- private methods --
    private void renderDebug(){
        ViewportUtils.drawGrid( viewport, renderer, GameConfig.CELL_SIZE );
    }
}
