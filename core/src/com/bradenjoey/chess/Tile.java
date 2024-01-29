package com.bradenjoey.chess;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {

    // piece color
    public String color = ""; // either "BLACK" or "WHITE"

    // default value
    Tiles tile = Tiles.EMPTY;

    // bottom left square
    float x = 0;
    float y = 0;

    public Texture pieceTexture;

    public void render(SpriteBatch batch) {

    }

}
