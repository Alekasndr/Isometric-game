package com.mytest.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent implements Component {
    private TextureRegion textureRegion;
    private boolean flipX;
    private boolean flipY;
    private int offsetX;
    private int offsetY;
    private boolean isVisible;

    public SpriteComponent(TextureRegion textureRegion, boolean flipX, boolean flipY, int offsetX, int offsetY) {
        this.textureRegion = textureRegion;
        this.flipX = flipX;
        this.flipY = flipY;
        this.offsetX = offsetX+127;
        this.offsetY = offsetY+115;
        this.isVisible = true;
    }

    public SpriteComponent(TextureRegion textureRegion, boolean flipX, boolean flipY) {
        this(textureRegion, flipX, flipY, 0, 0);
    }

    public SpriteComponent(TextureRegion textureRegion) {
        this(textureRegion, false, false);
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public boolean isFlipX() {
        return flipX;
    }

    public boolean isFlipY() {
        return flipY;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX += offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY += offsetY;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
