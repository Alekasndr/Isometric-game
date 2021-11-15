package com.mytest.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mytest.game.loaders.TileLoader;


public class ZombieComponent implements Component {
    public ZombieState animationState;
    private Vector2 startPosition;
    private Vector2 finishPosition;
    public State state;
    public boolean hasLog;
    private float woodcutTimer = 0;
    public Entity targetTreeEntity;

    public ZombieComponent() {
        this.animationState = ZombieState.STAND;
        this.state = State.Free;
        this.hasLog = false;
    }

    public enum State {
        BuildingTo,
        MovingTo,
        Cutting,
        BuildingFrom,
        MovingFrom,
        Free
    }

    public enum ZombieState {
        STAND,
        WALK_DOWN,
        WALK_UP,
        WALKWOOD_DOWN,
        WALKWOOD_UP,
        WOODCUT
    }

    public float getWoodcutTimer() {
        return woodcutTimer;
    }

    public void setWoodcutTimer(float woodcutTimer) {
        this.woodcutTimer = woodcutTimer;
    }

    public ZombieState getAnimationState() {
        return animationState;
    }

    public void setAnimationState(ZombieState animationState) {
        this.animationState = animationState;
    }

    public Vector2 getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Vector2 startPosition) {
        this.startPosition = startPosition;
    }

    public Vector2 getFinishPosition() {
        return finishPosition;
    }

    public void setFinishPosition(Vector2 finishPosition) {
        this.finishPosition = finishPosition;
    }

    public void setState(State state) {
        this.state = state;
    }
}
