package com.mytest.game.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.XmlReader;

public class TileLoader {
    public static TextureRegion loadTexture(String path, String name, int number) {
        String xmlName = path + name;
        FileHandle fileHandle = Gdx.files.classpath(xmlName);
        XmlReader xmlReader = new XmlReader();
        XmlReader.Element parseConfig = xmlReader.parse(fileHandle);

        String pngName = path + parseConfig.getAttribute("animationTexture");
        Texture texture = new Texture(pngName);

        int height = Integer.parseInt(parseConfig.getChildByName("frames").getChildByName("list").getChildByName("Frame").getAttribute("height"));
        int width = Integer.parseInt(parseConfig.getChildByName("frames").getChildByName("list").getChildByName("Frame").getAttribute("width"));

        TextureRegion textureRegion = new TextureRegion(texture, (width * number) + width, height, -width, -height);

        return textureRegion;
    }
}
