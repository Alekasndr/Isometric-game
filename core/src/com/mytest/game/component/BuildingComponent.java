package com.mytest.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mytest.game.loaders.TileLoader;
import com.mytest.game.utility.random.BuildingCheck;
import com.mytest.game.utility.physics.Cell;
import com.mytest.game.utility.physics.PhysicsMap;

import java.util.ArrayList;

public class BuildingComponent implements Component {
    private TextureRegion buildTextureRegion;
    private int buildingOffsetX = 32; // -32
    private int buildingOffsetY = 177; // -177
    private BuildingCheck buildingCheck;
    private ArrayList<Cell> buildings;

    public BuildingComponent(PhysicsMap physicsMap) {
        this.buildTextureRegion = TileLoader.loadTexture("animations/b_sklep_0/", "b_sklep_0.xml", 3);

        buildingCheck = new BuildingCheck();
        buildings = buildingCheck.check(physicsMap);
    }

    public TextureRegion getBuildTextureRegion() {
        return buildTextureRegion;
    }
}
