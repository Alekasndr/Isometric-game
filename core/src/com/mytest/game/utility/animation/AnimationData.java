package com.mytest.game.utility.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationData {
    private Array<TextureRegion> frames;
    private int frameTicks;
    private int offsetX;
    private int offsetY;

    public AnimationData(Array<TextureRegion> frames, int frameTicks, int offsetX, int offsetY) {
        this.frames = frames;
        this.frameTicks = frameTicks;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public Array<TextureRegion> getFrames() {
        return frames;
    }

    public int getFrameTicks() {
        return frameTicks;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }
}
