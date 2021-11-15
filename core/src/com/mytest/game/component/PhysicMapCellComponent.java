package com.mytest.game.component;

import com.badlogic.ashley.core.Component;
import com.mytest.game.utility.map.Pathfinder;
import com.mytest.game.utility.physics.PhysicsMap;


public class PhysicMapCellComponent implements Component {
    private PhysicsMap physicsMap;
    private float cellMapWidth;
    private float cellMapHeight;
    final private float cellWidth = 32;
    final private float cellHeight = 16;
    private Pathfinder pathfinder;

    public PhysicMapCellComponent(PhysicsMap physicsMap) {
        super();
        this.physicsMap = physicsMap;
        this.cellMapWidth = physicsMap.getWidth();
        this.cellMapHeight = physicsMap.getHeight();
        this.pathfinder = new Pathfinder(physicsMap);
    }

    public PhysicsMap getPhysicsMap() {
        return physicsMap;
    }

    public float getCellMapWidth() {
        return cellMapWidth;
    }

    public float getCellMapHeight() {
        return cellMapHeight;
    }

    public float getCellWidth() {
        return cellWidth;
    }

    public float getCellHeight() {
        return cellHeight;
    }
}
