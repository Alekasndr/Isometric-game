package com.mytest.game.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mytest.game.component.MapTilesComponent;

public class MapRenderSystem extends IteratingSystem {
    private final ComponentMapper<MapTilesComponent> mapTilesComponents = ComponentMapper.getFor(MapTilesComponent.class);
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera orthographicCamera;

    public MapRenderSystem(SpriteBatch spriteBatch, OrthographicCamera orthographicCamera) {
        super(Family.all(MapTilesComponent.class).get());
        this.spriteBatch = spriteBatch;
        this.orthographicCamera = orthographicCamera;
    }

    public void processEntity(Entity entity, float deltaTime) {
        MapTilesComponent mapComponent = mapTilesComponents.get(entity);
        for (MapTilesComponent.Tile tile : mapComponent.getTileArray()) {
            int width = tile.getWidth();
            int hight = tile.getHeight();
            int x = tile.getX();
            int y = tile.getY();
            if (tile.isFlipHorizontal()) {
                x += width;
                width = -width;
            }
            if (!tile.isFlipVertical()) {
                y += hight;
                hight = -hight;
            }
            spriteBatch.setProjectionMatrix(orthographicCamera.combined);
            spriteBatch.begin();
            spriteBatch.draw(tile.getTextureRegion(), x, y, width, hight);
            spriteBatch.end();
        }
    }
}
