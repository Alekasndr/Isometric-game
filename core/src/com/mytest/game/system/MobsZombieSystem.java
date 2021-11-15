package com.mytest.game.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.component.*;

import java.util.Random;

public class MobsZombieSystem extends EntitySystem {
    private final Family zombiesFamily = Family.all(AnimationComponent.class, MobsZombieComponent.class, CurrentPathComponent.class, ZombieComponent.class, TransformComponent.class).get();
    private final Family treesFamily = Family.all(TreeComponent.class, TransformComponent.class).get();
    private final Family physicsMapsFamily = Family.all(PhysicMapCellComponent.class).get();

    private final ComponentMapper<CurrentPathComponent> currentPathComponentComponentMapper = ComponentMapper.getFor(CurrentPathComponent.class);
    private final ComponentMapper<ZombieComponent> zombieComponentComponentMapper = ComponentMapper.getFor(ZombieComponent.class);
    private final ComponentMapper<TransformComponent> transformComponentComponentMapper = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<TreeComponent> treeComponentComponentMapper = ComponentMapper.getFor(TreeComponent.class);
    private final ComponentMapper<PhysicMapCellComponent> physicMapCellComponentComponentMapper = ComponentMapper.getFor(PhysicMapCellComponent.class);
    private final float zombue_wudocut_chance;
    private final float zombue_move_chance;
    private final float zombue_actions;
    private final float zombie_walking_range;
    Random random;

    public MobsZombieSystem() {
        this.random = new Random();
        this.zombue_move_chance = 0.9f;
        this.zombue_wudocut_chance = 1 - this.zombue_move_chance;
        this.zombue_actions = 0.4f;
        this.zombie_walking_range = 15f;
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> physicsMapEntities = getEngine().getEntitiesFor(physicsMapsFamily);
        Entity physicsMapEntity = physicsMapEntities.first();
        PhysicMapCellComponent physicMapCellComponent = physicMapCellComponentComponentMapper.get(physicsMapEntity);
        TransformComponent transformComponent = transformComponentComponentMapper.get(physicsMapEntity);

        for (Entity zombieEntity : getEngine().getEntitiesFor(zombiesFamily)) {
            TransformComponent zombieTransformComponent = transformComponentComponentMapper.get(zombieEntity);
            CurrentPathComponent zombieCurrentPathComponent = currentPathComponentComponentMapper.get(zombieEntity);
            ZombieComponent zombieComponent = zombieComponentComponentMapper.get(zombieEntity);

            if (zombieCurrentPathComponent.state == CurrentPathComponent.State.Free && zombieComponent.state == ZombieComponent.State.Free) {
                float randomValue = random.nextFloat() / deltaTime;
                if (randomValue < zombue_actions) {
                    randomValue = random.nextFloat();

                    if (randomValue < zombue_move_chance) {
                        int highBound = (int) (zombie_walking_range * 2 + 1);
                        Vector2 targetIsometricPosition = worldToIsometricCoordinates(
                                zombieTransformComponent.getPosition().x - transformComponent.getPosition().x,
                                zombieTransformComponent.getPosition().y - transformComponent.getPosition().y,
                                physicMapCellComponent.getCellWidth(), physicMapCellComponent.getCellHeight())
                                .add(random.nextInt(highBound) - zombie_walking_range,
                                        random.nextInt(highBound) - zombie_walking_range);

                        zombieCurrentPathComponent.setEndPathPoint(isometricToWorldCoordinates(
                                (int) targetIsometricPosition.x, (int) targetIsometricPosition.y,
                                physicMapCellComponent.getCellWidth(), physicMapCellComponent.getCellHeight())
                                .add(transformComponent.getPosition()));
                        zombieCurrentPathComponent.state = CurrentPathComponent.State.Build;
                    } else if (randomValue < zombue_wudocut_chance + zombue_move_chance) {
                        ImmutableArray<Entity> treesEntities = getEngine().getEntitiesFor(treesFamily);
                        Entity treeEntity = treesEntities.get(random.nextInt(treesEntities.size()));
                        TreeComponent treeComponent = treeComponentComponentMapper.get(treeEntity);

                        if (treeComponent.state == TreeComponent.TreeState.TREE) {
                            zombieComponent.targetTreeEntity = treeEntity;
                            zombieComponent.state = ZombieComponent.State.BuildingTo;
                        }
                    }
                }
            }
        }
    }

    public static Vector2 worldToIsometricCoordinates(float x, float y, float cellWidth, float cellHeight) {
        return new Vector2(
                Math.round(x / cellWidth - y / cellHeight),
                Math.round(x / cellWidth + y / cellHeight)
        );
    }

    public static Vector2 isometricToWorldCoordinates(int x, int y, float cellWidth, float cellHeight) {
        return new Vector2(
                (y + x) * cellWidth / 2.0f,
                (y - x) * cellHeight / 2.0f
        );
    }
}
