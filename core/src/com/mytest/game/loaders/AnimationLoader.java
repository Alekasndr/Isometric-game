package com.mytest.game.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.mytest.game.utility.animation.AnimationData;

import java.util.HashMap;

public class AnimationLoader {
    public static HashMap<String, AnimationData> LoadAnimations(String name) {
        HashMap<String, AnimationData> animationMap = new HashMap<>();
        for (String animationName : ANIMATIONS_MAP.get(name)) {
            FileHandle fileHandle = Gdx.files.classpath(String.format("%s%s/%s.xml",
                    "animations/", animationName, animationName));
            XmlReader xmlReader = new XmlReader();
            XmlReader.Element parseConfig = xmlReader.parse(fileHandle);

            String animationTexturePath = parseConfig.getAttribute("animationTexture");
            Texture texture = new Texture(String.format("%s%s/%s", "animations/",
                    animationName, animationTexturePath));

            int animationTicks = Integer.parseInt(parseConfig.getChildByName("frames").getChildByName("list").getChildByName("Frame").getAttribute("ticks"));
            Array<TextureRegion> frames = new Array<>();

            int xOffset = parseConfig.getChildByName("frames").getChildByName("list").getChildByName("Frame").hasAttribute("xOffset") ?
                    Integer.parseInt(parseConfig.getChildByName("frames").getChildByName("list").getChildByName("Frame").getAttribute("xOffset")) : 0;
            int yOffset = parseConfig.getChildByName("frames").getChildByName("list").getChildByName("Frame").hasAttribute("yOffset") ?
                    Integer.parseInt(parseConfig.getChildByName("frames").getChildByName("list").getChildByName("Frame").getAttribute("yOffset")) : 0;

            for (XmlReader.Element frameElement : parseConfig.getChildByName("frames").getChildByName("list").getChildrenByName("Frame")) {
                int width = Integer.parseInt(frameElement.getAttribute("width"));
                int height = Integer.parseInt(frameElement.getAttribute("height"));
                int x = Integer.parseInt(frameElement.getAttribute("x"));
                int y = Integer.parseInt(frameElement.getAttribute("y"));

                frames.add(new TextureRegion(texture, x, y + height, width, -height));
            }
            animationMap.put(animationName, new AnimationData(frames, animationTicks, xOffset, yOffset));
        }
        return animationMap;
    }

