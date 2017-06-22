package com.geeklife;

import com.geeklife.screen.loading.LoadingScreen;
import com.geeklife.util.GameBase;

public class CircleJumperGame extends GameBase {

	@Override
	public void postCreate() {
		setScreen( new LoadingScreen( this ) );
	}
}
