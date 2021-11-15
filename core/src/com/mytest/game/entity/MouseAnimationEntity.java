package com.mytest.game.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.component.AnimationComponent;
import com.mytest.game.component.SpriteComponent;
import com.mytest.game.component.TransformComponent;
import com.mytest.game.loaders.AnimationLoader;
import com.mytest.game.utility.animation.AnimationData;

import java.util.HashMap;

public class MouseAnimationEntity extends Entity {
    public MouseAnimationEntity(Vector2 position) {
        add(new TransformComponent(position));

        HashMap<String, AnimationData> animations = AnimationLoader.LoadAnimations("WHITE_WAVE");
        AnimationComponent animation = new AnimationComponent("WHITE_WAVE", "white_wave", animations);

        SpriteComponent spriteComponent = new SpriteComponent(animation.getAnimation().getKeyFrame(animation.getStateTime()));
        spriteComponent.setVisible(false);

        add(spriteComponent);
        add(animation);
    }
}
