package me.ludumdare.animalgame;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import me.ludumdare.animalgame.AnimalGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("AnimalGame");
		config.setWindowedMode(1480, 800);
		config.setResizable(false);
		new Lwjgl3Application(new AnimalGame(), config);
	}
}
