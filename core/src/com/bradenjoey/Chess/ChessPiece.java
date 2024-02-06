package com.bradenjoey.Chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ChessPiece {

    // chess piece textures
    public Texture whitePawnTexture = new Texture(Gdx.files.internal("Pieces/White/pw.png"));
    public Texture whiteBishopTexture = new Texture(Gdx.files.internal("Pieces/White/bw.png"));
    public Texture whiteKnightTexture = new Texture(Gdx.files.internal("Pieces/White/nw.png"));
    public Texture whiteRookTexture = new Texture(Gdx.files.internal("Pieces/White/rw.png"));
    public Texture whiteQueenTexture = new Texture(Gdx.files.internal("Pieces/White/qw.png"));
    public Texture whiteKingTexture = new Texture(Gdx.files.internal("Pieces/White/kw.png"));
    public Texture blackPawnTexture = new Texture(Gdx.files.internal("Pieces/Black/pb.png"));
    public Texture blackBishopTexture = new Texture(Gdx.files.internal("Pieces/Black/bb.png"));
    public Texture blackKnightTexture = new Texture(Gdx.files.internal("Pieces/Black/nb.png"));
    public Texture blackRookTexture = new Texture(Gdx.files.internal("Pieces/Black/rb.png"));
    public Texture blackQueenTexture = new Texture(Gdx.files.internal("Pieces/Black/qb.png"));
    public Texture blackKingTexture = new Texture(Gdx.files.internal("Pieces/Black/kb.png"));

    public char letter;
    public int number;

    public String color;

    public PieceType type;
    public Texture chessPieceTexture;

    public Rectangle chessPieceRectangle;

    public ChessPiece(PieceType type, String color, float x, float y) {
        this.type = type;
        this.color = color;

        // this makes the textures look nice and smooth, before it looked like shit so
        whitePawnTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        whiteBishopTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        whiteKnightTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        whiteRookTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        whiteQueenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        whiteKingTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        blackPawnTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        blackBishopTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        blackKnightTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        blackRookTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        blackQueenTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        blackKingTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);


        chessPieceRectangle = new Rectangle(x, y, 70, 70.5f);

        changePiece();
    }

    // used for piece promotions and when creating pieces
    // joey pls come up with a better function name
    public void changePiece() {
        if (color.equals("WHITE")) {
            switch (type) {
                case PAWN:
                    chessPieceTexture = whitePawnTexture;
                    break;
                case BISHOP:
                    chessPieceTexture = whiteBishopTexture;
                    break;
                case KNIGHT:
                    chessPieceTexture = whiteKnightTexture;
                    break;
                case ROOK:
                    chessPieceTexture = whiteRookTexture;
                    break;
                case QUEEN:
                    chessPieceTexture = whiteQueenTexture;
                    break;
                case KING:
                    chessPieceTexture = whiteKingTexture;
                    break;
            }
        }
        if (color.equals("BLACK")) {
            switch (type) {
                case PAWN:
                    chessPieceTexture = blackPawnTexture;
                    break;
                case BISHOP:
                    chessPieceTexture = blackBishopTexture;
                    break;
                case KNIGHT:
                    chessPieceTexture = blackKnightTexture;
                    break;
                case ROOK:
                    chessPieceTexture = blackRookTexture;
                    break;
                case QUEEN:
                    chessPieceTexture = blackQueenTexture;
                    break;
                case KING:
                    chessPieceTexture = blackKingTexture;
                    break;
            }
        }
    }

    public void render(SpriteBatch batch) {
        batch.begin();
            batch.draw(chessPieceTexture, chessPieceRectangle.x, chessPieceRectangle.y, 70, 70.5f);
        batch.end();
    }

}
