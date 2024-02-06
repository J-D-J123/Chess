package com.bradenjoey.Chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class Board {

    private Texture chessBoardTexture = new Texture(Gdx.files.internal("Board.png"));

    public String color;
    
    public Tile[][] tiles;

    public String latestMove;

    private Vector3 mousePosWindow;
    private Vector2 mousePosWorld;

    private int selectedChessPieceTileXIndex;
    private int selectedChessPieceTileYIndex;

    private boolean hasPiece = false;

    private Sound moveSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/move.mp3"));

    public Board(String color) {

        this.color = color;

        // chess board is 8 by 8
        tiles = new Tile[8][8];

        mousePosWindow = new Vector3();
        mousePosWorld = new Vector2();

        // creates all the tiles
        if (color.equals("WHITE")) {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    switch (x) {
                        case 0:
                            tiles[x][y] = new Tile('A', y + 1, this.color);
                            break;
                        case 1:
                            tiles[x][y] = new Tile('B', y + 1, this.color);
                            break;
                        case 2:
                            tiles[x][y] = new Tile('C', y + 1, this.color);
                            break;
                        case 3:
                            tiles[x][y] = new Tile('D', y + 1, this.color);
                            break;
                        case 4:
                            tiles[x][y] = new Tile('E', y + 1, this.color);
                            break;
                        case 5:
                            tiles[x][y] = new Tile('F', y + 1, this.color);
                            break;
                        case 6:
                            tiles[x][y] = new Tile('G', y + 1, this.color);
                            break;
                        case 7:
                            tiles[x][y] = new Tile('H', y + 1, this.color);
                            break;
                    }
                }
            }
        }

        if (color == "BLACK") {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    switch (x) {
                        case 0:
                            tiles[x][y] = new Tile('A', y + 1, this.color);
                            break;
                        case 1:
                            tiles[x][y] = new Tile('B', y + 1, this.color);
                            break;
                        case 2:
                            tiles[x][y] = new Tile('C', y + 1, this.color);
                            break;
                        case 3:
                            tiles[x][y] = new Tile('D', y + 1, this.color);
                            break;
                        case 4:
                            tiles[x][y] = new Tile('E', y + 1, this.color);
                            break;
                        case 5:
                            tiles[x][y] = new Tile('F', y + 1, this.color);
                            break;
                        case 6:
                            tiles[x][y] = new Tile('G', y + 1, this.color);
                            break;
                        case 7:
                            tiles[x][y] = new Tile('H', y + 1, this.color);
                            break;
                    }
                }
            }
        }

        // creates the piece on the board in the correct pos
        // TOP ROW 
        for(int x = 7; x>=0; x--) {
            tiles[x][1].piece = new ChessPiece(PieceType.PAWN, "WHITE", tiles[x][1].tileRectangle.x, tiles[x][1].tileRectangle.y);
        }

        // BOTTOM ROW
        tiles[0][0].piece = new ChessPiece(PieceType.ROOK, "WHITE", tiles[0][0].tileRectangle.x, tiles[0][0].tileRectangle.y);
        tiles[1][0].piece = new ChessPiece(PieceType.KNIGHT, "WHITE", tiles[1][0].tileRectangle.x, tiles[1][0].tileRectangle.y);
        tiles[2][0].piece = new ChessPiece(PieceType.BISHOP, "WHITE", tiles[2][0].tileRectangle.x, tiles[2][0].tileRectangle.y);
        tiles[3][0].piece = new ChessPiece(PieceType.QUEEN, "WHITE", tiles[3][0].tileRectangle.x, tiles[3][0].tileRectangle.y);
        tiles[4][0].piece = new ChessPiece(PieceType.KING, "WHITE", tiles[4][0].tileRectangle.x, tiles[4][0].tileRectangle.y);
        tiles[5][0].piece = new ChessPiece(PieceType.BISHOP, "WHITE", tiles[5][0].tileRectangle.x, tiles[5][0].tileRectangle.y);
        tiles[6][0].piece = new ChessPiece(PieceType.KNIGHT, "WHITE", tiles[6][0].tileRectangle.x, tiles[6][0].tileRectangle.y);
        tiles[7][0].piece = new ChessPiece(PieceType.ROOK, "WHITE", tiles[7][0].tileRectangle.x, tiles[7][0].tileRectangle.y);
    
        // black pieces

        // TOP ROW
        for(int x = 7; x>=0; x--) {
            tiles[x][6].piece = new ChessPiece(PieceType.PAWN, "BLACK", tiles[x][6].tileRectangle.x, tiles[x][6].tileRectangle.y);
        }

        // BOTTOM ROW
        tiles[7][7].piece = new ChessPiece(PieceType.ROOK, "BLACK", tiles[7][7].tileRectangle.x, tiles[7][7].tileRectangle.y);
        tiles[6][7].piece = new ChessPiece(PieceType.KNIGHT, "BLACK", tiles[6][7].tileRectangle.x, tiles[6][7].tileRectangle.y);
        tiles[5][7].piece = new ChessPiece(PieceType.BISHOP, "BLACK", tiles[5][7].tileRectangle.x, tiles[5][7].tileRectangle.y);
        tiles[4][7].piece = new ChessPiece(PieceType.QUEEN, "BLACK", tiles[4][7].tileRectangle.x, tiles[4][7].tileRectangle.y);
        tiles[3][7].piece = new ChessPiece(PieceType.KING, "BLACK", tiles[3][7].tileRectangle.x, tiles[3][7].tileRectangle.y);
        tiles[2][7].piece = new ChessPiece(PieceType.BISHOP, "BLACK", tiles[2][7].tileRectangle.x, tiles[2][7].tileRectangle.y);
        tiles[1][7].piece = new ChessPiece(PieceType.KNIGHT, "BLACK", tiles[1][7].tileRectangle.x, tiles[1][7].tileRectangle.y);
        tiles[0][7].piece = new ChessPiece(PieceType.ROOK, "BLACK", tiles[0][7].tileRectangle.x, tiles[0][7].tileRectangle.y);

    }

    public void movePiece(ScalingViewport viewport) {
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            // gets mouse pos in world space
			mousePosWindow.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            // changes the mouse world space location to the window space location
			viewport.unproject(mousePosWindow);
			mousePosWorld.x = mousePosWindow.x;
			mousePosWorld.y = mousePosWindow.y;

            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (tiles[x][y].tileRectangle.contains(mousePosWorld) && tiles[x][y].piece != null && !hasPiece && tiles[x][y].piece.color == color) {
                        selectedChessPieceTileXIndex = x;
                        selectedChessPieceTileYIndex = y;
                        hasPiece = true;
                    }
                }
            }

            if (hasPiece) {
                tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].piece.chessPieceRectangle.x = mousePosWorld.x - tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].piece.chessPieceRectangle.width / 2;
                tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].piece.chessPieceRectangle.y = mousePosWorld.y - tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].piece.chessPieceRectangle.height / 2;
            }

		} else {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].piece != null) {
                        tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].piece.chessPieceRectangle.x = tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].x;
                        tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].piece.chessPieceRectangle.y = tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].y;
                    }
                    
                    if (tiles[x][y].tileRectangle.contains(mousePosWorld) && tiles[x][y].piece == null && hasPiece) {

                        tiles[x][y].piece = tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].piece;

                        latestMove = tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].piece.type + " " + String.valueOf(tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].letter) + tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].number + " -> " + tiles[x][y].piece.type + " " + String.valueOf(tiles[x][y].letter) + tiles[x][y].number;
                        System.out.println(latestMove);

                        tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].piece = null;

                        tiles[x][y].piece.chessPieceRectangle.x = tiles[x][y].x;
                        tiles[x][y].piece.chessPieceRectangle.y = tiles[x][y].y;

                        tiles[x][y].piece.letter = tiles[x][y].letter;
                        tiles[x][y].piece.number = tiles[x][y].number;

                        moveSound.play();
                    }
                }
            }
            hasPiece = false;
        }
    }

    public void update(ScalingViewport viewport) {

        // gets input
        movePiece(viewport);

        // updates the tiles
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                tiles[x][y].update();
            }
        }        
    }

    public void render(SpriteBatch batch, ScalingViewport viewport) {

        batch.begin();
            batch.draw(chessBoardTexture, -viewport.getWorldWidth() / 2, -viewport.getWorldHeight() / 2, viewport.getWorldHeight(), viewport.getWorldHeight());
        batch.end();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                tiles[x][y].render(batch);
            }
        }
    }

}
