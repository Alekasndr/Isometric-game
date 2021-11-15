package com.mytest.game.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.component.SpriteComponent;
import com.mytest.game.component.TransformComponent;
import com.mytest.game.component.TreeComponent;
import com.mytest.game.loaders.PhysicsMapLoader;

public class TreeEntity extends Entity {
    public TreeEntity(Vector2 pos, Vector2 cellPos) {
        super();
        add(new TreeComponent(PhysicsMapLoader.loadCells(), cellPos));
        add(new TransformComponent(pos, cellPos));
        add(new SpriteComponent(new TreeComponent(PhysicsMapLoader.loadCells(), cellPos).getTreeTexturRegion()));
    }
}
