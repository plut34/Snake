package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.GameModel;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "Snake";
		config.resizable = false;
		config.foregroundFPS = 60;
		config.addIcon("menu/icon.png", Files.FileType.Internal);
		config.width = 1366;
		config.height = 768; 
		config.x = -1;
		config.y = -1;
		config.fullscreen = false;
		new LwjglApplication(new GameModel(),config);
	}
}
