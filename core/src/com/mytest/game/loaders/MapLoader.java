package com.mytest.game.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.mytest.game.component.MapTilesComponent;


public class MapLoader {
    public static Array<MapTilesComponent.Tile> loadMap() {
        String configName = "maps/main_island/main_island_map_config.xml";
        String islandName = "main_island/";
        FileHandle fileHandle = Gdx.files.classpath(configName);

        XmlReader xmlReader = new XmlReader();
        XmlReader.Element parseConfig = xmlReader.parse(fileHandle);

        String image = parseConfig.getAttribute("image");
        String textureName = "maps/" + islandName + image;
        Texture texture = new Texture(textureName);

        int tileBorderSize = Integer.parseInt(parseConfig.getAttribute("tileBorderSize"));
        int tileHeight = Integer.parseInt(parseConfig.getAttribute("tileHeight"));
        int tileWidth = Integer.parseInt(parseConfig.getAttribute("tileWidth"));
        int tilesPerAtlasColumn = Integer.parseInt(parseConfig.getAttribute("tilesPerAtlasColumn"));
        int tilesPerAtlasRow = Integer.parseInt(parseConfig.getAttribute("tilesPerAtlasRow"));

        Array<TextureRegion> arrayForTextureRegion = new Array<TextureRegion>();
        for (int i = 0; i < tilesPerAtlasRow; i++) {
            for (int j = 0; j < tilesPerAtlasColumn; j++) {
                arrayForTextureRegion.add(new TextureRegion(texture, tileBorderSize + j * (tileWidth + tileBorderSize * 2), tileBorderSize + i * (tileHeight + tileBorderSize * 2), tileWidth, tileHeight));
            }
        }

        Array<MapTilesComponent.Tile> tileArray = new Array<MapTilesComponent.Tile>();
        XmlReader.Element offsetElement = parseConfig.getChildByName("offset").getChild(0);
        int offsetX = Integer.parseInt(offsetElement.getAttribute("x"));
        int offsetY = Integer.parseInt(offsetElement.getAttribute("y"));

        for (XmlReader.Element tileElement : parseConfig.getChildByName("items").getChildByName("list").getChildrenByName("Tile")) {
            tileArray.add(new MapTilesComponent.Tile(
                    Boolean.parseBoolean(tileElement.getAttribute("flipHorizontal")),
                    Boolean.parseBoolean(tileElement.getAttribute("flipVertical")),
                    Integer.parseInt(tileElement.getAttribute("height")),
                    Integer.parseInt(tileElement.getAttribute("width")),
                    Integer.parseInt(tileElement.getAttribute("x")) - offsetX,
                    Integer.parseInt(tileElement.getAttribute("y")) - offsetY,
                    arrayForTextureRegion.get(Integer.parseInt(tileElement.getAttribute("index")) - 1)
            ));
        }
        return tileArray;
    }

    public static Array<Float> loadScale() {
        String configName = "maps/main_island/main_island_map_config.xml";
        FileHandle fileHandle = Gdx.files.classpath(configName);
        XmlReader xmlReader = new XmlReader();
        XmlReader.Element parseConfig = xmlReader.parse(fileHandle);

        Array<Float> array = new Array<Float>();
        array.add(Float.parseFloat(parseConfig.getAttribute("defaultScale")));
        array.add(Float.parseFloat(parseConfig.getAttribute("maxScale")));
        array.add(Float.parseFloat(parseConfig.getAttribute("minScale")));

        return array;
    }

    public static Array<Float> loadMapMaxSize() {
        String configName = "maps/main_island/main_island_map_config.xml";
        FileHandle fileHandle = Gdx.files.classpath(configName);
        XmlReader xmlReader = new XmlReader();
        XmlReader.Element parseConfig = xmlReader.parse(fileHandle);

        Array<Float> array = new Array<Float>();
        array.add(Float.parseFloat(parseConfig.getAttribute("tileMapHeight")));
        array.add(Float.parseFloat(parseConfig.getAttribute("tileMapWidth")));
        return array;
    }
}
