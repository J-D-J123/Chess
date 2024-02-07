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

    private String color;
    
    public Tile[][] tiles;

    public String latestMove;

    private Vector3 mousePosWindow;
    private Vector2 mousePosWorld;

    private int selectedChessPieceTileXIndex;
    private int selectedChessPieceTileYIndex;

    private boolean hasPiece;

    // need to get new sound becuase this one sounds very bad when using headphones
    private Sound moveSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/move.mp3"));

    public Board(String color) {
        this.color = color;

        tiles = new Tile[8][8];

        createTiles();

        createPieces();

        mousePosWindow = new Vector3();
        mousePosWorld = new Vector2();
    }

    public void createTiles() {
        // creates all the tiles
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

    public void createPieces() {
        // top row of pawns for white
        for(int x = 7; x>=0; x--) {
            tiles[x][1].piece = new ChessPiece(PieceType.P, "WHITE", tiles[x][1].tileRectangle.x, tiles[x][1].tileRectangle.y);
        }

        // bottom row of pieces for white
        tiles[0][0].piece = new ChessPiece(PieceType.R, "WHITE", tiles[0][0].tileRectangle.x, tiles[0][0].tileRectangle.y);
        tiles[1][0].piece = new ChessPiece(PieceType.N, "WHITE", tiles[1][0].tileRectangle.x, tiles[1][0].tileRectangle.y);
        tiles[2][0].piece = new ChessPiece(PieceType.B, "WHITE", tiles[2][0].tileRectangle.x, tiles[2][0].tileRectangle.y);
        tiles[3][0].piece = new ChessPiece(PieceType.Q, "WHITE", tiles[3][0].tileRectangle.x, tiles[3][0].tileRectangle.y);
        tiles[4][0].piece = new ChessPiece(PieceType.K, "WHITE", tiles[4][0].tileRectangle.x, tiles[4][0].tileRectangle.y);
        tiles[5][0].piece = new ChessPiece(PieceType.B, "WHITE", tiles[5][0].tileRectangle.x, tiles[5][0].tileRectangle.y);
        tiles[6][0].piece = new ChessPiece(PieceType.N, "WHITE", tiles[6][0].tileRectangle.x, tiles[6][0].tileRectangle.y);
        tiles[7][0].piece = new ChessPiece(PieceType.R, "WHITE", tiles[7][0].tileRectangle.x, tiles[7][0].tileRectangle.y);

        // top row of pawns for black
        for(int x = 7; x>=0; x--) {
            tiles[x][6].piece = new ChessPiece(PieceType.P, "BLACK", tiles[x][6].tileRectangle.x, tiles[x][6].tileRectangle.y);
        }

        // bottom row of pieces for black
        tiles[7][7].piece = new ChessPiece(PieceType.R, "BLACK", tiles[7][7].tileRectangle.x, tiles[7][7].tileRectangle.y);
        tiles[6][7].piece = new ChessPiece(PieceType.N, "BLACK", tiles[6][7].tileRectangle.x, tiles[6][7].tileRectangle.y);
        tiles[5][7].piece = new ChessPiece(PieceType.B, "BLACK", tiles[5][7].tileRectangle.x, tiles[5][7].tileRectangle.y);
        tiles[4][7].piece = new ChessPiece(PieceType.Q, "BLACK", tiles[4][7].tileRectangle.x, tiles[4][7].tileRectangle.y);
        tiles[3][7].piece = new ChessPiece(PieceType.K, "BLACK", tiles[3][7].tileRectangle.x, tiles[3][7].tileRectangle.y);
        tiles[2][7].piece = new ChessPiece(PieceType.B, "BLACK", tiles[2][7].tileRectangle.x, tiles[2][7].tileRectangle.y);
        tiles[1][7].piece = new ChessPiece(PieceType.K, "BLACK", tiles[1][7].tileRectangle.x, tiles[1][7].tileRectangle.y);
        tiles[0][7].piece = new ChessPiece(PieceType.R, "BLACK", tiles[0][7].tileRectangle.x, tiles[0][7].tileRectangle.y);
    }

    // move its own class? input.java?
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
                    if (tiles[x][y].tileRectangle.contains(mousePosWorld) && tiles[x][y].piece != null && !hasPiece) {
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

                        tiles[selectedChessPieceTileXIndex][selectedChessPieceTileYIndex].piece = null;

                        tiles[x][y].piece.chessPieceRectangle.x = tiles[x][y].x;
                        tiles[x][y].piece.chessPieceRectangle.y = tiles[x][y].y;

                        tiles[x][y].piece.letter = tiles[x][y].letter;
                        tiles[x][y].piece.number = tiles[x][y].number;

                        moveSound.play();

                        hasPiece = false;
                    }
                }
            }
        }
    }

    public void decodeMove(String move) {

        System.out.println(move);

        String color = move.substring(0, 5);

        char originalPieceTypeChar = move.charAt(6);
        PieceType originalPieceType = null;
        char originalPieceLetter = move.charAt(8);
        int originalPieceNumber = Integer.parseInt(move.substring(9, 10));

        int originalXIndex = -1; // tile index will never be -1
        int originalYIndex = originalPieceNumber - 1;

        switch (originalPieceLetter) {
            case 'A':
                originalXIndex = 0;
                break;
            case 'B':
                originalXIndex = 1;
                break;   
            case 'C':
                originalXIndex = 2;                
                break;
            case 'D':
                originalXIndex = 3;
                break;
            case 'E':
                originalXIndex = 4;
                break;
            case 'F':
                originalXIndex = 5;
                break;       
            case 'G':
                originalXIndex = 6;
                break;    
            case 'H':
                originalXIndex = 7;
                break;                              
        }

        // there probably is a better way to do these convertions but its fine for now i suppose
        switch (originalPieceTypeChar) {
            case 'P':
                originalPieceType = PieceType.P;
                break;
            case 'B':
                originalPieceType = PieceType.B;
                break;
            case 'N':
                originalPieceType = PieceType.N;
                break;
            case 'R':
                originalPieceType = PieceType.R;
                break;
            case 'Q':
                originalPieceType = PieceType.Q;
                break;
            case 'K':
                originalPieceType = PieceType.K;
                break;
        }

        PieceType newPieceType = originalPieceType; // temp, will change when piece promotions becomes a thing
        char newPieceLetter = move.charAt(16);
        int newPieceNumber = Integer.parseInt(move.substring(17, 18));

        int newXIndex = -1; // tile index will never be -1
        int newYIndex = newPieceNumber - 1;

        // there probably is a better way to do these convertions but its fine for now i suppose
        switch (newPieceLetter) {
            case 'A':
                newXIndex = 0;
                break;
            case 'B':
                newXIndex = 1;
                break;   
            case 'C':
                newXIndex = 2;                
                break;
            case 'D':
                newXIndex = 3;
                break;
            case 'E':
                newXIndex = 4;
                break;
            case 'F':
                newXIndex = 5;
                break;       
            case 'G':
                newXIndex = 6;
                break;    
            case 'H':
                newXIndex = 7;
                break;                              
        }

        tiles[newXIndex][newYIndex].piece = tiles[originalXIndex][originalYIndex].piece;

        tiles[newXIndex][newYIndex].piece.letter = newPieceLetter;
        tiles[newXIndex][newYIndex].piece.number = newPieceNumber;
        tiles[newXIndex][newYIndex].piece.type = newPieceType;

        tiles[newXIndex][newYIndex].piece.chessPieceRectangle.x = tiles[newXIndex][newYIndex].x;
        tiles[newXIndex][newYIndex].piece.chessPieceRectangle.x = tiles[newXIndex][newYIndex].y;

        tiles[originalXIndex][originalYIndex].piece = null;

        //if (tiles[originalXIndex][originalYIndex].piece.color == color && tiles[originalXIndex][originalYIndex].piece.type == originalPieceType) {
            //tiles[newXIndex][newXIndex].piece = tiles[originalXIndex][originalYIndex].piece;
            //tiles[originalXIndex][originalYIndex].piece = null;
        //}

    }

    public void update(ScalingViewport viewport) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                tiles[x][y].update();
            }
        }

        movePiece(viewport);
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