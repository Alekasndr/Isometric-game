package com.mytest.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {
    private Vector2 position;
    private Vector2 size;
    private Vector2 scale;
    private float rotation;

    public TransformComponent(Vector2 position, Vector2 size, Vector2 scale, float rotation) {
        this.position = position;
        this.size = size;
        this.scale = scale;
        this.rotation = rotation;
    }

    public TransformComponent(Vector2 position, Vector2 size, Vector2 scale) {
        this(position, size, scale, 0);
    }

    public TransformComponent(Vector2 position, Vector2 size) {
        this(position, size, new Vector2(1, 1));
    }

    public TransformComponent(Vector2 position) {
        this(position, new Vector2(0, 0));
    }

    public TransformComponent() {
        this(new Vector2());
    }


    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
