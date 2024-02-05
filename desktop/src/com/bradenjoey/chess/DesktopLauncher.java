package com.bradenjoey.chess;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


// commented out the imports we are not using 
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.Table;
// import com.bradenjoey.chess.Chess;

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

		// comment this out rn it is not working correctly idk why 
		//chessGame.setMenu(new menu()); 

		new Lwjgl3Application(new Chess(), config);
	}
}
