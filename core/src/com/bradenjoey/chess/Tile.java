package com.bradenjoey.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tile {

    // this is probably a waste of memory
    public static final Texture whitePawnTexture = new Texture(Gdx.files.internal("Pieces/White/pw.png"));
    public static final Texture whiteBishopTexture = new Texture(Gdx.files.internal("Pieces/White/bw.png"));
    public static final Texture whiteKnightTexture = new Texture(Gdx.files.internal("Pieces/White/kw.png"));
    public static final Texture whiteRookTexture = new Texture(Gdx.files.internal("Pieces/White/rw.png"));
    public static final Texture whiteQueenTexture = new Texture(Gdx.files.internal("Pieces/White/qw.png"));
    public static final Texture whiteKingTexture = new Texture(Gdx.files.internal("Pieces/White/kw.png"));
    public static final Texture blackPawnTexture = new Texture(Gdx.files.internal("Pieces/Black/pb.png"));
    public static final Texture blackBishopTexture = new Texture(Gdx.files.internal("Pieces/Black/bb.png"));
    public static final Texture blackKnightTexture = new Texture(Gdx.files.internal("Pieces/Black/kb.png"));
    public static final Texture blackRookTexture = new Texture(Gdx.files.internal("Pieces/Black/rb.png"));
    public static final Texture blackQueenTexture = new Texture(Gdx.files.internal("Pieces/Black/qb.png"));
    public static final Texture blackKingTexture = new Texture(Gdx.files.internal("Pieces/Black/kb.png"));

    // piece color
    public String color = ""; // either "BLACK" or "WHITE"

    // default value is Tiles.EMPTY
    public Tiles tile = Tiles.EMPTY;

    private Tiles lastTile;

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

        lastTile = tile;

        if (color.equals("WHITE")) {
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
        }

        if (color.equals("BLACK")) {
            // set x pixel coords
            switch (this.letter) {
                case 'A':
                    //x = -430;
                    x = 60;
                    break;
                case 'B':
                    //x = -360;
                    x = -10;
                    break;
                case 'C':
                    //x = -290;
                    x = -80;
                    break;
                case 'D':
                    //x = -220;
                    x = -150;
                    break;
                case 'E':
                    //x = -150;
                    x = -220;
                    break;
                case 'F':
                    //x = -80;
                    x = -290;
                    break;
                case 'G':
                    //x = -10;
                    x = -360;
                    break;
                case 'H':
                    //x = 60;
                    x = -430;
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
        }

        pieceRectangle = new Rectangle(x, y, 70, 70.5f); // its not perfect, but its works :(
    }

    public void update() {
        if (lastTile != tile) {
            if (color == "WHITE") {
                switch (tile) {
                    case EMPTY:
                        pieceTexture = null;
                        break;
                    case PAWN:
                        pieceTexture = whitePawnTexture;
                        break;
                    case BISHOP:
                        pieceTexture = whiteBishopTexture;
                        break;
                    case KNIGHT:
                        pieceTexture = whiteKnightTexture;
                        break;
                    case ROOK:
                        pieceTexture = whiteRookTexture;
                        break;
                    case QUEEN:
                        pieceTexture = whiteQueenTexture;
                        break;
                    case KING:
                        pieceTexture = whiteKingTexture;
                        break;
                }
            } else {
                switch (tile) {
                    case EMPTY:
                        pieceTexture = null;
                        break;
                    case PAWN:
                        pieceTexture = blackPawnTexture;
                        break;
                    case BISHOP:
                        pieceTexture = blackBishopTexture;
                        break;
                    case KNIGHT:
                        pieceTexture = blackKnightTexture;
                        break;
                    case ROOK:
                        pieceTexture = blackRookTexture;
                        break;
                    case QUEEN:
                        pieceTexture = blackQueenTexture;
                        break;
                    case KING:
                        pieceTexture = blackKingTexture;
                        break;
                }
            }
            lastTile = tile;
        }
    }

    public void render(SpriteBatch batch) {
        batch.begin();
            if (tile != Tiles.EMPTY) {
                batch.draw(pieceTexture, pieceRectangle.x , pieceRectangle.y, pieceRectangle.width, pieceRectangle.height);
            }
        batch.end();
    }

}