    // Zombie
    public static final String ZOMBIE_ANIMATIONS_NAME = "ZOMBIE_WOODCUTTER";
    public static final String ZOMBIE_STAND_ANIMATION_NAME = "anim_woodcutter_stand";
    public static final String ZOMBIE_WAKEUP_ANIMATION_NAME = "anim_woodcutter_wakeup";
    public static final String ZOMBIE_WALK_DOWN_ANIMATION_NAME = "anim_woodcutter_walk_down";
    public static final String ZOMBIE_WALK_UP_ANIMATION_NAME = "anim_woodcutter_walk_up";
    public static final String ZOMBIE_WALKWOOD_DOWN_ANIMATION_NAME = "anim_woodcutter_walkwood_down";
    public static final String ZOMBIE_WALKWOOD_UP_ANIMATION_NAME = "anim_woodcutter_walkwood_up";
    public static final String ZOMBIE_WOODCUT_ANIMATION_NAME = "anim_woodcutter_woodcut";
    public static final String[] ZOMBIE_ANIMATIONS = new String[]{
            ZOMBIE_STAND_ANIMATION_NAME,
            ZOMBIE_WAKEUP_ANIMATION_NAME,
            ZOMBIE_WALK_DOWN_ANIMATION_NAME,
            ZOMBIE_WALK_UP_ANIMATION_NAME,
            ZOMBIE_WALKWOOD_DOWN_ANIMATION_NAME,
            ZOMBIE_WALKWOOD_UP_ANIMATION_NAME,
            ZOMBIE_WOODCUT_ANIMATION_NAME
    };
    // Hats
    public static final String HAT_ANIMATIONS_NAME = "HAT";
    public static final String HAT_STAND_ANIMATION_NAME = "anim_woodcutter_hat_stand";
    public static final String HAT_WALK_DOWN_ANIMATION_NAME = "anim_woodcutter_hat_walk_down";
    public static final String HAT_WALK_UP_ANIMATION_NAME = "anim_woodcutter_hat_walk_up";
    public static final String HAT_WALKWOOD_DOWN_ANIMATION_NAME = "anim_woodcutter_hat_walkwood_down";
    public static final String HAT_WALKWOOD_UP_ANIMATION_NAME = "anim_woodcutter_hat_walkwood_up";
    public static final String HAT_WOODCUT_ANIMATION_NAME = "anim_woodcutter_hat_woodcut";
    public static final String[] HAT_ANIMATIONS = new String[]{
            HAT_STAND_ANIMATION_NAME,
            HAT_WALK_DOWN_ANIMATION_NAME,
            HAT_WALK_UP_ANIMATION_NAME,
            HAT_WALKWOOD_DOWN_ANIMATION_NAME,
            HAT_WALKWOOD_UP_ANIMATION_NAME,
            HAT_WOODCUT_ANIMATION_NAME
    };
    public static final String SECOND_HAT_ANIMATIONS_NAME = "SECOND_HAT";
    public static final String SECOND_HAT_STAND_ANIMATION_NAME = "anim_woodcutter_double_hat_stand";
    public static final String SECOND_HAT_WALK_DOWN_ANIMATION_NAME = "anim_woodcutter_double_hat_walk_down";
    public static final String SECOND_HAT_WALK_UP_ANIMATION_NAME = "anim_woodcutter_double_hat_walk_up";
    public static final String SECOND_HAT_WALKWOOD_DOWN_ANIMATION_NAME = "anim_woodcutter_double_hat_walkwood_down";
    public static final String SECOND_HAT_WALKWOOD_UP_ANIMATION_NAME = "anim_woodcutter_double_hat_walkwood_up";
    public static final String SECOND_HAT_WOODCUT_ANIMATION_NAME = "anim_woodcutter_double_hat_woodcut";
    public static final String[] SECOND_HAT_ANIMATIONS = new String[]{
            SECOND_HAT_STAND_ANIMATION_NAME,
            SECOND_HAT_WALK_DOWN_ANIMATION_NAME,
            SECOND_HAT_WALK_UP_ANIMATION_NAME,
            SECOND_HAT_WALKWOOD_DOWN_ANIMATION_NAME,
            SECOND_HAT_WALKWOOD_UP_ANIMATION_NAME,
            SECOND_HAT_WOODCUT_ANIMATION_NAME
    };
    // Cloth
    public static final String CLOTH_ANIMATIONS_NAME = "CLOTH";
    public static final String CLOTH_STAND_ANIMATION_NAME = "anim_woodcutter_cloth_stand";
    public static final String CLOTH_WALK_DOWN_ANIMATION_NAME = "anim_woodcutter_cloth_walk_down";
    public static final String CLOTH_WALK_UP_ANIMATION_NAME = "anim_woodcutter_cloth_walk_up";
    public static final String CLOTH_WALKWOOD_DOWN_ANIMATION_NAME = "anim_woodcutter_cloth_walkwood_down";
    public static final String CLOTH_WALKWOOD_UP_ANIMATION_NAME = "anim_woodcutter_cloth_walkwood_up";
    public static final String CLOTH_WOODCUT_ANIMATION_NAME = "anim_woodcutter_cloth_woodcut";
    public static final String[] CLOTH_ANIMATIONS = new String[]{
            CLOTH_STAND_ANIMATION_NAME,
            CLOTH_WALK_DOWN_ANIMATION_NAME,
            CLOTH_WALK_UP_ANIMATION_NAME,
            CLOTH_WALKWOOD_DOWN_ANIMATION_NAME,
            CLOTH_WALKWOOD_UP_ANIMATION_NAME,
            CLOTH_WOODCUT_ANIMATION_NAME
    };
    public static final String SECOND_CLOTH_ANIMATIONS_NAME = "DOUBLE_CLOTH";
    public static final String SECOND_CLOTH_STAND_ANIMATION_NAME = "anim_woodcutter_double_cloth_stand";
    public static final String SECOND_CLOTH_WALK_DOWN_ANIMATION_NAME = "anim_woodcutter_double_cloth_walk_down";
    public static final String SECOND_CLOTH_WALK_UP_ANIMATION_NAME = "anim_woodcutter_double_cloth_walk_up";
    public static final String SECOND_CLOTH_WALKWOOD_DOWN_ANIMATION_NAME = "anim_woodcutter_double_cloth_walkwood_down";
    public static final String SECOND_CLOTH_WALKWOOD_UP_ANIMATION_NAME = "anim_woodcutter_double_cloth_walkwood_up";
    public static final String SECOND_CLOTH_WOODCUT_ANIMATION_NAME = "anim_woodcutter_double_cloth_woodcut";
    public static final String[] SECOND_CLOTH_ANIMATIONS = new String[]{
            SECOND_CLOTH_STAND_ANIMATION_NAME,
            SECOND_CLOTH_WALK_DOWN_ANIMATION_NAME,
            SECOND_CLOTH_WALK_UP_ANIMATION_NAME,
            SECOND_CLOTH_WALKWOOD_DOWN_ANIMATION_NAME,
            SECOND_CLOTH_WALKWOOD_UP_ANIMATION_NAME,
            SECOND_CLOTH_WOODCUT_ANIMATION_NAME
    };

    // Mouse
    public static final String WHITE_WAVE_ANIMATIONS_NAME = "WHITE_WAVE";
    public static final String WHITE_WAVE_ANIMATION_NAME = "white_wave";
    public static final String[] WHITE_WAVE_ANIMATIONS = new String[]{
            WHITE_WAVE_ANIMATION_NAME
    };
    // Map for animations
    public static final HashMap<String, String[]> ANIMATIONS_MAP = new HashMap<String, String[]>() {{
        put(ZOMBIE_ANIMATIONS_NAME, ZOMBIE_ANIMATIONS);

        put(HAT_ANIMATIONS_NAME, HAT_ANIMATIONS);
        put(SECOND_HAT_ANIMATIONS_NAME, SECOND_HAT_ANIMATIONS);

        put(CLOTH_ANIMATIONS_NAME, CLOTH_ANIMATIONS);
        put(SECOND_CLOTH_ANIMATIONS_NAME, SECOND_CLOTH_ANIMATIONS);

        put(WHITE_WAVE_ANIMATIONS_NAME, WHITE_WAVE_ANIMATIONS);
    }};
}
