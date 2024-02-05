package com.bradenjoey.Chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ChessPiece {

    // chess piece textures
    public static final Texture whitePawnTexture = new Texture(Gdx.files.internal("Pieces/White/pw.png"));
    public static final Texture whiteBishopTexture = new Texture(Gdx.files.internal("Pieces/White/bw.png"));
    public static final Texture whiteKnightTexture = new Texture(Gdx.files.internal("Pieces/White/nw.png"));
    public static final Texture whiteRookTexture = new Texture(Gdx.files.internal("Pieces/White/rw.png"));
    public static final Texture whiteQueenTexture = new Texture(Gdx.files.internal("Pieces/White/qw.png"));
    public static final Texture whiteKingTexture = new Texture(Gdx.files.internal("Pieces/White/kw.png"));
    public static final Texture blackPawnTexture = new Texture(Gdx.files.internal("Pieces/Black/pb.png"));
    public static final Texture blackBishopTexture = new Texture(Gdx.files.internal("Pieces/Black/bb.png"));
    public static final Texture blackKnightTexture = new Texture(Gdx.files.internal("Pieces/Black/nb.png"));
    public static final Texture blackRookTexture = new Texture(Gdx.files.internal("Pieces/Black/rb.png"));
    public static final Texture blackQueenTexture = new Texture(Gdx.files.internal("Pieces/Black/qb.png"));
    public static final Texture blackKingTexture = new Texture(Gdx.files.internal("Pieces/Black/kb.png"));

    public char letter;
    public int number;

    public String color;

    public PieceType type;
    public Texture chessPieceTexture;

    public Rectangle chessPieceRectangle;

    public ChessPiece(PieceType type, String color, float x, float y) {
        this.type = type;
        this.color = color;

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