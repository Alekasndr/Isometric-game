package com.mytest.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class MapTilesComponent implements Component {
    private final Array<Tile> tileArray;
    public MapTilesComponent(Array<Tile> tileArray){
        this.tileArray = tileArray;
    }
    public Array<Tile> getTileArray() {
        return tileArray;
    }
    public static class Tile {
        final boolean flipHorizontal;
        final boolean flipVertical;
        final int height;
        final int width;
        final int x;
        final int y;
        final TextureRegion textureRegion;

        public Tile(boolean flipHorizontal, boolean flipVertical, int height, int width, int x, int y, TextureRegion textureRegion) {
            this.flipHorizontal = flipHorizontal;
            this.flipVertical = flipVertical;
            this.height = height;
            this.width = width;
            this.x = x;
            this.y = y;
            this.textureRegion = textureRegion;
        }

        public boolean isFlipHorizontal() {
            return flipHorizontal;
        }

        public boolean isFlipVertical() {
            return flipVertical;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public TextureRegion getTextureRegion() {
            return textureRegion;
        }
    }
}
