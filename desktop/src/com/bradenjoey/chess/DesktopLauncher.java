package com.bradenjoey.chess;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.bradenjoey.Chess.Menu;
import com.bradenjoey.Chess.Chess;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	// version # --- save for later 
	private static String version = " ";  

	public static void main (String[] arg) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		// Chess chessGame = new Chess(); 

		config.setForegroundFPS(240);
		config.setTitle("Chess" + version);
		config.setWindowedMode(900, 600);
		config.setResizable(true); // this is going to be a pain in the ass

		// run the main menu first then if user clicks new game or host game run the chess application 
		// new Lwjgl3Application(new com.bradenjoey.Chess.Chess(), config);
		Chess chess = new Chess(); 
		chess.setScreen(new Menu(chess)); 

		new Lwjgl3Application(chess, config); 
		
	}
}
