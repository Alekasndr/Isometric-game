package com.mytest.game.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.component.*;
import com.mytest.game.loaders.AnimationLoader;
import com.mytest.game.utility.animation.AnimationData;

import java.util.HashMap;

public class ZombieEntity extends Entity {
    public ZombieEntity(Vector2 position) {
        super();
        add(new TransformComponent(position));

        HashMap<String, AnimationData> animations = AnimationLoader.LoadAnimations("ZOMBIE_WOODCUTTER");
        AnimationComponent animation = new AnimationComponent("ZOMBIE_WOODCUTTER", "anim_woodcutter_stand", animations);

        add(new MobsZombieComponent());
        add(new ZombieComponent());
        add(new CurrentPathComponent());
        add(new SpriteComponent(animation.getAnimation().getKeyFrame(animation.getStateTime())));
        add(animation);
    }
}
