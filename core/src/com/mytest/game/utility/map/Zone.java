package com.mytest.game.utility.map;

public class Zone {
    private int id;
    private boolean availableForBuilding;
    private int color;
    private boolean locked;
    private String name;
    private boolean passable;

    public Zone(int id, boolean availableForBuilding, int color, boolean locked, String name, boolean passable) {
        this.id = id;
        this.availableForBuilding = availableForBuilding;
        this.color = color;
        this.locked = locked;
        this.name = name;
        this.passable = passable;
    }

    public boolean isAvailableForBuilding() {
        return availableForBuilding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }
}
