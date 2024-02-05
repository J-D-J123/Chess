package com.bradenjoey.Chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * date:    2/4/2024
 * desc:    the menu class is to make all the menu 
 *          items and put them onto the window
 */

public class menu {
    private Stage stage; 
    private BitmapFont font; 

    public void create() {
        stage   = new Stage(); 
        font    = new BitmapFont(); 

        // call the menu to set up 
        makeMenu(); 

        // set up the stage input 
        Gdx.input.setInputProcessor(stage); 
    }

    public void render() {
        // clears the screen and draws the stage
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1); 
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw new stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw(); 
    }

public void makeMenu() {
    Table table = new Table();
    table.setFillParent(true);
    stage.addActor(table);

    // make the menu buttons
    TextButton newGameMenuButton    = new TextButton("New Game", createButtonStyle());
    TextButton hostMenuButton       = new TextButton("Host Game", createButtonStyle());
    TextButton exitMenuButton       = new TextButton("Exit", createButtonStyle());

    // added listeners for the menu buttons
    newGameMenuButton.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            System.out.println("New Game button clicked");
            Gdx.app.log("Chess", "New Game Clicked");
        }
    });

    hostMenuButton.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            System.out.println("Host button clicked");
            Gdx.app.log("Chess", "Host button Clicked");
        }
    });

    exitMenuButton.addListener(new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            System.out.println("Exit button pressed");
            Gdx.app.exit();
        }
    });

    // add the buttons to the menu
    table.add(newGameMenuButton).padBottom(20).row();
    table.add(hostMenuButton).padBottom(20).row();
    table.add(exitMenuButton).padBottom(20).row();
}


    private TextButton.TextButtonStyle createButtonStyle() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        
        // Create and set the font
        style.font = font;
        
        // Load and set button textures
        style.up     = new TextureRegionDrawable(new TextureRegion(new Texture("chess/assets/button/button_up.png")));
        style.down   = new TextureRegionDrawable(new TextureRegion(new Texture("chess/assets/button/button_down.png")));
        
        return style;
    }

    public void dispose() {
        stage.dispose(); 
        font.dispose(); 
    }

}
