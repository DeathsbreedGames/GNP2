package org.deathsbreedgames.gnp2.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.deathsbreedgames.gnp2.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "GNP2";
		config.width = 500;
		config.height = 500;
		config.resizable = false;
		config.foregroundFPS = 30;
		config.backgroundFPS = 20;
		config.addIcon("gfx/GNPIcon.png", Files.FileType.Internal);
		new LwjglApplication(new Game(), config);
	}
}
