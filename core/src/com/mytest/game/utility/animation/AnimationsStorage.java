package com.mytest.game.utility.animation;

import com.mytest.game.loaders.AnimationLoader;

import java.util.HashMap;

public class AnimationsStorage {

    private static final HashMap<String, HashMap<String, AnimationData>> animations = new HashMap<>();

    public static HashMap<String, AnimationData> GetAnimations(String animationsName) {
        if (!animations.containsKey(animationsName)) {
            animations.put(animationsName, AnimationLoader.LoadAnimations(animationsName));
        }
        return animations.get(animationsName);
    }
}
