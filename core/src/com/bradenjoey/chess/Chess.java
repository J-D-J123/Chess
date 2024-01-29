package com.bradenjoey.chess;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class Chess extends ApplicationAdapter {
	SpriteBatch batch;

	Texture chessBoard;

	ShapeRenderer whiteTimerBox;
	ShapeRenderer blackTimerBox;

	@Override
	public void create () {
		batch = new SpriteBatch();
		chessBoard = new Texture(Gdx.files.internal("Board.png"));

		whiteTimerBox = new ShapeRenderer();
		blackTimerBox = new ShapeRenderer();
	}

	// runs every frame
	@Override
	public void render () {

		// sets the screen to the gray boarder color of the board.png
		ScreenUtils.clear(0.349019608f, 0.349019608f, 0.349019608f, 1); // ugly number

		batch.begin();
			batch.draw(chessBoard, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - 50);

			batch.enableBlending(); // i don't know why this works but it is needed
				whiteTimerBox.begin(ShapeRenderer.ShapeType.Filled);
					whiteTimerBox.rect(0, Gdx.graphics.getHeight() - 50, 150, 50);
					whiteTimerBox.setColor(0.831372549f, 0.831372549f, 0.831372549f, 1); // more ugly numbers
				whiteTimerBox.end();

				blackTimerBox.begin(ShapeRenderer.ShapeType.Filled);
					blackTimerBox.rect(Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 50, 150, 50);
					blackTimerBox.setColor(0.580392157f, 0.580392157f, 0.580392157f, 1); // even more ugly numbers
				blackTimerBox.end();
			batch.disableBlending();
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
