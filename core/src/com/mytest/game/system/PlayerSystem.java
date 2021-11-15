package com.mytest.game.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mytest.game.component.*;

public class PlayerSystem extends EntitySystem {
    private final Family playersFamily = Family.all(PlayerComponent.class, CurrentPathComponent.class, ZombieComponent.class, TransformComponent.class).get();
    private final Family physicsMapFamily = Family.all(PhysicMapCellComponent.class).get();
    private final Family inputsFamily = Family.all(MouseInputComponent.class).get();
    private final Family treesFamily = Family.all(TreeComponent.class, TransformComponent.class).get();

    private final ComponentMapper<MouseInputComponent> mouseInputComponentComponentMapper = ComponentMapper.getFor(MouseInputComponent.class);
    private final ComponentMapper<ZombieComponent> zombieComponentComponentMapper = ComponentMapper.getFor(ZombieComponent.class);
    private final ComponentMapper<CurrentPathComponent> currentPathComponentComponentMapper = ComponentMapper.getFor(CurrentPathComponent.class);
    private final ComponentMapper<PlayerComponent> playerComponentComponentMapper = ComponentMapper.getFor(PlayerComponent.class);
    private final ComponentMapper<TransformComponent> transformComponentComponentMapper = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<SpriteComponent> spriteComponentComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
    private final ComponentMapper<TreeComponent> treeComponentComponentMapper = ComponentMapper.getFor(TreeComponent.class);
    private final ComponentMapper<PhysicMapCellComponent> physicMapCellComponentComponentMapper = ComponentMapper.getFor(PhysicMapCellComponent.class);
    OrthographicCamera orthographicCamera;

    public PlayerSystem(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> inputEntities = getEngine().getEntitiesFor(inputsFamily);
        ImmutableArray<Entity> mapEntities = getEngine().getEntitiesFor(physicsMapFamily);
        ImmutableArray<Entity> playerEntities = getEngine().getEntitiesFor(playersFamily);
        Entity playerEntity = playerEntities.first();
        Entity input = inputEntities.first();
        Entity physicMap = mapEntities.first();

        ZombieComponent zombieComponent = zombieComponentComponentMapper.get(playerEntity);
        CurrentPathComponent playerPathComponent = currentPathComponentComponentMapper.get(playerEntity);
        MouseInputComponent mouseInputComponent = mouseInputComponentComponentMapper.get(input);
        PhysicMapCellComponent physicMapCellComponent = physicMapCellComponentComponentMapper.get(physicMap);
        TransformComponent transformComponent = transformComponentComponentMapper.get(physicMap);

        if (mouseInputComponent.getRightClik().x != 0.0F || mouseInputComponent.getRightClik().y != 0.0F) {
            Vector3 worldCoordinates = orthographicCamera.unproject(new Vector3(mouseInputComponent.getRightClik().x, mouseInputComponent.getRightClik().y, 0));

            Vector2 targetIsometricPosition = worldToIsometricCoordinates(
                    worldCoordinates.x - transformComponent.getPosition().x,
                    worldCoordinates.y - transformComponent.getPosition().y,
                    physicMapCellComponent.getCellWidth(), physicMapCellComponent.getCellHeight());

            boolean woodcuttingStarted = false;
            for (Entity treeEntity : getEngine().getEntitiesFor(treesFamily)) {
                TreeComponent treeComponent = treeComponentComponentMapper.get(treeEntity);

                Vector2 treeIsometricPosition = new Vector2(treeComponent.isometricPosition.x, treeComponent.isometricPosition.y);

                if (targetIsometricPosition.x >= treeIsometricPosition.x &&
                        targetIsometricPosition.x <= treeIsometricPosition.x + treeComponent.physicalSize.x &&
                        targetIsometricPosition.y >= treeIsometricPosition.y &&
                        targetIsometricPosition.y <= treeIsometricPosition.y + treeComponent.physicalSize.y) {
                    if (treeComponent.state == TreeComponent.TreeState.TREE) {
                        zombieComponent.targetTreeEntity = treeEntity;
                        zombieComponent.state = ZombieComponent.State.BuildingTo;
                        woodcuttingStarted = true;
                    }
                    break;
                }
            }
            if (!woodcuttingStarted) {
                playerPathComponent.setEndPathPoint(new Vector2(worldCoordinates.x, worldCoordinates.y));
                zombieComponent.setState(ZombieComponent.State.Free);
                playerPathComponent.setState(CurrentPathComponent.State.Build);
            }
            mouseInputComponent.setRightClik(new Vector2(0.0F, 0.0F));
        }
    }

    public static Vector2 worldToIsometricCoordinates(float x, float y, float cellWidth, float cellHeight) {
        return new Vector2(
                Math.round(x / cellWidth - y / cellHeight),
                Math.round(x / cellWidth + y / cellHeight)
        );
    }

}

