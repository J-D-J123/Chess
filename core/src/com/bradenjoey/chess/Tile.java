package com.bradenjoey.chess;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tile {

    // piece color
    public String color; // either "BLACK" or "WHITE"

    public ChessPiece piece;

    private ChessPiece lastPiece;

    // pixel positions
    public float x;
    public float y;

    public char letter;
    public int number;

    public Rectangle tileRectangle;

    public Tile(char letter, int number, String color) {
        this.letter = letter;
        this.number = number;
        this.color = color;

        // change this shit to be a loop (maybe??)
        if (color.equals("WHITE")) {
            // set x pixel coords
            switch (letter) {
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
            switch (number) {
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
        }

        if (color.equals("BLACK")) {
            // set x pixel coords
            switch (letter) {
                case 'A':
                    x = 60;
                    break;
                case 'B':
                    x = -10;
                    break;
                case 'C':
                    x = -80;
                    break;
                case 'D':
                    x = -150;
                    break;
                case 'E':
                    x = -220;
                    break;
                case 'F':
                    x = -290;
                    break;
                case 'G':
                    x = -360;
                    break;
                case 'H':
                    x = -430;
                    break;
            }

            // set y pixel coords
            switch (number) {
                /**
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
                **/
                case 1:
                    y = 210;
                    break;
                case 2:
                    y = 140;
                    break;
                case 3:
                    y = 70;
                    break;
                case 4:
                    y = 0;
                    break;
                case 5:
                    y = -70;
                    break;
                case 6:
                    y = -140;
                    break;
                case 7:
                    y = -210;
                    break;
                case 8:
                    y = -280.5f; // dont worry why this one is diffrent, just accept.
                    break;
                }
        }

        tileRectangle = new Rectangle(x, y, 70, 70.5f);
    }

    public void update() {
        // do stuff here when i figure out wtf acully happened in here
        // spelling mistake pls fix
    }

    public void render(SpriteBatch batch) {
        batch.begin();
            if (piece != null) {
                batch.draw(piece.chessPieceTexture, tileRectangle.x , tileRectangle.y, tileRectangle.width, tileRectangle.height);
            }
        batch.end();
    }

}
