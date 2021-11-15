package com.mytest.game.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.Main;
import com.mytest.game.component.*;
import com.mytest.game.loaders.AnimationLoader;
import com.mytest.game.utility.animation.AnimationData;

import java.util.HashMap;

public class PlayerEntity extends Entity {
    public PlayerEntity(Vector2 position){
        super();
        add(new CurrentPathComponent());
        add(new TransformComponent(position));

        HashMap<String, AnimationData> animations = AnimationLoader.LoadAnimations("ZOMBIE_WOODCUTTER");
        AnimationComponent animation = new AnimationComponent("ZOMBIE_WOODCUTTER", "anim_woodcutter_stand", animations);

        MouseAnimationEntity mouseAnimationEntity = new MouseAnimationEntity(position);
        Entity hatEntity = new HatEntity(position);
        ClothEntity clothEntity = new ClothEntity(position);
        Main.EngineInstance.addEntity(mouseAnimationEntity);
        Main.EngineInstance.addEntity(hatEntity);
        Main.EngineInstance.addEntity(clothEntity);

        add(new OutfitComponent(hatEntity, clothEntity));
        add(new ZombieComponent());
        add(new PlayerComponent(mouseAnimationEntity));
        add(new CurrentPathComponent());
        add(new SpriteComponent(animation.getAnimation().getKeyFrame(animation.getStateTime())));
        add(animation);
    }
}
