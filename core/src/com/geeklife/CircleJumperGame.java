package com.geeklife;

import com.geeklife.screen.game.GameScreen;
import com.geeklife.util.GameBase;

public class CircleJumperGame extends GameBase {

	@Override
	public void postCreate() {
		setScreen( new GameScreen( this ) );
	}
}
