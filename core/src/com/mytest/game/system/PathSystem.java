package com.mytest.game.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.component.*;
import com.mytest.game.utility.map.Pathfinder;


public class PathSystem extends EntitySystem {
    private final Family movingFamily = Family.all(CurrentPathComponent.class, TransformComponent.class).get();
    private final Family physicsMapsFamily = Family.all(PhysicMapCellComponent.class).get();

    private final ComponentMapper<CurrentPathComponent> currentPathComponentComponentMapper = ComponentMapper.getFor(CurrentPathComponent.class);
    private final ComponentMapper<SpriteComponent> spriteComponentComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
    private final ComponentMapper<ZombieComponent> zombieComponentComponentMapper = ComponentMapper.getFor(ZombieComponent.class);
    private final ComponentMapper<PhysicMapCellComponent> physicMapCellComponentComponentMapper = ComponentMapper.getFor(PhysicMapCellComponent.class);
    private final ComponentMapper<TransformComponent> transformComponentComponentMapper = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<AnimationComponent> animationComponentComponentMapper = ComponentMapper.getFor(AnimationComponent.class);
    private final ComponentMapper<OutfitComponent> outfitComponentComponentMapper = ComponentMapper.getFor(OutfitComponent.class);
    private final ComponentMapper<PlayerComponent> playerComponentComponentMapper = ComponentMapper.getFor(PlayerComponent.class);
    Pathfinder pathfinder;

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> physicsMapsEntities = getEngine().getEntitiesFor(physicsMapsFamily);
        Entity physicsMapEntity = physicsMapsEntities.first();
        PhysicMapCellComponent physicMapCellComponent = physicMapCellComponentComponentMapper.get(physicsMapEntity);
        pathfinder = new Pathfinder(physicMapCellComponent.getPhysicsMap());
        TransformComponent physicsMapTransformComponent = transformComponentComponentMapper.get(physicsMapEntity);
        for (Entity movingEntity : getEngine().getEntitiesFor(movingFamily)) {
            ZombieComponent zombieComponent = zombieComponentComponentMapper.get(movingEntity);
            CurrentPathComponent currentPathComponent = currentPathComponentComponentMapper.get(movingEntity);
            TransformComponent zombieTransformComponent = transformComponentComponentMapper.get(movingEntity);
            AnimationComponent animationComponent = animationComponentComponentMapper.get(movingEntity);

            switch (currentPathComponent.state) {
                case Build:
                    Vector2 startIsometricPosition = worldToIsometricCoordinates(
                            zombieTransformComponent.getPosition().x - physicsMapTransformComponent.getPosition().x,
                            zombieTransformComponent.getPosition().y - physicsMapTransformComponent.getPosition().y,
                            physicMapCellComponent.getCellWidth(), physicMapCellComponent.getCellHeight());

                    Vector2 endIsometricPosition = worldToIsometricCoordinates(
                            currentPathComponent.getEndPathPoint().x - physicsMapTransformComponent.getPosition().x,
                            currentPathComponent.getEndPathPoint().y - physicsMapTransformComponent.getPosition().y,
                            physicMapCellComponent.getCellWidth(), physicMapCellComponent.getCellHeight());

                    currentPathComponent.setPath(pathfinder.BuildPath(
                            (int) startIsometricPosition.x, (int) startIsometricPosition.y,
                            (int) endIsometricPosition.x, (int) endIsometricPosition.y,
                            physicMapCellComponent.getCellWidth(), physicMapCellComponent.getCellHeight(),
                            physicsMapTransformComponent.getPosition()));

                    if (currentPathComponent.getPath().size > 1) {
                        setWhiteWaveAnimation(movingEntity, currentPathComponent.getEndPathPoint(), true);
                        currentPathComponent.setNextPathPoint(currentPathComponent.getPath().removeFirst());
                        currentPathComponent.state = CurrentPathComponent.State.Move;
                    } else {

                        setWhiteWaveAnimation(movingEntity, new Vector2(), false);
                        currentPathComponent.state = CurrentPathComponent.State.Free;
                        ZombieComponent zombieComponent1 = zombieComponentComponentMapper.get(movingEntity);
                        if (zombieComponent1 != null) {
                            zombieComponent1.setState(ZombieComponent.State.Free);
                        }
                        zombieComponent.setAnimationState(getZombieState(
                                animationComponent, new Vector2(), zombieComponent.hasLog));
                        updateOutfitPosition(movingEntity, animationComponent);
                    }
                    break;
                case Move:
                    float shift = 100F * deltaTime;
                    Vector2 diff = new Vector2(currentPathComponent.getNextPathPoint()).sub(zombieTransformComponent.getPosition());
                    float distance = diff.len();
                    if (distance < shift) {
                        zombieTransformComponent.setPosition(currentPathComponent.getNextPathPoint());
                        if (currentPathComponent.getPath().size > 0) {
                            currentPathComponent.setNextPathPoint(currentPathComponent.getPath().removeFirst());
                        } else {
                            currentPathComponent.state = CurrentPathComponent.State.Free;
                            setWhiteWaveAnimation(movingEntity, new Vector2(), false);
                            diff = new Vector2();
                        }
                    } else zombieTransformComponent.getPosition().add(diff.nor().scl(shift));
                    if (zombieComponent != null) {
                        zombieComponent.setAnimationState(getZombieState(
                                animationComponent, diff, zombieComponent.hasLog));
                        updateOutfitPosition(movingEntity, animationComponent);
                    }
                case Free:
                default:
                    break;
            }
        }
    }

    private ZombieComponent.ZombieState getZombieState(AnimationComponent animationComponent, Vector2 derection, boolean haslog) {
        if (derection.len() == 0) {
            animationComponent.setFlipX(true);
            return ZombieComponent.ZombieState.STAND;
        }
        ZombieComponent.ZombieState action;
        animationComponent.setFlipX(derection.x > 0);
        if (derection.y < 0) {
            action = haslog ? ZombieComponent.ZombieState.WALKWOOD_UP : ZombieComponent.ZombieState.WALK_UP;
        } else {
            action = haslog ? ZombieComponent.ZombieState.WALKWOOD_DOWN : ZombieComponent.ZombieState.WALK_DOWN;
        }
        return action;
    }

    private void updateOutfitPosition(Entity clothesEntity, AnimationComponent animationComponent) {
        OutfitComponent outfitComponent = outfitComponentComponentMapper.get(clothesEntity);
        TransformComponent movingTransformComponent = transformComponentComponentMapper.get(clothesEntity);
        if (outfitComponent != null) {
            if (outfitComponent.isHasHat()) {
                transformComponentComponentMapper.get(outfitComponent.getHatEntity()).setPosition(movingTransformComponent.getPosition());
                animationComponentComponentMapper.get(outfitComponent.getHatEntity()).setFlipX(animationComponent.isFlipX());
            }
            if (outfitComponent.isHasCloth()) {
                transformComponentComponentMapper.get(outfitComponent.getClothEntity()).setPosition(movingTransformComponent.getPosition());
                animationComponentComponentMapper.get(outfitComponent.getClothEntity()).setFlipX(animationComponent.isFlipX());
            }
        }
    }

    public static Vector2 worldToIsometricCoordinates(float x, float y, float cellWidth, float cellHeight) {
        return new Vector2(
                Math.round(x / cellWidth - y / cellHeight),
                Math.round(x / cellWidth + y / cellHeight)
        );
    }

    private void setWhiteWaveAnimation(Entity movingEntity, Vector2 position, boolean mode) {
        PlayerComponent playerComponent = playerComponentComponentMapper.get(movingEntity);
        if (playerComponent != null) {
            Vector2 temp = new Vector2(position.x + 95, position.y + 98);
            transformComponentComponentMapper.get(playerComponent.mouseAnimationEntity).setPosition(temp);
            spriteComponentComponentMapper.get(playerComponent.mouseAnimationEntity).setVisible(mode);
        }
    }
}
