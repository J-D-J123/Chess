package com.bradenjoey.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tile {

    public static final Texture whitePawnTexture = new Texture(Gdx.files.internal("Pieces/White/pw.png"));

    // piece color
    public String color = ""; // either "BLACK" or "WHITE"

    // default value is Tiles.EMPTY
    public Tiles tile = Tiles.EMPTY;

    // pixel positions
    public float x;
    public float y;

    public char letter;
    public int number;

    public Rectangle pieceRectangle;

    // temp set to white pawn for testing stuff
    public Texture pieceTexture;

    public Tile(char letter, int number, String color) {
        this.letter = letter;
        this.number = number;
        this.color = color;

        // set x pixel coords
        switch (this.letter) {
            case 'A':
                x = -430;
                break;
            case 'B':
                x = -360;
                break;
            case 'C':
                x = -290;
                break;
            case 'D':
                x = -220;
                break;
            case 'E':
                x = -150;
                break;
            case 'F':
                x = -80;
                break;
            case 'G':
                x = -10;
                break;
            case 'H':
                x = 60;
                break;
        }

        // set y pixel coords
        switch (this.number) {
            case 1:
                y = -280.5f; // dont worry why this one is diffrent, just accept.
                break;
            case 2:
                y = -210;
                break;
            case 3:
                y = -140;
                break;
            case 4:
                y = -70;
                break;
            case 5:
                y = 0;
                break;
            case 6:
                y = 70;
                break;
            case 7:
                y = 140;
                break;
            case 8:
                y = 210;
                break;
        }

        pieceRectangle = new Rectangle(x, y, 70, 70.5f); // its not perfect, but its works :(
    }

    public void update() {
        
    }

    public void render(SpriteBatch batch) {
        batch.begin();
            if (tile != Tiles.EMPTY) {
                batch.draw(pieceTexture, pieceRectangle.x , pieceRectangle.y, pieceRectangle.width, pieceRectangle.height);
            }
        batch.end();
    }

}
