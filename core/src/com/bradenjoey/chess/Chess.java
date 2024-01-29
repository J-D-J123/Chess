package com.bradenjoey.chess;

// Chess.java
// Authors: Braden S
// Last Modified: Jan. 29, 2024
// Created using Java 21 and libGDX

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Chess extends ApplicationAdapter {

	// Game Version
	String gameVersion = "0.0.0";

	// sprite batch (just one)
	SpriteBatch batch;

	// textures
	Texture chessBoard;

	// shape renderers
	ShapeRenderer whiteTimerBox;
	ShapeRenderer blackTimerBox;

	// fonts
	BitmapFont mainFont;
	BitmapFont timerFont;

	// player times (probably will be replaced by chess players class later)
	String whiteTime = "10:00";
	String blackTime = "10:00";

	@Override
	public void create () {
		batch = new SpriteBatch();
		chessBoard = new Texture(Gdx.files.internal("Board.png"));

		whiteTimerBox = new ShapeRenderer();
		blackTimerBox = new ShapeRenderer();

		mainFont = new BitmapFont();
		mainFont.getData().scale(1);
		mainFont.setColor(Color.BLACK);

		timerFont = new BitmapFont();
		timerFont.getData().scale(2);
		timerFont.setColor(Color.BLACK);
	}

	// runs every frame
	@Override
	public void render () {

		// sets the screen to the gray boarder color of the board.png
		ScreenUtils.clear(0.349019608f, 0.349019608f, 0.349019608f, 1); // ugly number

		batch.begin();

			// draws the chess board texture
			batch.draw(chessBoard, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - 50);

			// draws the timer boxes
			batch.enableBlending(); // i don't know why this works, but it is needed or else the board is just white
				whiteTimerBox.begin(ShapeRenderer.ShapeType.Filled);
					whiteTimerBox.rect(0, Gdx.graphics.getHeight() - 50, 125, 50);
					whiteTimerBox.setColor(0.831372549f, 0.831372549f, 0.831372549f, 1); // more ugly numbers
				whiteTimerBox.end();

				blackTimerBox.begin(ShapeRenderer.ShapeType.Filled);
					blackTimerBox.rect(Gdx.graphics.getWidth() - 125, Gdx.graphics.getHeight() - 50, 125, 50);
					blackTimerBox.setColor(0.580392157f, 0.580392157f, 0.580392157f, 1); // even more ugly numbers
				blackTimerBox.end();
			batch.disableBlending();

			// bitmap fonts don't render correctly on linux so one day ill have to change this
			// draws the timers for the white and black pieces
			timerFont.draw(batch, whiteTime, 10, Gdx.graphics.getHeight() - 7.5f);
			timerFont.draw(batch, blackTime, Gdx.graphics.getWidth() - 115, Gdx.graphics.getHeight() - 7.5f);

			// i cant tell if this is centered enough because the fonts don't render correctly on linux so you gotta make this look right
			// renders the title at the top of the screen
			mainFont.draw(batch, "Chess " + gameVersion,Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() - 15);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		chessBoard.dispose();
		whiteTimerBox.dispose();
		blackTimerBox.dispose();
	}
}
