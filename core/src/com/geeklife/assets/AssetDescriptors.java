package com.geeklife.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by cle99 on 22/06/2017.
 */

public class AssetDescriptors {

    // -- constants --
    public static final AssetDescriptor<BitmapFont> FONT =
            new AssetDescriptor<BitmapFont>( AssetPaths.FONT, BitmapFont.class );

    // -- constructors --
    private AssetDescriptors() {}
}
