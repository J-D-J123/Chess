package com.bradenjoey.Chess;

// Chess.java
// Authors: Braden S and Joey J
// Last Modified: Feb. 9, 2024
// Created using Java 21 and libGDX

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.bradenjoey.Networking.Client.Client;

public class Chess extends ApplicationAdapter {

	// menu 
	// the fucking yellow line was pissing me off thats why its now commented out
	//private Menu menu;

	Client chessClient;

	Board chessBoard;

	boolean gameStarted;

	Chat chessChat;

	// sprite batch (just one)
	SpriteBatch batch;

	// camera and viewport
	OrthographicCamera camera;
	ScalingViewport viewport;

	// timer related things and such
	ShapeRenderer timerBox;

	// note from braden - there is a bitmap font data type in libgdx
	public Object font;

	// constructor pretty much
	@Override
	public void create () {
		chessClient = new Client();
		// temp, when you get done the menu, ill change this
		chessClient.connect("localhost", 6678);

		batch = new SpriteBatch();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScalingViewport(Scaling.fit, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

		// timerBox should prolly be in the board class maybe?
		timerBox = new ShapeRenderer();

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	// runs every frame
	@Override
	public void render () {

		if (chessClient.color != null && !gameStarted) {
			chessBoard = new Board(chessClient.color);
			chessChat = new Chat();
			gameStarted = true;
		}

		if (gameStarted) {
			chessBoard.update(viewport);
			//chessChat.update();
		}

		// sends new moves
		if (gameStarted && chessBoard.latestMove != null) {
			chessClient.sendMove(chessBoard.latestMove);
			chessBoard.latestMove = null;
			chessBoard.validateBoard();
		}

		// gets new moves
		if (gameStarted && chessClient.newMove != null) {
			// todo a queue system becuase if the game is fast enough then missed moves could happen maybe
			chessBoard.decodeMove(chessClient.newMove);
			chessClient.newMove = null;
		}

		// sets the screen to the same color of the gray boarder color of the board.png
		ScreenUtils.clear(0.349019608f, 0.349019608f, 0.349019608f, 1); // ugly number
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		if (gameStarted) {		
			// should the timer be part of the board class?
			batch.begin();
				timerBox.begin(ShapeType.Filled);
					timerBox.rect(600, 530, 280.5f, 50); // oh god
					timerBox.setColor(0.749019608f, 0.749019608f, 0.749019608f, 1); // even more ugly numbers
				timerBox.end();
			batch.end();

			chessBoard.render(batch, viewport);
			chessChat.render(batch);
		}

	}
	
	// TO:DO make everything dispose nicely
	// clean up clean up
	@Override
	public void dispose () {
		chessClient.exit();
		timerBox.dispose();
		// viewport doesnt need to be disposed
		// camera doesnt need to be disposed
		batch.dispose();
	}

	/**
	 * the changeScreen() method changes the screen to the menu phase 
	 * @param screenType sets different screens for the user 
	 */
	public void changeScreen(int screenType) {
		// home menu
		if (screenType == 1) {
			// menu.setScreen(new Menu(this)); // pause menu 
		} else if( screenType == 2) {
			// menu.setScreen(new ChessScreen(this));  // home menu 
		}
	}
}
