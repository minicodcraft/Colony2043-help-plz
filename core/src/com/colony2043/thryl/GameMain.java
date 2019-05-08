package com.colony2043.thryl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.colony2043.thryl.screens.playScreen;

public class GameMain extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new playScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
