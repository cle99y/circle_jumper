package com.geeklife.screen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.geeklife.congig.GameConfig;
import com.geeklife.entity.Coin;
import com.geeklife.entity.Monster;
import com.geeklife.entity.Planet;
import com.geeklife.util.ViewportUtils;
import com.geeklife.util.debug.DebugCameraController;

/**
 * Created by cle99 on 15/06/2017.
 */

public class GameRenderer implements Disposable {

    private static final Logger log = new Logger( GameRenderer.class.getName(), Logger.DEBUG );

    private final GameController controller;

    private Viewport viewport;
    private ShapeRenderer renderer;
    private OrthographicCamera camera;
    private DebugCameraController debugCameraController;

    private Planet planet;
    private Monster monster;

    // -- constructors --

    public GameRenderer( GameController controller ) {
        this.controller = controller;
        init();
    }

    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport( GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera );
        renderer = new ShapeRenderer();
        planet = controller.getPlanet();
        monster = controller.getMonster();

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition( GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y );
    }

    // -- public methods --
    public void render( float delta ) {
        debugCameraController.handleDebugInput( delta );
        debugCameraController.applyTo( camera );

        viewport.apply();

        renderDebug();

        renderer.setProjectionMatrix( camera.combined );
        renderer.begin( ShapeRenderer.ShapeType.Line );

        renderer.setColor( Color.YELLOW );
        Circle planetBounds = planet.getBounds();
        renderer.circle(
                planetBounds.x,
                planetBounds.y,
                planetBounds.radius, 30
        );
        renderer.setColor( Color.GREEN );
        Rectangle monsterBounds = monster.getBounds();
        renderer.rect(
                monsterBounds.x, monsterBounds.y,
                0, 0,
                monsterBounds.width, monsterBounds.height,
                1, 1,
                GameConfig.START_ANGLE - monster.getAngleDeg()
        );

        // render coins
        renderer.setColor( Color.MAGENTA );
        for ( Coin coin : controller.getCoins() ) {
            Rectangle coinBounds = coin.getBounds();
            renderer.rect(
                    coinBounds.x, coinBounds.y,
                    0, 0,
                    coinBounds.width, coinBounds.height,
                    1, 1,
                    GameConfig.START_ANGLE - coin.getAngle()
            );
        }

        renderer.end();


    }

    public void resize( int width, int height ) {
        viewport.update( width, height, true );
        ViewportUtils.debugPixelsPerUnit( viewport );
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    // -- private methods --
    private void renderDebug() {
        ViewportUtils.drawGrid( viewport, renderer, GameConfig.CELL_SIZE );
    }
}
