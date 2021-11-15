package com.mytest.game.loaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UiLoader {
    public static TextureRegionDrawable getClothButtonRegion1() {
        String pngName = "button3.png";
        Texture texture = new Texture(pngName);
        return new TextureRegionDrawable(texture);
    }

    public static TextureRegionDrawable getHatButtonRegion1() {
        String pngName = "hatButton3.png";
        Texture texture = new Texture(pngName);
        return new TextureRegionDrawable(texture);
    }
}
