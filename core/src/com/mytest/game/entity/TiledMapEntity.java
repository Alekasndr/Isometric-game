package com.mytest.game.entity;

import com.badlogic.ashley.core.Entity;
import com.mytest.game.component.MapTilesComponent;
import com.mytest.game.loaders.MapLoader;

public class TiledMapEntity extends Entity {
    public TiledMapEntity() {
        super();
        add(new MapTilesComponent(MapLoader.loadMap()));
    }
}
