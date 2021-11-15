package com.mytest.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mytest.game.utility.animation.AnimationData;

import java.util.HashMap;

public class AnimationComponent implements Component {
    private String animationsName;
    private String currentAnimation;
    private Animation<TextureRegion> animation;
    private HashMap<String, AnimationData> animations;
    private boolean flipX;
    private boolean flipY;
    private float stateTime = 0;

    public AnimationComponent(String animationsName, String currentAnimation, HashMap<String, AnimationData> animations) {
        this.animationsName = animationsName;
        this.currentAnimation = currentAnimation;
        AnimationData data = animations.get(this.currentAnimation);
        this.animation = new Animation<>(data.getFrameTicks() / 12.0f,
                data.getFrames());
        this.animations = animations;
    }

    public String getAnimationsName() {
        return animationsName;
    }

    public void setAnimationsName(String animationsName) {
        this.animationsName = animationsName;
    }

    public String getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(String currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public HashMap<String, AnimationData> getAnimations() {
        return animations;
    }

    public void setAnimations(HashMap<String, AnimationData> animations) {
        this.animations = animations;
    }

    public boolean isFlipX() {
        return flipX;
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public boolean isFlipY() {
        return flipY;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public void addStateTime(float stateTime) {
        this.stateTime += stateTime;
    }
}
