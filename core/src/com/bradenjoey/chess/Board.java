package com.bradenjoey.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class Board {

    private Texture chessBoardTexture = new Texture(Gdx.files.internal("Board.png"));

    public String color;
    
    public Tile[][] tiles;

    private Vector3 mousePosWindow;
    private Vector2 mousePosWorld;

    private Tiles selectedTile = null;
	private int selectedTileXIndex;
	private int selctedTileYIndex;
    private boolean isScaled = false;

    private MoveValidator validator;

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
                for (int y = 7; y > -1; y--) {
                    switch (x) {
                        case 0:
                            tiles[x][y] = new Tile('H', y + 1, this.color);
                            break;
                        case 1:
                            tiles[x][y] = new Tile('G', y + 1, this.color);
                            break;
                        case 2:
                            tiles[x][y] = new Tile('F', y + 1, this.color);
                            break;
                        case 3:
                            tiles[x][y] = new Tile('E', y + 1, this.color);
                            break;
                        case 4:
                            tiles[x][y] = new Tile('D', y + 1, this.color);
                            break;
                        case 5:
                            tiles[x][y] = new Tile('C', y + 1, this.color);
                            break;
                        case 6:
                            tiles[x][y] = new Tile('B', y + 1, this.color);
                            break;
                        case 7:
                            tiles[x][y] = new Tile('A', y + 1, this.color);
                            break;
                    }
                }
            }
        }

        // TOP ROW
        tiles[0][1].tile = Tiles.PAWN;
        tiles[1][1].tile = Tiles.PAWN;
        tiles[2][1].tile = Tiles.PAWN;
        tiles[3][1].tile = Tiles.PAWN;
        tiles[4][1].tile = Tiles.PAWN;
        tiles[5][1].tile = Tiles.PAWN;
        tiles[6][1].tile = Tiles.PAWN;
        tiles[7][1].tile = Tiles.PAWN;

        // BOTTOM ROW
        tiles[0][0].tile = Tiles.ROOK;
        tiles[1][0].tile = Tiles.KNIGHT;
        tiles[2][0].tile = Tiles.BISHOP;
        tiles[3][0].tile = Tiles.QUEEN;
        tiles[4][0].tile = Tiles.KING;
        tiles[5][0].tile = Tiles.BISHOP;
        tiles[6][0].tile = Tiles.KNIGHT;
        tiles[7][0].tile = Tiles.ROOK;

    }

    public void input(ScalingViewport viewport) {
        // gets mouse pos in world space
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            // gets the window mouse pos
			mousePosWindow.set(Gdx.input.getX(), Gdx.input.getY(), 0);

			// changing this to viewport instend of camera took me legit 5 hours to figure out
			viewport.unproject(mousePosWindow);
			mousePosWorld.x = mousePosWindow.x;
			mousePosWorld.y = mousePosWindow.y;

            // moves the pieces
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {

                    if (tiles[x][y].pieceRectangle.contains(mousePosWorld)) {
                        System.out.print(tiles[x][y].letter);
                        System.out.println(tiles[x][y].number);

                        System.out.println("X: " + x + "Y: " + y);
                    }

					if (selectedTile == null && tiles[x][y].pieceRectangle.contains(mousePosWorld) && tiles[x][y].tile != Tiles.EMPTY) {
						if (!isScaled) {
                            tiles[x][y].pieceRectangle.width = 75;
                            tiles[x][y].pieceRectangle.height = 76;;
                            isScaled = true;
                        }

						selectedTile = tiles[x][y].tile;
						selectedTileXIndex = x;
						selctedTileYIndex = y;
					}
                    if (selectedTile != null && tiles[x][y].pieceRectangle.contains(mousePosWorld) && tiles[x][y].tile == Tiles.EMPTY) {
						tiles[x][y].tile = selectedTile;
						tiles[selectedTileXIndex][selctedTileYIndex].tile = Tiles.EMPTY;

                        selectedTile = null;

                        if (isScaled) {
                            tiles[x][y].pieceRectangle.width = 70;
                            tiles[x][y].pieceRectangle.height = 70.5f;
                            isScaled = false;
                        }

					}
				}
			}
		}
    }

    public void update(ScalingViewport viewport) {

        // gets input
        input(viewport);

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
