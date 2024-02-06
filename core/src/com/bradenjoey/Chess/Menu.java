// Menu.java
package com.bradenjoey.Chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * @author 	Joseph J 
 * Date		2/5/2024
 * Desc		The Menu.java file is to let the user have a way to pause the game 
 * 				this is instead of a libgdx menu item (i can't find it) 
 */

public class Menu implements Screen {
    private Chess parent;
    private Stage stage;

    public Menu(Chess parent) {
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        TextButton newGame = new TextButton("New Game", skin);
        TextButton preferences = new TextButton("Preferences", skin);
        TextButton exit = new TextButton("Exit", skin);

        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switchToChessScreen();
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Implement preferences screen logic here
            }
        });
    }

    private void switchToChessScreen() {
		// TODO: FIX setScreen() method call
        // parent.setScreen(new ChessScreen(parent));
    }

    // Other necessary methods for Screen interface

    @Override
    public void render(float delta) {
        // Render logic
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    // Other necessary methods for Screen interface

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    // Other necessary methods for Screen interface

    @Override
    public void dispose() {
        stage.dispose();
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
