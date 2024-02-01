package com.bradenjoey.chess;

// Chess.java
// Authors: Braden S
// Last Modified: Jan. 29, 2024
// Created using Java 21 and libGDX

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class Chess extends ApplicationAdapter {

	// sprite batch (just one)
	SpriteBatch batch;

	// camera and viewport
	OrthographicCamera camera;
	ScalingViewport viewport;

	// textures
	Texture chessBoardTexture; // will move to board class

	// chat related things and such
	ShapeRenderer chatBox;

	// timer related things and such
	ShapeRenderer timerBox;

	Board chessBoard;

	// mouse position
	Vector3 mousePosWindow;
	Vector2 mousePosWorld;

	// constructor
	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScalingViewport(Scaling.fit, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);

		chessBoardTexture = new Texture(Gdx.files.internal("Board.png"));

		chatBox = new ShapeRenderer();
		timerBox = new ShapeRenderer();

		mousePosWindow = new Vector3();
		mousePosWorld = new Vector2();

		// temp set to white untill client and server shit is set up
		chessBoard = new Board("WHITE");

	}

	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	// runs every frame
	@Override
	public void render () {

		// gets mouse pos in world space
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			mousePosWindow.set(Gdx.input.getX(), Gdx.input.getY(), 0);

			// changing this to viewport instend of camera took me legit 5 hours to figure out
			viewport.unproject(mousePosWindow);
			mousePosWorld.x = mousePosWindow.x;
			mousePosWorld.y = mousePosWindow.y;

			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					if (chessBoard.tiles[x][y].pieceRectangle.contains(mousePosWorld)) {
						System.out.print(chessBoard.tiles[x][y].letter);
						System.out.println(chessBoard.tiles[x][y].number);
					}
				}
			}
		}

		// sets the screen to the same color of the gray boarder color of the board.png
		ScreenUtils.clear(0.349019608f, 0.349019608f, 0.349019608f, 1); // ugly number

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			// chess board texture
			batch.draw(chessBoardTexture, -viewport.getWorldWidth() / 2, -viewport.getWorldHeight() / 2, viewport.getWorldHeight(), viewport.getWorldHeight());
		batch.end();

		// chat (needes it own batch begin and end because if i dont the board texture will be white)
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
		chessBoard.render(batch);

	}
	
	// clean up clean up
	@Override
	public void dispose () {
		timerBox.dispose();
		chatBox.dispose();
		chessBoardTexture.dispose();
		// viewport doesnt need to be disposed
		// camera doesnt need to be disposed
		batch.dispose();
	}
}
