package com.mytest.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class OutfitComponent implements Component {
    private Entity hatEntity;
    private Entity clothEntity;
    private boolean hasHat;
    private boolean hasCloth;

    public OutfitComponent(Entity hatEntity, Entity clothEntity) {
        this.hatEntity = hatEntity;
        this.clothEntity = clothEntity;
        hasHat = true;
        hasCloth = true;
    }

    public boolean isHasHat() {
        return hasHat;
    }

    public boolean isHasCloth() {
        return hasCloth;
    }

    public Entity getHatEntity() {
        return hatEntity;
    }

    public Entity getClothEntity() {
        return clothEntity;
    }
}
