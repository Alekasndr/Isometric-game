package com.mytest.game.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.Main;
import com.mytest.game.component.PhysicMapCellComponent;
import com.mytest.game.component.TransformComponent;
import com.mytest.game.loaders.PhysicsMapLoader;
import com.mytest.game.utility.random.BuildingCheck;
import com.mytest.game.utility.physics.Cell;
import com.mytest.game.utility.physics.PhysicsMap;
import com.mytest.game.utility.random.TreeRandomCheck;
import com.mytest.game.utility.random.ZombieCheck;

import java.util.ArrayList;

public class RandomBuildingsPhysicMapEntity extends Entity {
    public RandomBuildingsPhysicMapEntity() {
        super();
        PhysicMapCellComponent physicsMapComponent = new PhysicMapCellComponent(PhysicsMapLoader.loadCells());
        add(physicsMapComponent);

        add(new TransformComponent(new Vector2(-465, 1837), new Vector2(0,0)));
        treeSpawn(physicsMapComponent.getPhysicsMap(), physicsMapComponent);
        buildingSpawn(physicsMapComponent.getPhysicsMap(), physicsMapComponent);
        zombieSpawn(physicsMapComponent.getPhysicsMap(), physicsMapComponent);
    }

    private void treeSpawn(PhysicsMap physicsMap, PhysicMapCellComponent physicsMapComponent) {
        TreeRandomCheck treeBuildCheck;
        ArrayList<Cell> trees;
        treeBuildCheck = new TreeRandomCheck();
        trees = treeBuildCheck.check(physicsMap);
        for(Cell cell : trees){

            Vector2 mapPosition = new Vector2(-465+25, 1837-138);
            Vector2 calc = isometricToWorldCoordinates(cell.getI(), cell.getJ(), physicsMapComponent.getCellWidth(),physicsMapComponent.getCellHeight());
            calc.x += mapPosition.x;
            calc.y += mapPosition.y;

            Main.EngineInstance.addEntity(new TreeEntity(calc, new Vector2(cell.getI(),cell.getJ())));
        }
    }

    private void buildingSpawn(PhysicsMap physicsMap, PhysicMapCellComponent physicsMapComponent){
        BuildingCheck buildingCheck;
        ArrayList<Cell> buildings;
        buildingCheck = new BuildingCheck();
        buildings = buildingCheck.check(physicsMap);
        for(Cell cell : buildings){

            Vector2 mapPosition = new Vector2(-465+92, 1837-60);
            Vector2 calc = isometricToWorldCoordinates(cell.getI(), cell.getJ(), physicsMapComponent.getCellWidth(),physicsMapComponent.getCellHeight());
            calc.x += mapPosition.x;
            calc.y += mapPosition.y;

            Main.EngineInstance.addEntity(new BuildingEntity(calc, new Vector2(cell.getI(),cell.getJ()))) ;
        }
    }

    private void zombieSpawn(PhysicsMap physicsMap, PhysicMapCellComponent physicsMapComponent){
        ZombieCheck zombieCheck;
        ArrayList<Cell> zombies;
        zombieCheck = new ZombieCheck();
        zombies = zombieCheck.check(physicsMap);
        for(Cell cell : zombies){

            Vector2 mapPosition = new Vector2(-465, 1837);
            Vector2 calc = isometricToWorldCoordinates(cell.getI(), cell.getJ(), physicsMapComponent.getCellWidth(),physicsMapComponent.getCellHeight());
            calc.x += mapPosition.x;
            calc.y += mapPosition.y;

            Main.EngineInstance.addEntity(new ZombieEntity(calc)) ;
        }
    }

    public static Vector2 isometricToWorldCoordinates(int x, int y, float cellWidth, float cellHeight) {
        return new Vector2(
                (y + x) * cellWidth / 2.0f,
                (y - x) * cellHeight / 2.0f
        );
    }
}
