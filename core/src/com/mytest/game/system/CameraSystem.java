package com.mytest.game.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.component.MouseInputComponent;
import com.mytest.game.loaders.MapLoader;

public class CameraSystem extends EntitySystem {
    private ImmutableArray<Entity> mouseEntities;
    private ComponentMapper<MouseInputComponent> mouseInputComponentComponentMapper = ComponentMapper.getFor(MouseInputComponent.class);
    OrthographicCamera orthographicCamera;
    SpriteBatch spriteBatch;
    private float zoom = MapLoader.loadScale().get(0);
    private float zoomMax = MapLoader.loadScale().get(1);
    private float zoomMin = MapLoader.loadScale().get(2);

    public CameraSystem(OrthographicCamera orthographicCamera, SpriteBatch batch) {
        this.orthographicCamera = orthographicCamera;
        this.spriteBatch = batch;
    }

    public void addedToEngine(Engine engine) {
        mouseEntities = engine.getEntitiesFor(Family.all(MouseInputComponent.class).get());
    }

    public void update(float deltaTime) {
        for (int i = 0; i < mouseEntities.size(); ++i) {
            Entity entity = mouseEntities.get(i);
            MouseInputComponent mouseInputComponent = mouseInputComponentComponentMapper.get(entity);
            if (mouseInputComponent.getZoomScroll() > zoomMin && mouseInputComponent.getZoomScroll() < zoomMax) {
                zoom = mouseInputComponent.getZoomScroll();
                orthographicCamera.zoom = zoom;
                orthographicCamera.update();
                spriteBatch.setProjectionMatrix(orthographicCamera.combined);
            }
            if (mouseInputComponent.getDrag().x != 0.0F || mouseInputComponent.getDrag().y != 0.0F) {
                orthographicCamera.translate(-mouseInputComponent.getDrag().x * zoom,
                        -mouseInputComponent.getDrag().y * zoom);
                orthographicCamera.update();
                mouseInputComponent.setDrag(new Vector2(0.0F, 0.0F));
            }
        }
    }
}
