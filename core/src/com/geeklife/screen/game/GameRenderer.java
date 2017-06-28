package com.geeklife.screen.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.geeklife.assets.AssetDescriptors;
import com.geeklife.common.GameManager;
import com.geeklife.congig.GameConfig;
import com.geeklife.entity.Coin;
import com.geeklife.entity.Monster;
import com.geeklife.entity.Obstacle;
import com.geeklife.entity.Planet;
import com.geeklife.util.ViewportUtils;
import com.geeklife.util.debug.DebugCameraController;

/**
 * Created by cle99 on 15/06/2017.
 */

public class GameRenderer implements Disposable {

    private static final Logger log = new Logger( GameRenderer.class.getName(), Logger.DEBUG );
    private final GameManager GM = GameManager.INSTANCE;
    private static final int PADDING = 20;

    private final GameController controller;
    private AssetManager assetManager;

    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer renderer;
    private OrthographicCamera camera;
    private DebugCameraController debugCameraController;

    SpriteBatch sb;
    private GlyphLayout layout;
    private BitmapFont font;

    private Planet planet;
    private Monster monster;
    private Obstacle obstacle;

    // -- constructors --

    public GameRenderer( SpriteBatch sb, AssetManager assetManager, GameController controller ) {
        this.controller = controller;
        this.assetManager = assetManager;
        this.sb = sb;
        init();
    }

    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport( GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera );
        hudViewport = new FitViewport( GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT );
        font = assetManager.get( AssetDescriptors.FONT );
        layout = new GlyphLayout();

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

        // render obstacles
        for ( Obstacle obstacle : controller.getObstacles() ) {
            renderer.setColor( Color.ORANGE );
            Rectangle obstacleBounds = obstacle.getBounds();
            renderer.rect(
                    obstacleBounds.x, obstacleBounds.y,
                    0, 0,
                    obstacleBounds.width, obstacleBounds.height,
                    1, 1,
                    GameConfig.START_ANGLE - obstacle.getAngle()
            );

            renderer.setColor( Color.TAN );
            Rectangle sensor = obstacle.getSensor();
            renderer.rect(
                    sensor.x, sensor.y,
                    0,0,
                    sensor.width, sensor.height,
                    1,1,
                    GameConfig.START_ANGLE - obstacle.getAngle()
            );
        }

        renderer.end();

        renderHUD();

    }

    public void resize( int width, int height ) {
        viewport.update( width, height, true );
        hudViewport.update( width, height, true );
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

    private void renderHUD() {
        sb.setProjectionMatrix( hudViewport.getCamera().combined );
        sb.begin();

        drawHUD();

        sb.end();
    }

    private void drawHUD() {
        String displayText = "HIGH SCORE:  " + GM.getDisplayHighScore();
        layout.setText( font, displayText );
        font.draw(
                sb, layout,
                PADDING, GameConfig.HUD_HEIGHT - PADDING
        );

        displayText = "SCORE:  " + GM.getDisplayScore();
        layout.setText( font, displayText );
        font.draw(
                sb, layout,
                GameConfig.HUD_WIDTH - PADDING - layout.width, GameConfig.HUD_HEIGHT - PADDING
        );
    }
}
