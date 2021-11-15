package com.mytest.game.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mytest.game.component.*;

import java.util.Comparator;

public class RenderSystem extends SortedIteratingSystem {
    private final ComponentMapper<TransformComponent> physicMapTransformComponentComponentMapper = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<SpriteComponent> spriteComponentComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
    OrthographicCamera orthographicCamera;
    SpriteBatch spriteBatch;

    public RenderSystem(OrthographicCamera orthographicCamera) {
        super(Family.all(SpriteComponent.class, TransformComponent.class).get(), new ZComparator());
        this.orthographicCamera = orthographicCamera;
        this.spriteBatch = new SpriteBatch();
    }

    public void update(float deltaTime) {
        forceSort();
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        for (Entity e : getEntities()) {
            TransformComponent transform = physicMapTransformComponentComponentMapper.get(e);
            SpriteComponent sprite = spriteComponentComponentMapper.get(e);

            if (sprite.isVisible()) {
                sprite.getTextureRegion().flip(sprite.isFlipX(), sprite.isFlipY());
                spriteBatch.draw(sprite.getTextureRegion(),
                        transform.getPosition().x - sprite.getOffsetX(), transform.getPosition().y - sprite.getOffsetY(), 0, 0,
                        sprite.getTextureRegion().getRegionWidth(), sprite.getTextureRegion().getRegionHeight(),
                        transform.getScale().x, transform.getScale().y, transform.getRotation());
                sprite.getTextureRegion().flip(sprite.isFlipX(), sprite.isFlipY());
            }
        }
        spriteBatch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
    }

    private static class ZComparator implements Comparator<Entity> {
        private final ComponentMapper<TransformComponent> transformMapper;

        public ZComparator() {
            transformMapper = ComponentMapper.getFor(TransformComponent.class);
        }

        @Override
        public int compare(Entity o1, Entity o2) {
            float y1 = transformMapper.get(o1).getPosition().y;
            float y2 = transformMapper.get(o2).getPosition().y;

            if (y1 < y2)
                return -1;
            else if (y1 > y2)
                return 1;

            OutfitComponent c1 = o1.getComponent(OutfitComponent.class);
            OutfitComponent c2 = o2.getComponent(OutfitComponent.class);
            if (c1 == null && c2 != null)
                return 1;
            else if (c1 != null && c2 == null)
                return -1;

            return 0;
        }
    }
}