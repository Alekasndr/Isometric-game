package com.mytest.game.component;

import com.badlogic.ashley.core.Component;
import com.mytest.game.entity.MouseAnimationEntity;

public class PlayerComponent implements Component {
    public MouseAnimationEntity mouseAnimationEntity;

    public PlayerComponent(MouseAnimationEntity mouseAnimationEntity) {
        this.mouseAnimationEntity = mouseAnimationEntity;
    }
}
