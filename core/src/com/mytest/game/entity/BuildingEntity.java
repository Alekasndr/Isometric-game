package com.mytest.game.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.component.*;
import com.mytest.game.loaders.PhysicsMapLoader;

public class BuildingEntity extends Entity {
    public BuildingEntity(Vector2 pos, Vector2 cellPos) {
        super();
        add(new TransformComponent(pos));
        add(new SpriteComponent(new BuildingComponent(PhysicsMapLoader.loadCells()).getBuildTextureRegion()));
    }
}
