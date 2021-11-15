package com.mytest.game.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.mytest.game.component.AnimationComponent;
import com.mytest.game.component.OutfitComponent;
import com.mytest.game.component.ZombieComponent;
import com.mytest.game.utility.animation.AnimationsStorage;
import com.mytest.game.utility.animation.HasMaps;

import java.util.Arrays;

public class ClothesSystem extends EntitySystem {
    private final Family clothFamily = Family.all(OutfitComponent.class, ZombieComponent.class, AnimationComponent.class).get();

    private final ComponentMapper<OutfitComponent> outfitComponentComponentMapper = ComponentMapper.getFor(OutfitComponent.class);
    private final ComponentMapper<ZombieComponent> zombieComponentComponentMapper = ComponentMapper.getFor(ZombieComponent.class);
    private final ComponentMapper<AnimationComponent> animationComponentComponentMapper = ComponentMapper.getFor(AnimationComponent.class);
    private boolean Hat;
    private boolean Cloth;

    public ClothesSystem() {
        this.Hat = true;
        this.Cloth = true;
    }

    @Override
    public void update(float deltaTime) {
        for (Entity dressedEntity : getEngine().getEntitiesFor(clothFamily)) {
            OutfitComponent outfitComponent = outfitComponentComponentMapper.get(dressedEntity);
            ZombieComponent zombieComponent = zombieComponentComponentMapper.get(dressedEntity);
            AnimationComponent animationComponent = animationComponentComponentMapper.get(dressedEntity);

            if (outfitComponent.isHasHat()) {
                processClothes(animationComponent, outfitComponent.getHatEntity(), HasMaps.HATS_ANIMATIONS, zombieComponent.animationState, true);
            }

            if (outfitComponent.isHasCloth()) {
                processClothes(animationComponent, outfitComponent.getClothEntity(), HasMaps.CLOTHS_ANIMATIONS, zombieComponent.animationState, false);
            }
        }
    }

    private void processClothes(AnimationComponent parentAnimationComponent, Entity clothesEntity, String[] clothesAnimations, ZombieComponent.ZombieState state, Boolean isHat) {
        AnimationComponent animationComponent = animationComponentComponentMapper.get(clothesEntity);
        if (isHat) {
            if (Hat) {
                changeAnimation(animationComponent, clothesAnimations, state);
                Hat = false;
            }
        } else if (Cloth) {
            changeAnimation(animationComponent, clothesAnimations, state);
            Cloth = false;
        }
    }

    private void changeAnimation(AnimationComponent animationComponent, String[] animationsArray, ZombieComponent.ZombieState state) {
        int index = Arrays.binarySearch(animationsArray, animationComponent.getAnimationsName()) + 1;
        if (index == animationsArray.length)
            index = 0;

        animationComponent.setAnimationsName(animationsArray[index]);
        animationComponent.setCurrentAnimation(HasMaps.WOODCUTTER_ANIMATIONS_MAP.get(animationComponent.getAnimationsName()).get(state));
        animationComponent.setAnimations(AnimationsStorage.GetAnimations(animationComponent.getAnimationsName()));
        AnimationSystem.setAnimation(animationComponent);
    }

    public void changeHat() {
        this.Hat = true;
    }

    public void changeCloth() {
        this.Cloth = true;
    }
}
