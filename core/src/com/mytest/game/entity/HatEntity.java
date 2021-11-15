package com.mytest.game.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.component.AnimationComponent;
import com.mytest.game.component.SpriteComponent;
import com.mytest.game.component.TransformComponent;
import com.mytest.game.utility.animation.AnimationData;
import com.mytest.game.utility.animation.AnimationsStorage;

import java.util.HashMap;

public class HatEntity extends Entity {
    public HatEntity(Vector2 position) {
        add(new TransformComponent(position));

        HashMap<String, AnimationData> animations = AnimationsStorage.GetAnimations("HAT");
        if (animations == null)
            animations = new HashMap<>();
        AnimationComponent animation = new AnimationComponent("HAT", "anim_woodcutter_hat_stand", animations);

        add(new SpriteComponent(animation.getAnimation().getKeyFrame(animation.getStateTime())));
        add(animation);
    }
}
