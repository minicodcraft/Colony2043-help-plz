package com.colony2043.thryl.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.colony2043.thryl.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Colony: 2043";
		config.width = 1920;
		config.height = 1080;
		config.fullscreen = true;
		new LwjglApplication(new GameMain(), config);
	}
}
