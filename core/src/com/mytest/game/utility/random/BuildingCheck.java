package com.mytest.game.utility.random;

import com.mytest.game.utility.physics.Cell;
import com.mytest.game.utility.physics.PhysicsMap;

public class BuildingCheck extends RandomCheck {
    @Override
    public Cell cellCheck(PhysicsMap physicsMap) {
        while (true) {
            int i = randomInBorders(22, 135);
            int j = randomInBorders(0, 113);
            Cell cell = lookingForPos(physicsMap, i, j);
            if (cell != null) {
                return cell;
            }
        }
    }

    private Cell lookingForPos(PhysicsMap physicsMap, int i, int j) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 4; y++) {
                Cell cell = physicsMap.getCell(i + x, j + y);
                if (cell == null) {
                    return null;
                }
                if (!cell.isAvailableForBuilding()) {
                    return null;
                }
            }
        }
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 4; y++) {
                physicsMap.getCell(i + x, j + y).setAvailableForBuilding(false);
                physicsMap.getCell(i + x, j + y).setPassable(false);
            }
        }
        return physicsMap.getCell(i, j);
    }
}

