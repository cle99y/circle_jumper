package com.geeklife.util;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;

/**
 * Created by cle99 on 15/06/2017.
 */

public abstract class GameBase extends Game {

    private AssetManager assetManager;
    private SpriteBatch sb;

    @Override
    public void create() {
        Gdx.app.setLogLevel( Application.LOG_DEBUG );

        assetManager = new AssetManager();
        assetManager.getLogger().setLevel( Logger.DEBUG );

        sb = new SpriteBatch();

        postCreate();
    }

    public void postCreate() {
    }

    // -- public methods --
    public AssetManager getAssetManager() {
        return assetManager;
    }

    public SpriteBatch getSb() {
        return sb;
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }

}
