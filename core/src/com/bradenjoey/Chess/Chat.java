package com.bradenjoey.Chess;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Chat {

    private ShapeRenderer chatBoxRenderer;
    
    // do more stuff here!

    public Chat() {
        chatBoxRenderer = new ShapeRenderer();
    }

    public void render(SpriteBatch batch) {
        batch.begin();
            chatBoxRenderer.begin(ShapeType.Filled);
                chatBoxRenderer.rect(600, 19.5f, 280.5f, 560); // oh god
                chatBoxRenderer.setColor(0.549019608f, 0.549019608f, 0.549019608f, 1); // ugly numbers again
            chatBoxRenderer.end();
        batch.end();
    }

}
