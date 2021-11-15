package com.mytest.game.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.component.*;

public class WoodcutSystem extends EntitySystem {
    private final Family woodcuttersFamily = Family.all(ZombieComponent.class, CurrentPathComponent.class, TransformComponent.class).get();
    private final Family physicsMapsFamily = Family.all(PhysicMapCellComponent.class).get();

    private final ComponentMapper<TransformComponent> transformComponentComponentMapper = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<CurrentPathComponent> currentPathComponentComponentMapper = ComponentMapper.getFor(CurrentPathComponent.class);
    private final ComponentMapper<ZombieComponent> zombieComponentComponentMapper = ComponentMapper.getFor(ZombieComponent.class);
    private final ComponentMapper<PhysicMapCellComponent> physicMapCellComponentComponentMapper = ComponentMapper.getFor(PhysicMapCellComponent.class);
    private final ComponentMapper<TreeComponent> treeComponentComponentMapper = ComponentMapper.getFor(TreeComponent.class);

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> physicsMapsEntities = getEngine().getEntitiesFor(physicsMapsFamily);

        for (Entity woodcutterEntity : getEngine().getEntitiesFor(woodcuttersFamily)) {
            ZombieComponent woodcutterComponent = zombieComponentComponentMapper.get(woodcutterEntity);
            CurrentPathComponent woodcutterPathComponent = currentPathComponentComponentMapper.get(woodcutterEntity);
            TransformComponent woodcutterTransformComponent = transformComponentComponentMapper.get(woodcutterEntity);

            if (woodcutterComponent.getFinishPosition() != null &&
                    woodcutterComponent.getFinishPosition().x == woodcutterTransformComponent.getPosition().x &&
                    woodcutterComponent.getFinishPosition().y == woodcutterTransformComponent.getPosition().y) {
                if (woodcutterComponent.state == ZombieComponent.State.MovingTo) {
                    woodcutterComponent.state = ZombieComponent.State.Cutting;
                    woodcutterComponent.setWoodcutTimer(0);
                } else if (woodcutterComponent.state == ZombieComponent.State.MovingFrom) {
                    woodcutterComponent.state = ZombieComponent.State.Free;
                }
            }

            switch (woodcutterComponent.state) {
                case BuildingTo:
                    Entity physicsMapEntity = physicsMapsEntities.first();
                    PhysicMapCellComponent physicMapCellComponent = physicMapCellComponentComponentMapper.get(physicsMapEntity);
                    TransformComponent physicMapTransformComponent = transformComponentComponentMapper.get(physicsMapEntity);

                    TreeComponent treeComponent = treeComponentComponentMapper.get(woodcutterComponent.targetTreeEntity);

                    if (treeComponent.state == TreeComponent.TreeState.TREE) {
                        Vector2 calc;
                        if (physicMapCellComponent.getPhysicsMap().getCell((int) (treeComponent.isometricPosition.x - 1),
                                (int) treeComponent.isometricPosition.y) != null && physicMapCellComponent.getPhysicsMap().getCell((int) (treeComponent.isometricPosition.x - 1),
                                (int) treeComponent.isometricPosition.y).isPassable()) {
                            calc = isometricToWorldCoordinates((int) treeComponent.isometricPosition.x - 1,
                                    (int) treeComponent.isometricPosition.y, physicMapCellComponent.getCellWidth(),
                                    physicMapCellComponent.getCellHeight()).add(physicMapTransformComponent.getPosition());
                        } else {
                            calc = isometricToWorldCoordinates((int) treeComponent.isometricPosition.x,
                                    (int) treeComponent.isometricPosition.y - 1, physicMapCellComponent.getCellWidth(),
                                    physicMapCellComponent.getCellHeight()).add(physicMapTransformComponent.getPosition());
                        }

                        woodcutterPathComponent.setEndPathPoint(calc);
                        woodcutterPathComponent.state = CurrentPathComponent.State.Build;
                        woodcutterComponent.setStartPosition(woodcutterTransformComponent.getPosition());
                        woodcutterComponent.setFinishPosition(calc);
                        woodcutterComponent.state = ZombieComponent.State.MovingTo;
                    } else {
                        woodcutterComponent.state = ZombieComponent.State.Free;
                    }
                case Cutting:
                    if (woodcutterComponent.getWoodcutTimer() == 0) {
                        woodcutterComponent.animationState = ZombieComponent.ZombieState.WOODCUT;
                    }
                    woodcutterComponent.setWoodcutTimer(woodcutterComponent.getWoodcutTimer() + deltaTime);

                    // woodcutting finish -> path back
                    if (woodcutterComponent.getWoodcutTimer() >= 2.0f) {
                        woodcutterComponent.state = ZombieComponent.State.BuildingFrom;
                    }
                    break;
                case BuildingFrom:
                    woodcutterComponent.hasLog = true;
                    woodcutterComponent.state = ZombieComponent.State.MovingFrom;

                    treeComponentComponentMapper.get(woodcutterComponent.targetTreeEntity).state = TreeComponent.TreeState.STUMP;

                    woodcutterPathComponent.setEndPathPoint(woodcutterComponent.getStartPosition());
                    woodcutterPathComponent.state = CurrentPathComponent.State.Build;
                    break;
                case MovingTo:
                case MovingFrom:
                case Free:
                default:
                    woodcutterComponent.setWoodcutTimer(0);
                    break;
            }
        }
    }

    public static Vector2 isometricToWorldCoordinates(int x, int y, float cellWidth, float cellHeight) {
        return new Vector2(
                (y + x) * cellWidth / 2.0f,
                (y - x) * cellHeight / 2.0f
        );
    }
}
