package com.mytest.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.loaders.MapLoader;

public class MouseInputComponent implements Component {
    private Vector2 drag;
    private float zoomScroll;
    private Vector2 rightClik;

    public MouseInputComponent() {
        drag = new Vector2(0.0F, 0.0F);
        rightClik = new Vector2(0.0F, 0.0F);
        zoomScroll = MapLoader.loadScale().get(0);
    }

    public void setDrag(Vector2 drag) {
        this.drag.x = drag.x;
        this.drag.y = drag.y;
    }

    public Vector2 getDrag() {
        return drag;
    }

    public float getZoomScroll() {
        return zoomScroll;
    }

    public void setZoomScroll(float zoomScroll) {
        this.zoomScroll += zoomScroll;
    }

    public Vector2 getRightClik() {
        return rightClik;
    }

    public void setRightClik(Vector2 rightClik) {
        this.rightClik.x = rightClik.x;
        this.rightClik.y = rightClik.y;
    }

}
