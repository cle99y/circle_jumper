package com.geeklife.entity;

import com.geeklife.congig.GameConfig;
import com.geeklife.util.entity.EntityTemplate;

/**
 * Created by cle99 on 15/06/2017.
 */

public class Planet extends EntityTemplate{

    // -- constructors --

    public Planet() {
        setSize( GameConfig.PLANET_SIZE, GameConfig.PLANET_SIZE );
    }

}
