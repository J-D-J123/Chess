package com.bradenjoey.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ChessPiece {

    // chess piece textures
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

    public char letter;
    public int number;

    public String Color;

    public PieceType type;
    public Texture chessPieceTexture;

    // used for piece promotions
    public void changePiece() {

    }

}
