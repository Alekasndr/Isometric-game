package com.mytest.game.utility.physics;

public class PhysicsMap {
    private Cell[][] cells;
    private int width;
    private int height;

    public PhysicsMap(int width, int height) {
        cells = new Cell[width][height];
        this.width = width;
        this.height = height;
    }

    public void SetCell(int x, int y, Cell cell) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }
        cells[x][y] = cell;
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return cells[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
