package com.bradenjoey.chess;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board {
    
    public Tile[][] tiles;

    public Board(String color) {
        // chess board is 8 by 8
        tiles = new Tile[8][8];

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                switch (x) {
                    case 0:
                        tiles[x][y] = new Tile('A', y + 1, color);
                        break;
                    case 1:
                        tiles[x][y] = new Tile('B', y + 1, color);
                        break;
                    case 2:
                        tiles[x][y] = new Tile('C', y + 1, color);
                        break;
                    case 3:
                        tiles[x][y] = new Tile('D', y + 1, color);
                        break;
                    case 4:
                        tiles[x][y] = new Tile('E', y + 1, color);
                        break;
                    case 5:
                        tiles[x][y] = new Tile('F', y + 1, color);
                        break;
                    case 6:
                        tiles[x][y] = new Tile('G', y + 1, color);
                        break;
                    case 7:
                        tiles[x][y] = new Tile('H', y + 1, color);
                        break;
                }
            }
        }

    }

    public void render(SpriteBatch batch) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                tiles[x][y].render(batch);
            }
        }
    }

}
