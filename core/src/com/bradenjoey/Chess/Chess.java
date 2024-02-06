package com.bradenjoey.Chess;

// Chess.java
// Authors: Braden S
// Last Modified: Jan. 29, 2024
// Created using Java 21 and libGDX

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
	private Menu menu; 

	// sprite batch (just one)
	SpriteBatch batch;

	// camera and viewport
	OrthographicCamera camera;
	ScalingViewport viewport;

	// chat related things and such
	ShapeRenderer chatBox;

	// timer related things and such
	ShapeRenderer timerBox;

	Board chessBoard;

	Client chessClient;

	// note from braden - there is a bitmap font data type in libgdx
	public Object font;

	// constructor pretty much
	@Override
	public void create () {
		chessClient = new Client();

		// temp, when you get done the menu, ill change this
		chessClient.connect("localhost", 6678);

		chessBoard = new Board();

		batch = new SpriteBatch();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScalingViewport(Scaling.fit, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

		// move lastMoveRenderer to new class
		chatBox = new ShapeRenderer();
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

		/////////////////////////////////////
		// updating begins here            //
		/////////////////////////////////////

		chessBoard.update(viewport);

		chessBoard.color = chessClient.color;

		/////////////////////////////////////
		// real rendering begins here      //
		/////////////////////////////////////

		// sets the screen to the same color of the gray boarder color of the board.png
		ScreenUtils.clear(0.349019608f, 0.349019608f, 0.349019608f, 1); // ugly number
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		// chat, will be moved to chat class
		batch.begin();
			chatBox.begin(ShapeType.Filled);
				chatBox.rect(600, 19.5f, 280.5f, 560); // oh god
				chatBox.setColor(0.549019608f, 0.549019608f, 0.549019608f, 1); // ugly numbers again
			chatBox.end();

			timerBox.begin(ShapeType.Filled);
				timerBox.rect(600, 530, 280.5f, 50); // oh god
				timerBox.setColor(0.749019608f, 0.749019608f, 0.749019608f, 1); // even more ugly numbers
			timerBox.end();
		batch.end();

		// renders chess board
		chessBoard.render(batch, viewport);

	}
	
	// TO:DO make everything dispose nicely
	// clean up clean up
	@Override
	public void dispose () {
		timerBox.dispose();
		chatBox.dispose();
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
