package com.mytest.game.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.component.*;
import com.mytest.game.utility.animation.AnimationData;
import com.mytest.game.utility.animation.HasMaps;

public class AnimationSystem extends EntitySystem {
    private final Family animatedEntitiesFamily = Family.all(AnimationComponent.class, SpriteComponent.class).get();
    private final Family animatedZombieFamily = Family.all(AnimationComponent.class, ZombieComponent.class).get();
    private final Family animatedTreesFamily = Family.all(SpriteComponent.class, TreeComponent.class, TransformComponent.class).get();

    private final ComponentMapper<AnimationComponent> animationComponentComponentMapper = ComponentMapper.getFor(AnimationComponent.class);
    private final ComponentMapper<SpriteComponent> spriteComponentComponentMapper = ComponentMapper.getFor(SpriteComponent.class);
    private final ComponentMapper<ZombieComponent> zombieComponentComponentMapper = ComponentMapper.getFor(ZombieComponent.class);
    private final ComponentMapper<OutfitComponent> outfitComponentComponentMapper = ComponentMapper.getFor(OutfitComponent.class);
    private final ComponentMapper<TransformComponent> transformComponentComponentMapper = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<TreeComponent> treeComponentComponentMapper = ComponentMapper.getFor(TreeComponent.class);

    @Override
    public void update(float deltaTime) {
        for (Entity zombieEntity : getEngine().getEntitiesFor(animatedZombieFamily)) {
            AnimationComponent animationComponent = animationComponentComponentMapper.get(zombieEntity);
            ZombieComponent zombieComponent = zombieComponentComponentMapper.get(zombieEntity);

            String newAnimation = HasMaps.WOODCUTTER_ANIMATIONS_MAP.get(animationComponent.getAnimationsName()).get(zombieComponent.getAnimationState());
            if (!animationComponent.getCurrentAnimation().equals(newAnimation)) {
                animationComponent.setCurrentAnimation(newAnimation);
                setAnimation(animationComponent);

                OutfitComponent outfitComponent = outfitComponentComponentMapper.get(zombieEntity);
                if (outfitComponent != null) {
                    if (outfitComponent.isHasHat()) {
                        AnimationComponent hatAnimationComponent = animationComponentComponentMapper.get(outfitComponent.getHatEntity());
                        hatAnimationComponent.setCurrentAnimation(HasMaps.WOODCUTTER_ANIMATIONS_MAP.get(hatAnimationComponent.getAnimationsName()).get(zombieComponent.getAnimationState()));
                        setAnimation(hatAnimationComponent);
                        hatAnimationComponent.setFlipX(animationComponent.isFlipX());
                        hatAnimationComponent.setStateTime(animationComponent.getStateTime());
                    }
                    if (outfitComponent.isHasCloth()) {
                        AnimationComponent clothAnimationComponent = animationComponentComponentMapper.get(outfitComponent.getClothEntity());
                        clothAnimationComponent.setCurrentAnimation(HasMaps.WOODCUTTER_ANIMATIONS_MAP.get(clothAnimationComponent.getAnimationsName()).get(zombieComponent.getAnimationState()));
                        setAnimation(clothAnimationComponent);
                        clothAnimationComponent.setFlipX(animationComponent.isFlipX());
                        clothAnimationComponent.setStateTime(animationComponent.getStateTime());
                    }
                }
            }
        }

        for (Entity treeEntity : getEngine().getEntitiesFor(animatedTreesFamily)) {
            SpriteComponent spriteComponent = spriteComponentComponentMapper.get(treeEntity);
            TransformComponent transformComponent = transformComponentComponentMapper.get(treeEntity);
            TreeComponent treeComponent = treeComponentComponentMapper.get(treeEntity);

            if (treeComponent.state == TreeComponent.TreeState.STUMP) {
                Vector2 mapPosition = new Vector2(-465 + 117, 1837 + 72);
                Vector2 calc = isometricToWorldCoordinates((int) treeComponent.isometricPosition.x, (int) treeComponent.isometricPosition.y, 32, 16);
                calc.x += mapPosition.x;
                calc.y += mapPosition.y;
                transformComponent.setPosition(new Vector2(calc.x, calc.y));
                spriteComponent.setTextureRegion(treeComponent.getStumpTexturRegion());
            }
        }

        for (Entity animatedEntity : this.getEngine().getEntitiesFor(animatedEntitiesFamily)) {
            AnimationComponent animationComponent = animationComponentComponentMapper.get(animatedEntity);
            SpriteComponent spriteComponent = spriteComponentComponentMapper.get(animatedEntity);

            animationComponent.addStateTime(deltaTime);
            spriteComponent.setTextureRegion(animationComponent.getAnimation().getKeyFrame(animationComponent.getStateTime(), true));
            spriteComponent.setFlipX(animationComponent.isFlipX());
            spriteComponent.setFlipY(animationComponent.isFlipY());
            spriteComponent.setOffsetX(animationComponent.getAnimations().get(animationComponent.getCurrentAnimation()).getOffsetX());
            spriteComponent.setOffsetY(animationComponent.getAnimations().get(animationComponent.getCurrentAnimation()).getOffsetY());
        }
    }

    public static void setAnimation(AnimationComponent animationComponent) {
        AnimationData data = animationComponent.getAnimations().get(animationComponent.getCurrentAnimation());
        animationComponent.setAnimation(new Animation<>(data.getFrameTicks() / 12F, data.getFrames()));
        animationComponent.setStateTime(0);
    }

    public static Vector2 isometricToWorldCoordinates(int x, int y, float cellWidth, float cellHeight) {
        return new Vector2(
                (y + x) * cellWidth / 2.0f,
                (y - x) * cellHeight / 2.0f
        );
    }
}
