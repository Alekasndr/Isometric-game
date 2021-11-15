package com.mytest.game.utility.random;

import com.mytest.game.utility.physics.Cell;
import com.mytest.game.utility.physics.PhysicsMap;

import java.util.ArrayList;
import java.util.Random;

public abstract class RandomCheck {
    public ArrayList<Cell> check(PhysicsMap physicsMap) {
        int numberOfBuildings = randomInBorders(3, 6);
        ArrayList<Cell> array = new ArrayList<>();
        while (array.size() < numberOfBuildings) {
            array.add(cellCheck(physicsMap));
        }
        return array;
    }

    public abstract Cell cellCheck(PhysicsMap physicsMap);

    public int randomInBorders(int min, int max) {
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return i;
    }
}
