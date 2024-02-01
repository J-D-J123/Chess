package com.bradenjoey.chess;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.bradenjoey.chess.Chess;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(240);
		config.setTitle("Chess");
		config.setWindowedMode(900, 600);
		config.setResizable(true); // this is going to be a pain in the ass
		new Lwjgl3Application(new Chess(), config);
	}
}
