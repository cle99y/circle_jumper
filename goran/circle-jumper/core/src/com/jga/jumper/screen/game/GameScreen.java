package com.jga.jumper.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.jga.jumper.assets.AssetDescriptors;
import com.jga.jumper.common.SoundListener;
import com.jga.util.GdxUtils;
import com.jga.util.game.GameBase;

/**
 * Created by goran on 4/10/2016.
 */

public class GameScreen extends ScreenAdapter {

    // == attributes ==
    private final GameBase game;
    private final AssetManager assetManager;
    private final SoundListener listener;

    private GameController controller;
    private GameRenderer renderer;

    private Sound coinSound;
    private Sound jumpSound;
    private Sound loseSound;

    // == constructors ==
    public GameScreen(GameBase game) {
        this.game = game;
        assetManager = game.getAssetManager();

        listener = new SoundListener() {
            @Override
            public void hitCoin() {
                coinSound.play();
            }

            @Override
            public void jump() {
                jumpSound.play();
            }

            @Override
            public void lose() {
                loseSound.play();
            }
        };
    }

    // == public methods ==
    @Override
    public void show() {
        coinSound = assetManager.get(AssetDescriptors.COIN);
        jumpSound = assetManager.get(AssetDescriptors.JUMP);
        loseSound = assetManager.get(AssetDescriptors.LOSE);

        controller = new GameController(listener);
        renderer = new GameRenderer(controller, game.getBatch(), assetManager);
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        controller.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
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
