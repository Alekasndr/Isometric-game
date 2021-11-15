package com.mytest.game.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;

public class CurrentPathComponent implements Component {
    private Vector2 nextPathPoint;
    private Vector2 endPathPoint;
    private Queue<Vector2> path;
    public State state;

    public enum State {
        Move, Free, Build
    }

    public CurrentPathComponent() {
        this.state = state.Free;
    }

    public Vector2 getNextPathPoint() {
        return nextPathPoint;
    }

    public void setNextPathPoint(Vector2 nextPathPoint) {
        this.nextPathPoint = nextPathPoint;
    }

    public Vector2 getEndPathPoint() {
        return endPathPoint;
    }

    public void setEndPathPoint(Vector2 endPathPoint) {
        this.endPathPoint = endPathPoint;
    }

    public Queue<Vector2> getPath() {
        return path;
    }

    public void setPath(Queue<Vector2> path) {
        this.path = path;
    }

    public void setState(State state) {
        this.state = state;
    }
}
