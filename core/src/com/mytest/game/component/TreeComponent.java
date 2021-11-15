package com.mytest.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.mytest.game.loaders.TileLoader;
import com.mytest.game.utility.physics.PhysicsMap;

public class TreeComponent implements Component {
    private TextureRegion treeTexturRegion;
    private TextureRegion stumpTexturRegion;
    public int treeOffsetX = 25;
    public int treeOffsetY = -138;
    public TreeState state;
    public Vector2 physicalSize;
    public Vector2 isometricPosition;
    public int stampOffsetX = 117;
    public int stampOffsetY = 72;

    public TreeComponent(PhysicsMap physicsMap, Vector2 isomPos) {
        this.stumpTexturRegion = TileLoader.loadTexture("animations/t_oak3_stump/", "t_oak3_stump.xml", 0);
        this.treeTexturRegion = TileLoader.loadTexture("animations/t_oak3_0/", "t_oak3_0.xml", 0);
        this.physicalSize = new Vector2(1, 1);
        this.state = TreeState.TREE;
        this.isometricPosition = isomPos;
    }

    public TextureRegion getTreeTexturRegion() {
        return treeTexturRegion;
    }

    public enum TreeState {
        TREE,
        STUMP
    }

    public TextureRegion getStumpTexturRegion() {
        return stumpTexturRegion;
    }
}
