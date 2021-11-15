package com.mytest.game.entity;

import com.badlogic.ashley.core.Entity;
import com.mytest.game.component.MouseInputComponent;

public class MouseEntity extends Entity {
    public MouseEntity() {
        super();
        add(new MouseInputComponent());
    }
}
