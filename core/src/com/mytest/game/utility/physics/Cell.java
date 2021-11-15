package com.mytest.game.utility.physics;


import com.mytest.game.utility.map.Zone;

import java.util.Hashtable;

public class Cell {
    private int i;
    private int j;
    private Zone zone;
    private boolean availableForBuilding;
    private boolean locked;
    private boolean passable;

    public Cell(int zoneId, Hashtable<Integer, Zone> zones, int i, int j) {
        this.zone = zones.get(zoneId);
        this.i = i;
        this.j = j;
        this.availableForBuilding = zones.get(zoneId).isAvailableForBuilding();
        this.passable = zones.get(zoneId).isPassable();
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isAvailableForBuilding() {
        return availableForBuilding;
    }

    public void setAvailableForBuilding(boolean availableForBuilding) {
        this.availableForBuilding = availableForBuilding;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }
}