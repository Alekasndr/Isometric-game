package com.mytest.game.utility.animation;

import com.mytest.game.component.ZombieComponent;
import com.mytest.game.loaders.AnimationLoader;

import java.util.HashMap;

public class HasMaps {
    public static final HashMap<ZombieComponent.ZombieState, String> ZOMBIE_STATES_ANIMATIONS = new HashMap<ZombieComponent.ZombieState, String>() {{
        put(ZombieComponent.ZombieState.STAND, AnimationLoader.ZOMBIE_STAND_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALK_DOWN, AnimationLoader.ZOMBIE_WALK_DOWN_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALK_UP, AnimationLoader.ZOMBIE_WALK_UP_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALKWOOD_DOWN, AnimationLoader.ZOMBIE_WALKWOOD_DOWN_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALKWOOD_UP, AnimationLoader.ZOMBIE_WALKWOOD_UP_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WOODCUT, AnimationLoader.ZOMBIE_WOODCUT_ANIMATION_NAME);
    }};
    public static final HashMap<ZombieComponent.ZombieState, String> HAT_STATES_ANIMATIONS = new HashMap<ZombieComponent.ZombieState, String>() {{
        put(ZombieComponent.ZombieState.STAND, AnimationLoader.HAT_STAND_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALK_DOWN, AnimationLoader.HAT_WALK_DOWN_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALK_UP, AnimationLoader.HAT_WALK_UP_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALKWOOD_DOWN, AnimationLoader.HAT_WALKWOOD_DOWN_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALKWOOD_UP, AnimationLoader.HAT_WALKWOOD_UP_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WOODCUT, AnimationLoader.HAT_WOODCUT_ANIMATION_NAME);
    }};
    public static final HashMap<ZombieComponent.ZombieState, String> SECOND_HAT_STATES_ANIMATIONS = new HashMap<ZombieComponent.ZombieState, String>() {{
        put(ZombieComponent.ZombieState.STAND, AnimationLoader.SECOND_HAT_STAND_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALK_DOWN, AnimationLoader.SECOND_HAT_WALK_DOWN_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALK_UP, AnimationLoader.SECOND_HAT_WALK_UP_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALKWOOD_DOWN, AnimationLoader.SECOND_HAT_WALKWOOD_DOWN_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALKWOOD_UP, AnimationLoader.SECOND_HAT_WALKWOOD_UP_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WOODCUT, AnimationLoader.SECOND_HAT_WOODCUT_ANIMATION_NAME);
    }};
    public static final HashMap<ZombieComponent.ZombieState, String> CLOTH_ACTIONS_ANIMATIONS = new HashMap<ZombieComponent.ZombieState, String>() {{
        put(ZombieComponent.ZombieState.STAND, AnimationLoader.CLOTH_STAND_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALK_DOWN, AnimationLoader.CLOTH_WALK_DOWN_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALK_UP, AnimationLoader.CLOTH_WALK_UP_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALKWOOD_DOWN, AnimationLoader.CLOTH_WALKWOOD_DOWN_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALKWOOD_UP, AnimationLoader.CLOTH_WALKWOOD_UP_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WOODCUT, AnimationLoader.CLOTH_WOODCUT_ANIMATION_NAME);
    }};
    public static final HashMap<ZombieComponent.ZombieState, String> SECOND_CLOTH_STATES_ANIMATIONS = new HashMap<ZombieComponent.ZombieState, String>() {{
        put(ZombieComponent.ZombieState.STAND, AnimationLoader.SECOND_CLOTH_STAND_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALK_DOWN, AnimationLoader.SECOND_CLOTH_WALK_DOWN_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALK_UP, AnimationLoader.SECOND_CLOTH_WALK_UP_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALKWOOD_DOWN, AnimationLoader.SECOND_CLOTH_WALKWOOD_DOWN_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WALKWOOD_UP, AnimationLoader.SECOND_CLOTH_WALKWOOD_UP_ANIMATION_NAME);
        put(ZombieComponent.ZombieState.WOODCUT, AnimationLoader.SECOND_CLOTH_WOODCUT_ANIMATION_NAME);
    }};

    public static final HashMap<String, HashMap<ZombieComponent.ZombieState, String>> WOODCUTTER_ANIMATIONS_MAP = new HashMap<String, HashMap<ZombieComponent.ZombieState, String>>() {{
        put(AnimationLoader.ZOMBIE_ANIMATIONS_NAME, ZOMBIE_STATES_ANIMATIONS);
        put(AnimationLoader.HAT_ANIMATIONS_NAME, HAT_STATES_ANIMATIONS);
        put(AnimationLoader.SECOND_HAT_ANIMATIONS_NAME, SECOND_HAT_STATES_ANIMATIONS);
        put(AnimationLoader.CLOTH_ANIMATIONS_NAME, CLOTH_ACTIONS_ANIMATIONS);
        put(AnimationLoader.SECOND_CLOTH_ANIMATIONS_NAME, SECOND_CLOTH_STATES_ANIMATIONS);
    }};

    public static final String[] HATS_ANIMATIONS = new String[]{
            AnimationLoader.HAT_ANIMATIONS_NAME,
            AnimationLoader.SECOND_HAT_ANIMATIONS_NAME
    };
    public static final String[] CLOTHS_ANIMATIONS = new String[]{
            AnimationLoader.CLOTH_ANIMATIONS_NAME,
            AnimationLoader.SECOND_CLOTH_ANIMATIONS_NAME
    };
}
