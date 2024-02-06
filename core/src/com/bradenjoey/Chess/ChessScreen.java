package com.bradenjoey.Chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * @author 	Joseph J 
 * Date		2/5/2024
 * Desc		The ChessScreen.java file is to provide the user with 
 *              a chess home menu that the user can click on to 
 *              choose to do mutliple things such as 
 *              start a new game -> calls new game 
 *              join a game      -> opens up join by ip 
 *              credits          -> opens up the credits // in progress 
 *              exit             -> exits and closes the ligGDX application 
 */

public class ChessScreen implements Screen {
    private Chess parent;

    public ChessScreen(Chess parent) {
        this.parent = parent;
    }

    public void show() {
        // Initialization logic if needed
    }

    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render chess game logic here

        // Example: Draw a placeholder text
        parent.batch.begin();

        // TODO: FIX LINE 36 
        // parent.font.draw(parent.batch, "Chess Game Screen", 100, 300);
        parent.batch.end();
    }

    // Other necessary methods for Screen interface

    public void dispose() {
        // Cleanup resources
    }

    @Override
    public void resize(int width, int height) {
        throw new UnsupportedOperationException("Unimplemented method 'resize'");
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void hide() {
        throw new UnsupportedOperationException("Unimplemented method 'hide'");
    }
}
